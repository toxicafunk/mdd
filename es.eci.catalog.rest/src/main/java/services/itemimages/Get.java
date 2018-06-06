package services.itemimages;

import static es.eci.catalog.model.Tables.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.SelectForUpdateStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import es.eci.catalog.model.converters.InverseCodecs;
import es.eci.catalog.model.pojos.*;
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

	public static ItemImage fecthById(DSLContext ctx, Integer id) {
		return ctx.select().from(ITEM_IMAGE_).where(ITEM_IMAGE_._ID_.eq(id)).fetchInto(ItemImage.class).get(0);
	}

	public static List<ItemImage> fecthByIds(DSLContext ctx, Integer... ids) {
		return ctx.select().from(ITEM_IMAGE_).where(ITEM_IMAGE_._ID_.in(ids)).fetchInto(ItemImage.class);
	}

	public static List<ItemImage> fecthByMasterId(DSLContext ctx, Integer id) {
		return ctx.select().from(ITEM_IMAGE_).where(ITEM_IMAGE_._ITEM_ID_.eq(id)).fetchInto(ItemImage.class);
	}

	protected void executeService(UserTransaction ut, DSLContext ctx, Message<Object> message)
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

		boolean returnAsList = parameters.getBoolean("returnAsList", Boolean.TRUE);
		String initializeParameter = parameters.getString("initialize", "");
		List<String> initialize = Arrays.asList(initializeParameter.split(","));

		String idParameter = parameters.getString("id");

		List<SortField<?>> orderFields = order(parameters);
		SelectSelectStep<Record> selectStep = ctx.select();
		SelectJoinStep<Record> joinStep = fromClause(selectStep, initialize);
		SelectForUpdateStep<Record> query = joinStep
				.where(condition(parameters, idParameter))
				.orderBy(orderFields)
				.limit(limitParameter).offset(offsetParameter);

		logger.debug(String.format("SQL: %s;", query.toString()));

		HazelcastInstance hz = getCache();
		IMap<String, List<EntityBean>> cache = hz.getMap("CATALOG_CACHE");
		String key = ((returnAsList)? queryAsString(query) : "ItemImage_" + idParameter)  + "_" + initializeParameter;
		logger.info(String.format("Key: %s", key));
		List<EntityBean> entities = cache.get(key);

		// TODO: cache the final result
		int found = 0;
		if (entities == null) {
			entities = query.fetch().stream().map(r -> (ItemImage) r.into(ITEM_IMAGE_).into(ItemImage.class)).collect(Collectors.toList());

			found = entities.size();
			logger.info(String.format("Found %d ItemImages", found));
			if (found > 0) {
				logger.info(String.format("Adding Key: %s to cache", key));
				cache.putIfAbsent(key, entities);
			}
		}

		MultiMap headers = message.headers();
		int total = ctx.selectCount().from(ITEM_IMAGE_).fetchOne(0, int.class);

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

		if (entities.isEmpty()) {
			logger.info("No itemimages found!");
			if (StringUtils.isEmpty(parameters.getString("id"))) {
				headers.add("type", "array");
				message.reply(new JsonArray(), options.setHeaders(headers));
			} else {
				headers.add("type", "object");
				message.reply(new JsonObject(), options.setHeaders(headers));
			}
		}
		else if (returnAsList) {
			List<JsonObject> entityList = entities.stream().map(e -> InverseCodecs.entity2JsonObject(ctx, (ItemImage) e, initialize)).collect(Collectors.toList());
			JsonArray jsonEntities = new JsonArray(entityList);
			headers.add("type", "array");
			message.reply(jsonEntities.encode(), options.setHeaders(headers));
		} else {
			headers.add("type", "object");
			message.reply(InverseCodecs.entity2JsonObject(ctx, (ItemImage) entities.get(0), initialize), options.setHeaders(headers));
		}
	}

	private List<SortField<?>> order(JsonObject parameters) {
		String orderParameter = parameters.getString("order");
		List<SortField<?>> fields = new ArrayList<>();

		if (orderParameter != null) {
			String[] splittedParameters = orderParameter.split("\\,");
			for (String parameter : splittedParameters) {
				String[] split = parameter.split("\\.");
				if (split.length == 2) {
					Field<?> field = ITEM_IMAGE_.field(split[0]+"_");
					SortField<?> sortField = ("desc".equals(split[1].trim()))? field.desc() : field.asc();
					fields.add(sortField);
				}
			}
		}

		if (fields.isEmpty()) {
			fields.add(ITEM_IMAGE_._ID_.asc());
			fields.add(ITEM_IMAGE_._LAST_MODIFIED_.asc());
		}

		return fields;
	}

	private String queryAsString(SelectForUpdateStep<Record> query) {
		return query.toString().replaceAll("\\W", "");
	}

	private SelectJoinStep<Record> fromClause(SelectSelectStep<Record> selectStep, List<String> initialize) {
		SelectJoinStep<Record> clause = selectStep.from(ITEM_IMAGE_);


		return clause;
	}

	private Condition condition(JsonObject parameters, String idParameter) {

		String searchParameter = parameters.getString("search");

		// Advanced search expression
		// String filterParameter = parameters.getString("filter");

		Condition result = DSL.trueCondition();

		if (StringUtils.isNotEmpty(idParameter)) {
			String[] strIds = idParameter.split(",");

			if (strIds.length == 1) {
				result = ITEM_IMAGE_._ID_.equal(Integer.valueOf(strIds[0]));
			} else if (strIds.length > 1) {
				Integer[] ids = Stream.of(strIds).map(s -> Integer.valueOf(s)).collect(Collectors.toList()).toArray(new Integer[strIds.length]);
				result = ITEM_IMAGE_._ID_.in(ids);
			}
		}

		if (searchParameter != null) {
			result = result.and("public.item_image_._search_ @@ to_tsquery('spanish', '" + searchParameter + ":*')");
		}

		return result;
	}
}
	