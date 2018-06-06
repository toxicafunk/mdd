package services.brands;

import static es.eci.catalog.model.Tables.BRANDS;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.DSLContext;

import es.eci.catalog.model.tables.records.BrandsRecord;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Delete extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Get.class);
	
	protected void executeService(UserTransaction ut, DSLContext create, Message<Object> message)
			throws NotSupportedException, SystemException {
		JsonObject request = (JsonObject) message.body();
		JsonObject parameters = request.getJsonObject("parameters");
		
		String idParameter = parameters.getString("id");
		
		BrandsRecord entityRecord = create.fetchOne(BRANDS, BRANDS._ID.eq(Integer.valueOf(idParameter)));
		int numberCreated = entityRecord.delete();
		
		if (numberCreated > 0) {
			message.reply(new JsonObject());
		} else {
			message.reply(new JsonObject("{\"errors\": [\"Error al insertar record\"]}"));
		}
	}
			
}
