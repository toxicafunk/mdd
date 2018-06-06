package graphql

import io.vertx.core.Vertx

import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.vertx.VertxComponent
import org.apache.camel.ProducerTemplate
import io.vertx.core.json.JsonObject
import org.apache.camel.scala.dsl.builder.RouteBuilder
import services.categories.CategoriesCodec

import scala.collection.JavaConversions._
import io.vertx.core.json.JsonArray
import java.util.concurrent.CompletableFuture
import io.vertx.core.json.Json
import scala.collection.mutable.ListBuffer


class CatalogRepo(template: ProducerTemplate) {
  
  def cat2Category(categoryPojo: es.eci.catalog.model.pojos.Category): Category = {
    Category(
        categoryPojo.getId,
        categoryPojo.getCode,
        categoryPojo.getAtgCode,
        Option(categoryPojo.getParentCategory) map { c => c.getId.toInt} getOrElse(-1),
        categoryPojo.getStatus,
        categoryPojo.getDescriptions map { m => new Map(m.getId, m.getKey, m.getValue) } toList,
        categoryPojo.getFullDescriptions map { m => new Map(m.getId, m.getKey, m.getValue) } toList,
        categoryPojo.getStartDate,
        categoryPojo.getEndDate,
        categoryPojo.getInvalidationDate,
        Option(categoryPojo.getReference).map { 
          l => new Link(l.getId, l.getIdExt, l.getCategoryId, l.getCategoryId) } getOrElse(new Link(-1, null, null, null)),
        categoryPojo.getNoveltyDays.toDouble,
        categoryPojo.getChildren map { c => c.getId.toInt } toList
    )
  }
  
  def category(id: String): Option[Category] = {
    val jsonRequest = new JsonObject()
		val jsonParameters = new JsonObject()

		jsonParameters.put("id", id);
		jsonRequest.put("parameters", jsonParameters);
		
		
    val future: CompletableFuture[Object] = template.asyncRequestBodyAndHeader("direct:vertxCluster", jsonRequest, "address", "vertx:services.categories.get");
    val json: String = template.extractFutureBody(future, classOf[String])
    
    val categoryPojo = CategoriesCodec.json2Entity(json)
    val category = cat2Category(categoryPojo)
    
    return Some(category)
  }
  
  def categories: List[Category] = {
    val future: CompletableFuture[Object] = template.asyncRequestBodyAndHeader("direct:vertxCluster", new JsonObject(), "address", "vertx:services.categories.get");
    val json: String = template.extractFutureBody(future, classOf[String])
    val jsonArray = new JsonArray(json);
    val cats: ListBuffer[Category] = new ListBuffer[Category]()
    
    jsonArray.forEach { toJavaConsumer { j => cats += cat2Category(CategoriesCodec.json2Entity(j.asInstanceOf[JsonObject])) } }
    
    cats.toList
  }
  
}

object CatalogRepo {
  def apply(vertx: Vertx) = {
    val camelContext: CamelContext = new DefaultCamelContext
    val template = camelContext.createProducerTemplate()
    val vertxComponent = new VertxComponent()
    vertxComponent.setVertx(vertx);
    camelContext.addComponent("vertx", vertxComponent);
    camelContext.addRoutes(routeBuilder)
    camelContext.start()
    new CatalogRepo(template)
  }
  
  val routeBuilder = new RouteBuilder {
   "direct:vertxCluster" recipients(_.getIn.getHeader("address"))
  }
}
