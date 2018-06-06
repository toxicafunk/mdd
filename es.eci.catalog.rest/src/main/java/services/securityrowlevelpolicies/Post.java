package services.securityrowlevelpolicies;

import static es.eci.catalog.model.Tables.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.DSLContext;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import es.eci.catalog.model.converters.Codecs;
import es.eci.catalog.model.converters.InverseCodecs;
import es.eci.catalog.model.pojos.SecurityRowLevelPolicy;
import es.eci.catalog.model.pojos.EntityBean;
import es.eci.catalog.model.tables.records.SecurityRowLevelPolicy_Record;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Post extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Post.class);
	
	protected void executeService(UserTransaction ut, DSLContext ctx, Message<Object> message)
			throws NotSupportedException, SystemException {
		SecurityRowLevelPolicy entity;
		if (message.body() instanceof SecurityRowLevelPolicy) {
			entity = (SecurityRowLevelPolicy) message.body();
		} else {
			entity = Codecs.json2SecurityRowLevelPolicy((JsonObject) message.body());
		}
		
		try {
			SecurityRowLevelPolicy_Record record = createRecord(ctx, entity);
			SecurityRowLevelPolicy entityBean = services.securityrowlevelpolicies.Get.fecthById(ctx, record.get_Id_());
			message.reply(InverseCodecs.entity2JsonObject(ctx, entityBean, Collections.<String>emptyList()));
		} catch(Exception e) {
			message.reply(new JsonObject(String.format("{\"errors\": [\"Error al insertar record\", \"%s\"]}", e.getCause())));
		}
	}
	
	public static SecurityRowLevelPolicy_Record createRecord(DSLContext ctx, SecurityRowLevelPolicy entityBean) {
		SecurityRowLevelPolicy_Record entityRecord = ctx.newRecord(SECURITY_ROW_LEVEL_POLICY_, entityBean);
		int stored = entityRecord.store();
		
		if (stored == 1) {
			HazelcastInstance hz = getCache();
			IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
			cache.set("SecurityRowLevelPolicy_" + entityRecord.get_Id_().toString(), Arrays.asList(entityBean));
		}
		
		return entityRecord;
	}
}
		