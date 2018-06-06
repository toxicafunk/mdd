package graphql

import io.vertx.core.Vertx

import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.vertx.VertxComponent
import java.util.concurrent.CompletableFuture
import org.apache.camel.ProducerTemplate
import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.RouteBuilder

class VertxEventProducer(vertx: Vertx) {
  val routeBuilder = new RouteBuilder {
   "direct:vertxCluster" recipients(_.getIn.getHeader("address"))
  }
   
  val camelContext: CamelContext = new DefaultCamelContext
  val template: ProducerTemplate =  camelContext.createProducerTemplate()
  val vertxComponent = new VertxComponent()
  vertxComponent.setVertx(vertx);
  camelContext.addComponent("vertx", vertxComponent);
  camelContext.addRoutes(routeBuilder)
  camelContext.start()
    
  def send(address: String, message: io.vertx.core.json.JsonObject): String = {
    val future: CompletableFuture[Object] = template.asyncRequestBodyAndHeader("direct:vertxCluster", message, "address", address);
    template.extractFutureBody(future, classOf[String])
  }
  
}