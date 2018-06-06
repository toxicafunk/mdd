package web;

import graphql.QueryProcessor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class GraphQLVerticle extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(GraphQLVerticle.class);
	
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
                                logger.info("GraphQL server listening");
                            } else {
                                fut.fail(result.cause());
                                logger.info("GraphQL server failed to start!");
                            }
                        }
                );
	}
	
	protected Router configRoutes() {
        // Create a router object
        Router router = Router.router(vertx);
       
        // GraphQL call
        // Allows access to the request body for routes under '/graphql'
        router.route("/graphql").handler(BodyHandler.create());
        router.post("/graphql").handler(this::graphQLEndpoint);
        // Bind "/" to our message
        router.route().handler(StaticHandler.create());

        return router;
    }
	
	private void graphQLEndpoint(RoutingContext routingContext) {
		QueryProcessor.process(routingContext.getBodyAsString(), routingContext.response(), vertx);
	}
}
