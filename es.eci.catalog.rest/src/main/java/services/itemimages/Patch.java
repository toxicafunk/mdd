package services.itemimages;

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
import es.eci.catalog.model.pojos.ItemImage;
import es.eci.catalog.model.pojos.EntityBean;
import es.eci.catalog.model.tables.records.ItemImage_Record;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Patch extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Patch.class);
	
	protected void executeService(UserTransaction ut, DSLContext ctx, Message<Object> message)
			throws NotSupportedException, SystemException {
		JsonObject entity = (JsonObject) message.body();
		
		try {
			ItemImage_Record record = updateRecord(ctx, Codecs.json2ItemImage(entity));
			message.reply(InverseCodecs.entity2JsonObject(ctx, record.into(new ItemImage()), Collections.<String>emptyList()));
		} catch(Exception e) {
			message.reply(new JsonObject(String.format("{\"errors\": [\"Error al insertar record\", \"%s\"]}", e.getCause())));
		}
	}
	
	public static ItemImage_Record updateRecord(DSLContext ctx, ItemImage entityBean) {
		ItemImage_Record record = ctx.fetchOne(ITEM_IMAGE_, ITEM_IMAGE_._ID_.eq(entityBean.getId()));
		int stored = 0;
		if (record == null) {
			record = Post.createRecord(ctx, entityBean);
		} else {
			record.from(entityBean);
			stored = record.update();
		}
		
		if (stored == 1) {
			HazelcastInstance hz = getCache();
			IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
			cache.replace("ItemImage_" + record.get_Id_().toString(), Arrays.asList(entityBean));
		}
		
		return record;
	}	
}		
