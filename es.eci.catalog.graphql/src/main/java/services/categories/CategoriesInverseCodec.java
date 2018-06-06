package services.categories;

import java.util.Arrays;

import es.eci.catalog.model.pojos.Category;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class CategoriesInverseCodec implements MessageCodec<Category, JsonObject> {
	
	final Logger logger = LoggerFactory.getLogger(CategoriesInverseCodec.class);

	@Override
	public void encodeToWire(Buffer buffer, Category entity) {
		JsonObject jsonToEncode = entity2JsonObject(entity);
		String json = jsonToEncode.encode();
		
		logger.info("encoding: " + json);
		
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public JsonObject decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		JsonObject contentJson = new JsonObject(json);		
		logger.debug("decoding: " + contentJson);
		
		return contentJson;
	}

	@Override
	public JsonObject transform(Category entity) {
		return entity2JsonObject(entity);
	}
	
	public static JsonObject entity2JsonObject(Category entity) {
	
		if (entity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("code", entity.getCode());
		jsonToEncode.put("atgCode", entity.getAtgCode());
		jsonToEncode.put("parentCategory", services.categories.CategoriesInverseCodec.entity2JsonObject(entity.getParentCategory()));
		jsonToEncode.put("status", entity.getStatus());
		jsonToEncode.put("descriptions", entity.getDescriptions());		
		jsonToEncode.put("fullDescriptions", entity.getFullDescriptions());		
		jsonToEncode.put("startDate", entity.getStartDate());
		jsonToEncode.put("endDate", entity.getEndDate());
		jsonToEncode.put("invalidationDate", entity.getInvalidationDate());
		jsonToEncode.put("reference", services.links.LinksInverseCodec.entity2JsonObject(entity.getReference()));
		jsonToEncode.put("noveltyDays", entity.getNoveltyDays());
		jsonToEncode.put("children", entity.getChildren());		
		jsonToEncode.put("id", entity.getId());
		
		
		return jsonToEncode;
	}

	@Override
	public String name() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public byte systemCodecID() {
		return -1;
	}

}
	