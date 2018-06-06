package services.securityaccessprivileges;

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
import es.eci.catalog.model.pojos.SecurityAccessPrivilege;
import es.eci.catalog.model.pojos.EntityBean;
import es.eci.catalog.model.tables.records.SecurityAccessPrivilege_Record;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Post extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Post.class);
	
	protected void executeService(UserTransaction ut, DSLContext ctx, Message<Object> message)
			throws NotSupportedException, SystemException {
		SecurityAccessPrivilege entity;
		if (message.body() instanceof SecurityAccessPrivilege) {
			entity = (SecurityAccessPrivilege) message.body();
		} else {
			entity = Codecs.json2SecurityAccessPrivilege((JsonObject) message.body());
		}
		
		try {
			SecurityAccessPrivilege_Record record = createRecord(ctx, entity);
			SecurityAccessPrivilege entityBean = services.securityaccessprivileges.Get.fecthById(ctx, record.get_Id_());
			message.reply(InverseCodecs.entity2JsonObject(ctx, entityBean, Collections.<String>emptyList()));
		} catch(Exception e) {
			message.reply(new JsonObject(String.format("{\"errors\": [\"Error al insertar record\", \"%s\"]}", e.getCause())));
		}
	}
	
	public static SecurityAccessPrivilege_Record createRecord(DSLContext ctx, SecurityAccessPrivilege entityBean) {
		SecurityAccessPrivilege_Record entityRecord = ctx.newRecord(SECURITY_ACCESS_PRIVILEGE_, entityBean);
		int stored = entityRecord.store();
		
		if (stored == 1) {
			HazelcastInstance hz = getCache();
			IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
			cache.set("SecurityAccessPrivilege_" + entityRecord.get_Id_().toString(), Arrays.asList(entityBean));
		}
		
		return entityRecord;
	}
}
		