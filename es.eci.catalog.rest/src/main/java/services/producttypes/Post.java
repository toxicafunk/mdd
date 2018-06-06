package services.producttypes;

import static es.eci.catalog.model.Tables.*;

import java.util.Optional;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.DSLContext;

import es.eci.catalog.model.converters.POJOBeanConverter;
import es.eci.catalog.model.pojos.*;
import es.eci.catalog.model.pojos.db.*;
import es.eci.catalog.model.tables.records.*;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Post extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Post.class);
	
	protected void executeService(UserTransaction ut, DSLContext create, Message<Object> message)
			throws NotSupportedException, SystemException {
		ProductType entity = (ProductType) message.body();
		
		ProductTypeBean jooqBean = POJOBeanConverter.entity2Bean(create, entity);
				
		ProductTypesRecord entityRecord = create.newRecord(PRODUCT_TYPES, jooqBean);
		int numberCreated = entityRecord.store();
		
		if (numberCreated > 0) {
			entity.setId(entityRecord.get_Id());
			DeliveryOptions options = new DeliveryOptions();
			options.setCodecName("producttypesinversecodec");
			message.reply(entity, options);
		} else {
			message.reply(new JsonObject("{\"errors\": [\"Error al insertar record\"]}"));
		}
	}	
}
		