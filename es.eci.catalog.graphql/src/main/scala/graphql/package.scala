import java.util.function.Consumer

package object graphql {
  def toJavaFunction[U, V](f:Function1[U,V]): Function[U, V] = new Function[U, V] {
    override def apply(t: U): V = f(t)
  }
  
  def toJavaConsumer[T, Unit](f: Function1[T, Unit]): Consumer[T] = new Consumer[T] {
    override def accept(t: T) = f(t)
  }
}