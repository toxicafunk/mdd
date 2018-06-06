package services.categories;

import static es.eci.catalog.model.Tables.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import es.eci.catalog.model.converters.BeanPOJOConverter;
import es.eci.catalog.model.pojos.*;
import es.eci.catalog.model.pojos.db.*;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import services.ECIServiceVerticle;

public class Get extends ECIServiceVerticle {
	final Logger logger = LoggerFactory.getLogger(Get.class);
	
	protected void executeService(UserTransaction ut, DSLContext create, Message<Object> message)
			throws NotSupportedException, SystemException {
		
		JsonObject request = (JsonObject) message.body();
		JsonObject parameters = request.getJsonObject("parameters");

		if (parameters == null) {
			parameters = new JsonObject();
		}
				
		int limitParameter = Math.min(1000, (parameters.getString("limit", "").equals("") ? 100
				: Integer.parseInt(parameters.getString("limit"))));
		int offsetParameter = (parameters.getString("offset", "").equals("") ? 0
				: Integer.parseInt(parameters.getString("offset")));

		String initializeParameter = parameters.getString("initialize");

		HashMap<String, HashSet<Integer>> initialize = new HashMap<String, HashSet<Integer>>();
		if (initializeParameter != null) {
			for (String token : initializeParameter.split(",")) {
				initialize.put(token, new HashSet<Integer>());
			}
		}

		List<Field<?>> orderFields = order(parameters);
		SelectConditionStep<Record> query = (SelectConditionStep<Record>) create.select()
				.from(CATEGORIES)
				.where(condition(parameters))
				.orderBy(orderFields.get(0), orderFields.get(1))
				.limit(limitParameter + 1).offset(offsetParameter);
				
		List<CategoryBean> entities = query.fetchInto(CategoryBean.class);
		int total = create.selectCount().from(CATEGORIES).fetchOne(0, int.class);
		MultiMap headers = message.headers();

		if (entities.size() > 0) {
			headers.add("Content-Range", offsetParameter + 1 + "-" + (offsetParameter + entities.size()) + "/"
					+ total);
		} else {
			if (offsetParameter == 0) {
				headers.add("Content-Range", "*/0");
			} else {
				headers.add("Content-Range", "*/*");
			}
		}
				
		headers.add("Has-More", new Boolean((offsetParameter + limitParameter) < total).toString());
		
		DeliveryOptions options = new DeliveryOptions();
		
		if (entities.size() == 1) {
			options.setCodecName("categoriesinversecodec");
			headers.add("type", "object");
			message.reply(BeanPOJOConverter.toCategory(create, entities.get(0)), options.setHeaders(headers));
		} else {
			List<Category> entityList = entities.stream().map(e -> BeanPOJOConverter.toCategory(create, e)).collect(Collectors.toList());
			JsonArray jsonEntities = new JsonArray(entityList);
			headers.add("type", "array");
			logger.info(String.format("Found %d Categories", jsonEntities.size()));
			message.reply(jsonEntities.encode(), options.setHeaders(headers));
		}
	}
	
	private List<Field<?>> order(JsonObject parameters) {
		String orderParameter = parameters.getString("order");
		List<Field<?>> fields = new ArrayList<>();
		
		if (orderParameter != null) {
			String[] split = orderParameter.split("\\.");
			Field<?> field0 = CATEGORIES.field(split[0]);
			Field<?> field1 = CATEGORIES.field(split[1]);
			if (field0 != null && field1 != null) {
				fields = Arrays.asList(field0, field1);
			}
		}
		
		if (fields.isEmpty()) {
			fields.add(CATEGORIES._ID);
			fields.add(CATEGORIES._LAST_MODIFIED);
		}
		
		return fields;
	}

	private Condition condition(JsonObject parameters) {

		String idParameter = parameters.getString("id");
		String searchParameter = parameters.getString("search");

		// Advanced search expression
		// String filterParameter = parameters.getString("filter");

		Condition result = DSL.trueCondition();

		if (idParameter != null) {
			result = CATEGORIES._ID.equal(Integer.valueOf(idParameter));
		}

		if (searchParameter != null) {
			result = result.and("_search @@ to_tsquery('spanish', '" + searchParameter + ":*')");
		}

		return result;
	}			
}
	