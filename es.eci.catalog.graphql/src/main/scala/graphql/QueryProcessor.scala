package graphql

import monix.execution.Scheduler.Implicits.global
import scala.util.Failure
import scala.util.Success

import argonaut.Argonaut.StringToParseWrap
import argonaut.Argonaut.jObjectPL
import argonaut.Argonaut.jString
import argonaut.Argonaut.jStringPL
import argonaut.Argonaut.jsonObjectPL
import argonaut.Json
import argonaut.JsonObject
import info.macias.sse.EventTarget
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerResponse
import sangria.ast.Document
import sangria.execution.ErrorWithResolver
import sangria.execution.Executor
import sangria.execution.QueryAnalysisError
import sangria.marshalling.argonaut.ArgonautResultMarshaller
import sangria.parser.QueryParser
import sangria.ast.OperationType
import sangria.renderer.SchemaRenderer

object QueryProcessor {
  
  def renderSchema(response: HttpServerResponse) = {
    output(response, 200, SchemaRenderer.renderSchema(SchemaDefinition.schema))
  }

  def process(json: String, response: HttpServerResponse, vertx: Vertx, target: EventTarget) = {
    val query = jObjectPL >=> jsonObjectPL("query") >=> jStringPL
    val operationName = jObjectPL >=> jsonObjectPL("operationName") >=> jStringPL
    val variables = jObjectPL >=> jsonObjectPL("variables") >=> jObjectPL

    val parsed = json.parseOption match {
      case Some(j) ⇒ j
      case _ ⇒ Json.jEmptyObject
    }

    val vars = variables.get(parsed) match {
      case Some(obj: JsonObject) ⇒ obj
      case _ ⇒ JsonObject.empty
    }

    query.get(parsed) match {
      case Some(q) ⇒ QueryParser.parse(q) match {
        // query parsed successfully, time to execute it!
        case Success(queryAst) ⇒
          executeGraphQLQuery(queryAst, operationName.get(parsed), vars, response, vertx, target)

        // can't parse GraphQL query, return error
        case Failure(error) ⇒
          output(response, 500, Json("error" -> jString(error.getMessage)))
      }
      case _ ⇒
        output(response, 500, Json("error" -> jString("Missing query!")))
    }
  }

  def executeGraphQLQuery(query: Document, op: Option[String], vars: JsonObject, response: HttpServerResponse, vertx: Vertx, target: EventTarget) = {
    val operation = query.operationType(op)
    operation match {
      case Some(OperationType.Subscription) ⇒ {
        import sangria.streaming.monix._
        import sangria.execution.ExecutionScheme.Stream
        Executor.execute(SchemaDefinition.schema, query, graphql.GQLOutputTypes.Ctx(CatalogRepo(vertx)), operationName = op, variables = vars,
            deferredResolver = graphql.GQLOutputTypes.resolver)
          .map(serverSentEvent(target, _)).subscribe()
      }
      case _ ⇒ {
        Executor.execute(SchemaDefinition.schema, query, graphql.GQLOutputTypes.Ctx(CatalogRepo(vertx)), operationName = op, variables = vars,
            deferredResolver = graphql.GQLOutputTypes.resolver)
          .map(output(response, 200, _))
          .recover {
            case error: QueryAnalysisError ⇒ output(response, 501, error.resolveError)
            case error: ErrorWithResolver ⇒ output(response, 503, error.resolveError)
            case error: Throwable => {
              error.printStackTrace
              output(response, 500, error.getLocalizedMessage)
            }
          }
      }
    }
  }

  def serverSentEvent(target: EventTarget, json: Json): EventTarget = {
    target.send("message", json.nospaces)
  }
  
  def output(response: HttpServerResponse, statusCode: Int, json: Json): Unit =
    output(response, statusCode, json.toString())

  def output(response: HttpServerResponse, statusCode: Int, json: String): Unit = {
    response
      .putHeader("Content-Type", "application/json; charset=utf-8")
      .end(json);
  }
}
