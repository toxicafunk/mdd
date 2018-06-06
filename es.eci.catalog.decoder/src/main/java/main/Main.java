package main;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ShutdownRoute;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * * Run with:
 *  java -jar target/dc-decoder-integrations-1.0-SNAPSHOT-fat.jar -conf config.json
 *  
 * @author erodriguez
 *
 */
public class Main extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
	final String START_DECODER = "start_logical_decoding";
	final String STOP_DECODER = "stop_logical_decoding";
	final String POLL_DECODER =	"poll_logical_decoding";
	
	private CamelContext camelContext;
	private ProducerTemplate template;
	
	@Override
	public void start(Future<Void> fut) throws Exception {
	
		JsonObject postgresqlConfig = (JsonObject) config().getValue("postgresql");
		JsonObject vertxConfig = (JsonObject) config().getValue("vertx");
		
		camelContext = setupCamelContext(postgresqlConfig);
		camelContext.addRoutes(buildLogicalDecodingRoute(vertxConfig));
		
		camelContext.start();
		logger.info("Camel started");
		
		template = camelContext.createProducerTemplate();
		try {
			template.sendBody("direct:create_slot", "START");
		} catch (CamelExecutionException cee) {
			logger.warn(cee);
		}
		
		Thread.sleep(2000);
		logger.info("Starting poll slot");
		camelContext.startRoute(POLL_DECODER);
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		template.sendBody("direct:stop_slot", "STOP");
		camelContext.stop();
	}
	
	private CamelContext setupCamelContext(JsonObject config) {
		DataSource dataSource = setupAtomikosDataSource(config);
		
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("postgres_decoding", dataSource);
		
		return new DefaultCamelContext(registry);
	}
	
	private DataSource setupAtomikosDataSource(JsonObject config ) {
		AtomikosDataSourceBean adsb = new AtomikosDataSourceBean();
		adsb.setUniqueResourceName("postgres");
		adsb.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
		Properties p = new Properties();
		
		if (config.getString("username") != null
				&& !config.getString("username").equals(""))
			p.setProperty("user", config.getString("username"));
		if (config.getString("password") != null
				&& !config.getString("password").equals(""))
			p.setProperty("password", config.getString("password"));
		
		p.setProperty("databaseName", config.getString("dbname"));
		
		JsonObject postgresqlAddress = config.getJsonObject("address");
		p.setProperty("serverName", postgresqlAddress.getString("host"));
		p.setProperty("portNumber", "" + postgresqlAddress.getInteger("port"));
		
		adsb.setXaProperties(p);
		
		return adsb;
	}
	
	private RouteBuilder buildLogicalDecodingRoute(JsonObject config) {
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("direct:create_slot")
					.setBody(constant("SELECT * FROM pg_create_logical_replication_slot('catalog_slot', 'decoding_json')"))
					.to("jdbc:postgres_decoding")
					.to("log:es.eci.catalog.postgres.logicaldecoding?showAll=true&multiline=true");
				
				from("timer://PollCatalogSlot?period=30000")
					.routeId(POLL_DECODER)
					.autoStartup(false)
					.shutdownRoute(ShutdownRoute.Defer)
					.setBody(constant("select * from pg_logical_slot_get_changes('catalog_slot', NULL, NULL)"))
					.to("jdbc:postgres_decoding")
					.process(new PostgresLogicalDecodingProcessor(vertx, config.getString("address")))
					.to("log:es.eci.catalog.postgres.logicaldecoding?showAll=true&multiline=true");
				
				from("direct:stop_slot")
					.setBody(constant("SELECT pg_drop_replication_slot('catalog_slot')"))
					.to("jdbc:postgres_decoding")
					.to("log:es.eci.catalog.postgres.logicaldecoding?showAll=true&multiline=true");
			};
			
		};
	}
}
