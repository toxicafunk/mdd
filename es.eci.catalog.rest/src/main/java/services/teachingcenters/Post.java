package services.teachingcenters;

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
import es.eci.catalog.model.pojos.TeachingCenter;
import es.eci.catalog.model.pojos.EntityBean;
import es.eci.catalog.model.tables.records.TeachingCenter_Record;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Post extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Post.class);
	
	protected void executeService(UserTransaction ut, DSLContext ctx, Message<Object> message)
			throws NotSupportedException, SystemException {
		TeachingCenter entity;
		if (message.body() instanceof TeachingCenter) {
			entity = (TeachingCenter) message.body();
		} else {
			entity = Codecs.json2TeachingCenter((JsonObject) message.body());
		}
		
		try {
			TeachingCenter_Record record = createRecord(ctx, entity);
			TeachingCenter entityBean = services.teachingcenters.Get.fecthById(ctx, record.get_Id_());
			message.reply(InverseCodecs.entity2JsonObject(ctx, entityBean, Collections.<String>emptyList()));
		} catch(Exception e) {
			message.reply(new JsonObject(String.format("{\"errors\": [\"Error al insertar record\", \"%s\"]}", e.getCause())));
		}
	}
	
	public static TeachingCenter_Record createRecord(DSLContext ctx, TeachingCenter entityBean) {
		TeachingCenter_Record entityRecord = ctx.newRecord(TEACHING_CENTER_, entityBean);
		int stored = entityRecord.store();
		
		if (stored == 1) {
			HazelcastInstance hz = getCache();
			IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
			cache.set("TeachingCenter_" + entityRecord.get_Id_().toString(), Arrays.asList(entityBean));
		}
		
		return entityRecord;
	}
}
		