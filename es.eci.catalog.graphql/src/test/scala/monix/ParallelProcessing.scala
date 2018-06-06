package monix

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

import monix.eval._
import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.execution.Scheduler.Implicits.global
import monix.reactive._
import monix.reactive.Observer
import monix.reactive.observers._
import monix.reactive.subjects.ConcurrentSubject

case class Car(model: String, color: String, qty: Int)

object ParallelProcessing {

  def naive = {
    val items = 0 until 1000

    // The list of all tasks needed for execution
    val tasks = items.map(i => Task(i * 2))
    // Processing in parallel
    //val aggregate = Task.gather(tasks).map(_.toList)
    // Non-blocking
    val aggregate = Task.gatherUnordered(tasks).map(_.toList)

    // Evaluation:
    aggregate.foreach(println)
  }

  def batchedTasks = {
    val items = 0 until 1000

    // The list of all tasks needed for execution
    val tasks = items.map(i => Task(i * 2))
    // Building batches of 10 tasks to execute in parallel:
    val batches = tasks.sliding(10, 10).map(b => Task.gather(b))
    // Sequencing batches, then flattening the final result
    val aggregate = Task.sequence(batches).map(_.flatten.toList)

    // Evaluation:
    aggregate.foreach(println)
  }

  def batchedObservables = {
    // The `bufferIntrospective` will do buffering, up to a certain
    // `bufferSize`, for as long as the downstream is busy and then
    // stream a whole sequence of all buffered events at once
    val source = Observable.range(0, 1000).bufferIntrospective(256)

    // Processing in batches, powered by `Task`
    val batched = source.flatMap { items =>
      // The list of all tasks needed for execution
      val tasks = items.map(i => Task(i * 2))
      // Building batches of 10 tasks to execute in parallel:
      val batches = tasks.sliding(10, 10).map(b => Task.gather(b))
      // Sequencing batches, then flattening the final result
      val aggregate = Task.sequence(batches).map(_.flatten)
      // Converting into an observable, needed for flatMap
      Observable.fromTask(aggregate)
        .flatMap(i => Observable.fromIterator(i))
    }

    // Evaluation:
    batched.toListL.foreach(println)
  }

  def observableMapAsync = {
    val source = Observable.range(0, 1000)
    // The parallelism factor needs to be specified
    val processed = source.mapAsync(parallelism = 10) { i =>
      Task(i * 2)
    }

    // Evaluation:
    processed.toListL.foreach(println)
  }

  def observableMergeMap = {
    val source = Observable.range(0, 1000)
    // The parallelism factor needs to be specified
    val processed = source.mergeMap { i =>
      Observable.fork(Observable.eval(i * 2))
    }

    // Evaluation:
    processed.toListL.foreach(println)
  }
  
  val sumConsumer: Consumer[Int,Long] =
    Consumer.create[Int,Long] { (scheduler, cancelable, callback) =>
      new Observer.Sync[Int] {
        private var sum = 0L
  
        def onNext(elem: Int): Ack = {
          sum += elem
          Continue
        }
  
        def onComplete(): Unit = {
          // We are done so we can signal the final result
          callback.onSuccess(sum)
        }
  
        def onError(ex: Throwable): Unit = {
          // Error happened, so we signal the error
          callback.onError(ex)
        }
      }
    }
  
  val sourceCar = ConcurrentSubject.publish[Car]
  val s = sourceCar.map { car => s"${car.model} is color ${car.color}" }.dump("O")

  def main(args: Array[String]): Unit = {
    println("Starting!")

    //naive
    //batchedTasks
    //batchedObservables
    //observableMapAsync
    //observableMergeMap
    /*observer.onNext(Car("prius", "red"))
    observer.onNext(Car("prius", "green"))
    subscriber.onComplete()
    subscriber.onNext(Car("renegade", "white"))
    observer.onNext(Car("lexus", "black"))*/

    /*connectable.pushFirst(Car("prius", "red"))
    connectable.connect()
    connectable.onNext(Car("renegade", "white"))*/
    
    
    
    //source.subscribe()
    
    //source.foreach { car => println(s"${car.model} is color ${car.color}")}
    //source.asyncBoundary(OverflowStrategy.Unbounded).dump("O")
    //val s = sourceCar.map { car => car.qty }.dump("O")
    s.firstOrElseL(throw new IllegalStateException("Promise was not completed - observable haven't produced any elements.")).runAsync
    //s.lastOrElseL(throw new IllegalStateException("Promise was not completed - observable haven't produced any elements.")).runAsync
    //s.consumeWith(sumConsumer).runAsync
    
    sendEvents
    
    Thread.sleep(1000)
    sourceCar.onNext(Car("kia", "green", 3))
    println("Really Done!")
  }
  
  def sendEvents = {
    sourceCar.onNext(Car("prius", "red", 2))
    sourceCar.onNext(Car("lexus", "black", 3))
    Thread.sleep(3000)
    sourceCar.onNext(Car("renegade", "white", 1))
  }
}