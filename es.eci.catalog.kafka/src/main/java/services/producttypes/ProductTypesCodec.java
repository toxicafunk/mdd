package services.producttypes;

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

public class ProductTypesCodec implements MessageCodec<JsonObject, ProductType> {
	
	final Logger logger = LoggerFactory.getLogger(ProductTypesCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public ProductType decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		ProductType producttype = json2Entity(json);
		logger.info(producttype);
		
		return producttype;
	}
	
	@Override
	public ProductType transform(JsonObject json) {
		ProductType producttype = json2Entity(json.encode());
		logger.info(producttype);
		return producttype;
	}
	
	public static ProductType json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static ProductType json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String atgCode = json.getString("atgCode");
		Optional<JsonArray> descriptionsArr = Optional.ofNullable(json.getJsonArray("descriptions"));
		List<Map> descriptions = new ArrayList<Map>();
		descriptionsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					descriptions.add(services.maps.MapsCodec.json2Entity((JsonObject) j));
				}
			}
		);
		String[] parents = (String[]) json.getValue("parents");
		Boolean isMarketplace = json.getBoolean("isMarketplace");
		String categoryListCode = json.getString("categoryListCode");
		String categoryListLabel = json.getString("categoryListLabel");
		Optional<JsonArray> categoryCodesArr = Optional.ofNullable(json.getJsonArray("categoryCodes"));
		List<Category> categoryCodes = new ArrayList<Category>();
		categoryCodesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					categoryCodes.add(services.categories.CategoriesCodec.json2Entity((JsonObject) j));
				}
			}
		);
		String importId = json.getString("importId");
		int id = idJson.orElse(-1);
		
		ProductType producttype = new ProductType();
		producttype.setCode(code);
		producttype.setAtgCode(atgCode);
		producttype.setDescriptions(descriptions);
		producttype.setParents(parents);
		producttype.setIsMarketplace(isMarketplace);
		producttype.setCategoryListCode(categoryListCode);
		producttype.setCategoryListLabel(categoryListLabel);
		producttype.setCategoryCodes(categoryCodes);
		producttype.setImportId(importId);
		
		if (id != -1) {
			producttype.setId(id);
		}
		
		return producttype;
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
	