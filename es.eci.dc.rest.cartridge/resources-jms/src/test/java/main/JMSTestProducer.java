package main;

import java.io.File;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class JMSTestProducer {
	
	private static ILogger logger = Logger.getLogger(JMSTestProducer.class);
	private static CamelContext context;
	
	public class HeaderTypeComputer {
	
		public void setJmsHeader(Exchange exchange) {
			@SuppressWarnings("unchecked")
			GenericFile<File> file = (GenericFile<File>) exchange.getIn().getBody();
			String name = file.getFileNameOnly();
			logger.info("setJmsHeader: " + name);
			switch (WPCType.valueOf(name.split("\\.")[0].toUpperCase())) {
			case ACTORS:
				exchange.getIn().setHeader("JMSType", WPCType.ACTORS.getCode());
				break;
			case AUTHORS:
				exchange.getIn().setHeader("JMSType", WPCType.AUTHORS.getCode());
				break;
			case BRANDS:
				exchange.getIn().setHeader("JMSType", WPCType.BRANDS.getCode());
				break;
			case CAMPAIGNS:
				exchange.getIn().setHeader("JMSType", WPCType.CAMPAIGNS.getCode());
				break;
			case FEATURES:
				exchange.getIn().setHeader("JMSType", WPCType.FEATURES.getCode());
				break;
			case GLOSSARY:
				exchange.getIn().setHeader("JMSType", WPCType.GLOSSARY.getCode());
				break;
			case PERFORMERS:
				exchange.getIn().setHeader("JMSType", WPCType.PERFORMERS.getCode());
				break;
			case PRODUCTTYPES:
				exchange.getIn().setHeader("JMSType", WPCType.PRODUCTTYPES.getCode());
				break;
			case REFERENCESESP:
				exchange.getIn().setHeader("JMSType", WPCType.REFERENCESESP.getCode());
				break;
			case SALESHIERARCHY:
				exchange.getIn().setHeader("JMSType", WPCType.SALESHIERARCHY.getCode());
				break;
			default:
				break;
			}
			
			//exchange.getIn().setHeader("CamelMongoDbLimit", 10);
		}
	}
		
	public static void main(String[] args) throws Exception {					
		
		JMSTestProducer producer = new JMSTestProducer();
		
		SimpleRegistry registry = new SimpleRegistry();
		//registerMongo(registry);
		registry.put("computeJMSHeader", producer.new HeaderTypeComputer());
		
		context = new DefaultCamelContext(registry);
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		context.addComponent("jms-wpc", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(buildWPCRoute());
		
		logger.info("Starting context");
		context.start();
		
		Thread.sleep(5000);
		
		logger.info("Stopping context");
		stop();
	}
	
	public static void stop() throws Exception {
		context.stop();
	}
	
	private static  RouteBuilder buildWPCRoute() {
        return new RouteBuilder() {
            public void configure() {
            	//from("file://utils/?noop=true&include=.*.json&exclude=uat_pimmessages.json&charset=utf-8")
            	from("file://utils/?noop=true&include=salesHierarchy.json&charset=utf-8")
            	.to("bean:computeJMSHeader")
            	.split(bodyAs(String.class).tokenize("\n"))
            	.to("jms-wpc:test-queue")
            	.to("log:es.eci.catalog.jms.amq?showAll=true&multiline=true");;
            }
        };
    }
	
	private static void registerMongo(SimpleRegistry registry) {
		MongoClientURI uri = new MongoClientURI("mongodb://eciapp:24122014mk@192.168.53.179:27017/?authSource=marketplace&authMechanism=SCRAM-SHA-1");
		MongoClient mongo = new MongoClient(uri);
		registry.put("uatmongo", mongo);
	}
 
 /*
  * Producer for direct
  * ProducerTemplate template = context.createProducerTemplate();
		try {
			template.sendBody("direct:fetch_ean", "START");
		} catch (CamelExecutionException cee) {
			cee.printStackTrace();;
		}
		
  * Route for Mongo
  * from("direct:fetch_ean")
            	.setBody().constant("{ \"type\": \"001\" }")
            	.to("mongodb:uatmongo?database=marketplace&collection=pimMessages&operation=findAll")
            	.to("log:es.eci.catalog.jms.mongo?level=INFO&showAll=true&multiline=true")
            	.to("bean:computeJMSHeader")
            	//.to("jms-wpc:test-queue?jmsMessageType=Text")
            	.to("jms-wpc:test-queue")
            	.to("log:es.eci.catalog.jms.amq?showAll=true&multiline=true");
  */
}
