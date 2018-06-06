package main;

import services.maps.MapsCodec;
import services.maps.MapsInverseCodec;
import services.links.LinksCodec;
import services.links.LinksInverseCodec;
import services.glossaryvalues.GlossaryValuesCodec;
import services.glossaryvalues.GlossaryValuesInverseCodec;
import services.brands.BrandsCodec;
import services.brands.BrandsInverseCodec;
import services.categories.CategoriesCodec;
import services.categories.CategoriesInverseCodec;
import services.images.ImagesCodec;
import services.images.ImagesInverseCodec;
import services.videos.VideosCodec;
import services.videos.VideosInverseCodec;
import services.producttypes.ProductTypesCodec;
import services.producttypes.ProductTypesInverseCodec;
import services.items.ItemsCodec;
import services.items.ItemsInverseCodec;
import services.gendertargets.GenderTargetsCodec;
import services.gendertargets.GenderTargetsInverseCodec;
import services.relateditems.RelatedItemsCodec;
import services.relateditems.RelatedItemsInverseCodec;
import services.suppliers.SuppliersCodec;
import services.suppliers.SuppliersInverseCodec;
import services.supplieritems.SupplierItemsCodec;
import services.supplieritems.SupplierItemsInverseCodec;
import services.glossaries.GlossariesCodec;
import services.glossaries.GlossariesInverseCodec;
import services.lkups.LkupsCodec;
import services.lkups.LkupsInverseCodec;
import services.eans.EansCodec;
import services.eans.EansInverseCodec;

import java.util.Optional;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * * Run with:
 *  java -jar target/dc-jms-integrations-1.0-SNAPSHOT-fat -conf config.json
 *  
 * @author erodriguez
 *
 */
public class Main extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private CamelContext context;
	private ConnectionFactory connectionFactory;
	
	private Optional<String> brokerUrl;
	private Optional<String> queueName;
	private Optional<String> outputDir;
	private Optional<Integer> concurrentConsumers;
	
	@Override
	public void start(Future<Void> fut) throws Exception {
	
		brokerUrl = Optional.ofNullable(config().getString("broker-url"));
		queueName = Optional.ofNullable(config().getString("queue-name"));
		outputDir = Optional.ofNullable(config().getString("output-dir"));
		concurrentConsumers = Optional.ofNullable(config().getInteger("concurrent-consumers"));
		
		String connectionUrl = brokerUrl.orElse("tcp://localhost:61616");
		connectionFactory = new ActiveMQConnectionFactory(connectionUrl);
		
		logger.info("Listening to broker: " + connectionUrl);
	
		vertx.eventBus().registerCodec(new MapsCodec());
		vertx.eventBus().registerCodec(new MapsInverseCodec());
		vertx.eventBus().registerCodec(new LinksCodec());
		vertx.eventBus().registerCodec(new LinksInverseCodec());
		vertx.eventBus().registerCodec(new GlossaryValuesCodec());
		vertx.eventBus().registerCodec(new GlossaryValuesInverseCodec());
		vertx.eventBus().registerCodec(new BrandsCodec());
		vertx.eventBus().registerCodec(new BrandsInverseCodec());
		vertx.eventBus().registerCodec(new CategoriesCodec());
		vertx.eventBus().registerCodec(new CategoriesInverseCodec());
		vertx.eventBus().registerCodec(new ImagesCodec());
		vertx.eventBus().registerCodec(new ImagesInverseCodec());
		vertx.eventBus().registerCodec(new VideosCodec());
		vertx.eventBus().registerCodec(new VideosInverseCodec());
		vertx.eventBus().registerCodec(new ProductTypesCodec());
		vertx.eventBus().registerCodec(new ProductTypesInverseCodec());
		vertx.eventBus().registerCodec(new ItemsCodec());
		vertx.eventBus().registerCodec(new ItemsInverseCodec());
		vertx.eventBus().registerCodec(new GenderTargetsCodec());
		vertx.eventBus().registerCodec(new GenderTargetsInverseCodec());
		vertx.eventBus().registerCodec(new RelatedItemsCodec());
		vertx.eventBus().registerCodec(new RelatedItemsInverseCodec());
		vertx.eventBus().registerCodec(new SuppliersCodec());
		vertx.eventBus().registerCodec(new SuppliersInverseCodec());
		vertx.eventBus().registerCodec(new SupplierItemsCodec());
		vertx.eventBus().registerCodec(new SupplierItemsInverseCodec());
		vertx.eventBus().registerCodec(new GlossariesCodec());
		vertx.eventBus().registerCodec(new GlossariesInverseCodec());
		vertx.eventBus().registerCodec(new LkupsCodec());
		vertx.eventBus().registerCodec(new LkupsInverseCodec());
		vertx.eventBus().registerCodec(new EansCodec());
		vertx.eventBus().registerCodec(new EansInverseCodec());
		
		context = new DefaultCamelContext();
		context.addComponent("jms-wpc", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(buildWPCRoute());

		context.start();		
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		context.stop();
	}
	
	private RouteBuilder buildWPCRoute() {
        return new RouteBuilder() {
            public void configure() {
            	String jmsRouteConfig = String.format("jms-wpc:%s?concurrentConsumers=%d&asyncConsumer=true", queueName.orElse("test-queue"), concurrentConsumers.orElse(10));
            	logger.info("JMS Route Config: " + jmsRouteConfig);
            	
            	from(jmsRouteConfig)
            	.process(new WPCProcessor(vertx.eventBus()))
            	.to(String.format("file:%s", outputDir.orElse("//tmp/out")));
            }
        };
    }
}
