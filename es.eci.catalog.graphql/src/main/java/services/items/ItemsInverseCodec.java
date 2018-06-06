package services.items;

import java.util.Arrays;

import es.eci.catalog.model.pojos.Item;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ItemsInverseCodec implements MessageCodec<Item, JsonObject> {
	
	final Logger logger = LoggerFactory.getLogger(ItemsInverseCodec.class);

	@Override
	public void encodeToWire(Buffer buffer, Item entity) {
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
	public JsonObject transform(Item entity) {
		return entity2JsonObject(entity);
	}
	
	public static JsonObject entity2JsonObject(Item entity) {
	
		if (entity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("ean", entity.getEan());
		jsonToEncode.put("providerRef", entity.getProviderRef());
		jsonToEncode.put("startDate", entity.getStartDate());
		jsonToEncode.put("name", entity.getName());
		jsonToEncode.put("description", entity.getDescription());
		jsonToEncode.put("tags", entity.getTags());
		jsonToEncode.put("extendedDescription", entity.getExtendedDescription());
		jsonToEncode.put("weight", entity.getWeight());
		jsonToEncode.put("weightUnit", entity.getWeightUnit());
		jsonToEncode.put("photos", entity.getPhotos());
		jsonToEncode.put("supplementaryItems", entity.getSupplementaryItems());		
		jsonToEncode.put("soldSeparately", entity.getSoldSeparately());
		jsonToEncode.put("productId", entity.getProductId());
		jsonToEncode.put("productType", entity.getProductType());
		jsonToEncode.put("regulations", entity.getRegulations());
		jsonToEncode.put("securityInfo", entity.getSecurityInfo());
		jsonToEncode.put("category", services.categories.CategoriesInverseCodec.entity2JsonObject(entity.getCategory()));
		jsonToEncode.put("keywords", entity.getKeywords());
		jsonToEncode.put("image", entity.getImage());
		jsonToEncode.put("secondaryImages", entity.getSecondaryImages());
		jsonToEncode.put("videos", entity.getVideos());
		jsonToEncode.put("subType", entity.getSubType());
		jsonToEncode.put("style", entity.getStyle());
		jsonToEncode.put("washingInstructions", entity.getWashingInstructions());
		jsonToEncode.put("serviceDate", entity.getServiceDate());
		jsonToEncode.put("serviceComments", entity.getServiceComments());
		jsonToEncode.put("brand", services.brands.BrandsInverseCodec.entity2JsonObject(entity.getBrand()));
		jsonToEncode.put("defaultCategories", entity.getDefaultCategories());		
		jsonToEncode.put("additionalInformation", entity.getAdditionalInformation());
		jsonToEncode.put("mainProduct", services.items.ItemsInverseCodec.entity2JsonObject(entity.getMainProduct()));
		jsonToEncode.put("color", entity.getColor());
		jsonToEncode.put("color1", entity.getColor1());
		jsonToEncode.put("color2", entity.getColor2());
		jsonToEncode.put("size", entity.getSize());
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
	