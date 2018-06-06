package services.securityattributes;

import static es.eci.catalog.model.Tables.SECURITY_ATTRIBUTE_;

import java.util.List;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.DSLContext;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import es.eci.catalog.model.pojos.EntityBean;
import es.eci.catalog.model.tables.records.SecurityAttribute_Record;
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
		
		SecurityAttribute_Record record = create.fetchOne(SECURITY_ATTRIBUTE_, SECURITY_ATTRIBUTE_._ID_.eq(Integer.valueOf(idParameter)));
		
		int stored = record.delete();
		if (stored == 1) {
			HazelcastInstance hz = getCache();
			IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
			cache.delete("SecurityAttribute_" + record.get_Id_().toString());
			
			message.reply(new JsonObject());
		} else {
			message.reply(new JsonObject("{\"errors\": [\"Error al borrar record\"]}"));
		}
	}
			
}
