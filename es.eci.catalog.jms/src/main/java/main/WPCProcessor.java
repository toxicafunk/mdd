package main;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.mindcurv.argonaut.JsonWPCMessage;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


public class WPCProcessor implements Processor {
	
	final Logger logger = LoggerFactory.getLogger(WPCProcessor.class);
	
	private EventBus eventBus;

	public WPCProcessor(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info(String.format("Received message of type %s", exchange.getIn().getHeader("JMSType")));
		
		String type = (String) exchange.getIn().getHeader("JMSType");
		String body = (String) exchange.getIn().getBody();
		constructJSONRequest(exchange, body, type);
	}

	private void constructJSONRequest(Exchange exchange, String body, String type) {
		DeliveryOptions options = new DeliveryOptions();
		JsonObject jsonRequest;
		String serviceType;
		
		switch(wpcTypeFromCode(type)) {
		case ACTORS:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.ACTORS));
			serviceType = "categories";
			break;
		case AUTHORS:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.AUTHORS));
			serviceType = "categories";
			break;
		case BRANDS:
			options.setCodecName("brandcodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.BRANDS));
			serviceType = "brands";
			break;
		case CAMPAIGNS:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.CAMPAIGNS));
			serviceType = "categories";
			break;
		case FEATURES:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.FEATURES));
			serviceType = "categories";
			break;
		case GLOSSARY:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.GLOSSARY));
			serviceType = "categories";
			break;
		case PERFORMERS:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.PERFORMERS));
			serviceType = "categories";
			break;
		case PRODUCTTYPES:
			options.setCodecName("producttypescodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.PRODUCTTYPES));
			serviceType = "producttypes";
			break;
		case REFERENCESESP:
			options.setCodecName("eanscodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.REFERENCESESP));
			serviceType = "eans";
			break;
		case SALESHIERARCHY:
			options.setCodecName("categorycodec");
			jsonRequest = new JsonObject(JsonWPCMessage.wpc2DC(body, WPCType.SALESHIERARCHY));
			serviceType = "categories";
			break;
		default:
			jsonRequest = new JsonObject();
			serviceType = "categories";
			break;
		}
		
		callService(jsonRequest, options, serviceType);
	}
	
	private void callService(JsonObject jsonRequest, DeliveryOptions options, String serviceType) {
		String serviceName = String.format("services.%s.post", serviceType);
		
		eventBus.send(serviceName, jsonRequest, options, message -> {
			if (message.succeeded()) {
				logger.info(message.result());
			}
		});
	}

	public WPCType wpcTypeFromCode(String code) {
		for (WPCType type : WPCType.values()) {
			if (code.equals(type.getCode()))
				return type;
		}
		
		return WPCType.UNDEFINED;
	}
}
