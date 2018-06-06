package web;

import java.util.Map.Entry;
import java.util.Optional;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RestVerticle extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(RestVerticle.class);
	
	protected final String CONTENT_TYPE_LABEL = "Content-Type";
    protected final String CONTENT_TYPE_HEADER = "application/json; charset=utf-8";
    
    @Override
	public void start(Future<Void> fut) throws Exception {
		Router router = configRoutes();
        // Create the HTTP server and pass the "accept" method to the request handler
        JsonObject config = (JsonObject) config().getValue("vertx");
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config.getInteger("port", 8000),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
	}
	
	protected Router configRoutes() {
        // Create a router object
        Router router = Router.router(vertx);
       
        // Bind "/" to our message
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader(CONTENT_TYPE_LABEL, "text/html")
                    .end("<h1>Hola vertriculos web!</h1>");
        });

        // REST API
        router.get("/api/:entityName").handler(this::getEntities);
        router.get("/api/:entityName/:id").handler(this::getEntity);
        
        // Allows access to the request body for routes under '/api/:entityName'
        router.route("/api/:entityName*").handler(BodyHandler.create());
        router.post("/api/:entityName").handler(this::addEntity);
        router.patch("/api/:entityName").handler(this::updateEntity);
        router.delete("/api/:entityName/:id").handler(this::deleteEntity);
		
        return router;
    }
	
	private void getEntity(RoutingContext routingContext) {
		final String id = routingContext.request().getParam("id");
		String entityName = routingContext.pathParam("entityName").toLowerCase();

		JsonObject jsonRequest = new JsonObject();
		JsonObject jsonParameters = new JsonObject();

		jsonParameters.put("id", id);
		jsonRequest.put("parameters", jsonParameters);

		vertx.eventBus().send("services." + entityName + ".get", jsonRequest, message -> {
			if (message.succeeded()) {
				doOutput(entityName, routingContext.response(), message.result());
			}
		});
	}

	private void getEntities(RoutingContext routingContext) {
		String entityName = routingContext.pathParam("entityName").toLowerCase();

		JsonObject jsonRequest = new JsonObject();
		JsonObject jsonParameters = new JsonObject();

		for (Entry<String, String> entry : routingContext.request().params().entries()) {
			jsonParameters.put(entry.getKey(), entry.getValue());
		}

		jsonRequest.put("parameters", jsonParameters);

		vertx.eventBus().send("services." + entityName + ".get", jsonRequest, message -> {
			if (message.succeeded()) {
				doOutput(entityName, routingContext.response(), message.result());
			}
		});
	}

	private void addEntity(RoutingContext routingContext) {
		String entityName = routingContext.pathParam("entityName").toLowerCase();
		JsonObject jsonRequest = routingContext.getBodyAsJson();
		
		DeliveryOptions options = new DeliveryOptions();
		options.setCodecName(entityName + "codec");

		vertx.eventBus().send("services." + entityName + ".post", jsonRequest, options, message -> {
			doOutput(entityName, routingContext.response(), message.result());
		});
	}
	
	private void updateEntity(RoutingContext routingContext) {
		String entityName = routingContext.pathParam("entityName").toLowerCase();
		JsonObject jsonRequest = routingContext.getBodyAsJson();
		
		DeliveryOptions options = new DeliveryOptions();
		options.setCodecName(entityName + "codec");

		vertx.eventBus().send("services." + entityName + ".patch", jsonRequest, options, message -> {
			doOutput(entityName, routingContext.response(), message.result());
		});
	}
	
	private void deleteEntity(RoutingContext routingContext) {
		final String id = routingContext.request().getParam("id");
		String entityName = routingContext.pathParam("entityName").toLowerCase();

		JsonObject jsonRequest = new JsonObject();
		JsonObject jsonParameters = new JsonObject();

		jsonParameters.put("id", id);
		jsonRequest.put("parameters", jsonParameters);

		vertx.eventBus().send("services." + entityName + ".delete", jsonRequest, message -> {
			if (message.succeeded()) {
				doOutput(entityName, routingContext.response(), message.result());
			}
		});
	}

	private void doOutput(String entityName, HttpServerResponse response, Message<Object> result) {
		boolean succeeded = true;
		boolean isEmpty;
		
		MultiMap headers = result.headers();
		String jsonResponse;
		Optional<String> objectType = Optional.ofNullable(headers.get("type"));
		
		if (objectType.orElse("object").equals("array")) {
			JsonArray jsonMessage = new JsonArray(result.body().toString());
			isEmpty = jsonMessage.isEmpty();
			jsonResponse = jsonMessage.encode();
		} else {
			JsonObject jsonMessage = (JsonObject) result.body();
			JsonArray errors = jsonMessage.getJsonArray("errors");
			succeeded = errors == null;
			isEmpty = jsonMessage.isEmpty();
			jsonResponse = jsonMessage.encode();
		}
		
		int httpStatus = getHttpStatus(isEmpty, succeeded);
		Optional<String> contentRange = Optional.ofNullable(headers.get("Content-Range"));
		Optional<String> hasMore = Optional.ofNullable(headers.get("Has-More"));
		
		response.putHeader("Access-Control-Allow-Origin", "*").putHeader("Content-Location", "/api/" + entityName)
				.putHeader("Content-Range", contentRange.orElse("0"))
				.putHeader("Has-More", hasMore.orElse("false"))
				.putHeader("Content-Type", "application/json; charset=utf-8").setStatusCode(httpStatus).end(jsonResponse);
	}

	private int getHttpStatus(boolean isEmpty, boolean succeeded) {
		if (succeeded) {
			return 200;
		} else if (isEmpty) {
			return 404;
		} else {
			return 500;
		}
	}
}
