package services.categories;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import es.eci.catalog.model.pojos.*;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class CategoriesCodec implements MessageCodec<JsonObject, Category> {
	
	final Logger logger = LoggerFactory.getLogger(CategoriesCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Category decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Category category = json2Entity(json);
		logger.info(category);
		
		return category;
	}
	
	@Override
	public Category transform(JsonObject json) {
		Category category = json2Entity(json.encode());
		logger.info(category);
		return category;
	}
	
	public static Category json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Category json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String atgCode = json.getString("atgCode");
		Category parentCategory = services.categories.CategoriesCodec.json2Entity((JsonObject) json.getValue("parentCategory"));
		String status = json.getString("status");
		Optional<JsonArray> descriptionsArr = Optional.ofNullable(json.getJsonArray("descriptions"));
		List<Map> descriptions = new ArrayList<Map>();
		descriptionsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					descriptions.add(services.maps.MapsCodec.json2Entity((JsonObject) j));
				}
			}
		);
		Optional<JsonArray> fullDescriptionsArr = Optional.ofNullable(json.getJsonArray("fullDescriptions"));
		List<Map> fullDescriptions = new ArrayList<Map>();
		fullDescriptionsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					fullDescriptions.add(services.maps.MapsCodec.json2Entity((JsonObject) j));
				}
			}
		);
		String startDate = json.getString("startDate");
		String endDate = json.getString("endDate");
		String invalidationDate = json.getString("invalidationDate");
		Link reference = services.links.LinksCodec.json2Entity((JsonObject) json.getValue("reference"));
		Float noveltyDays = json.getFloat("noveltyDays");
		Optional<JsonArray> childrenArr = Optional.ofNullable(json.getJsonArray("children"));
		List<Category> children = new ArrayList<Category>();
		childrenArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					children.add(services.categories.CategoriesCodec.json2Entity((JsonObject) j));
				}
			}
		);
		int id = idJson.orElse(-1);
		
		Category category = new Category();
		category.setCode(code);
		category.setAtgCode(atgCode);
		category.setParentCategory(parentCategory);
		category.setStatus(status);
		category.setDescriptions(descriptions);
		category.setFullDescriptions(fullDescriptions);
		category.setStartDate(startDate);
		category.setEndDate(endDate);
		category.setInvalidationDate(invalidationDate);
		category.setReference(reference);
		category.setNoveltyDays(noveltyDays);
		category.setChildren(children);
		
		if (id != -1) {
			category.setId(id);
		}
		
		return category;
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
	