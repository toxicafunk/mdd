package services.eans;

import java.util.Arrays;

import es.eci.catalog.model.pojos.Ean;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class EansInverseCodec implements MessageCodec<Ean, JsonObject> {
	
	final Logger logger = LoggerFactory.getLogger(EansInverseCodec.class);

	@Override
	public void encodeToWire(Buffer buffer, Ean entity) {
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
	public JsonObject transform(Ean entity) {
		return entity2JsonObject(entity);
	}
	
	public static JsonObject entity2JsonObject(Ean entity) {
	
		if (entity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("code", entity.getCode());
		jsonToEncode.put("eciRef", entity.getEciRef());
		jsonToEncode.put("eciRefList", entity.getEciRefList());
		jsonToEncode.put("webVariantCode", entity.getWebVariantCode());
		jsonToEncode.put("internalProviderCode", entity.getInternalProviderCode());
		jsonToEncode.put("referenceType", entity.getReferenceType());
		jsonToEncode.put("productTypeId", entity.getProductTypeId());
		jsonToEncode.put("parentCategories", entity.getParentCategories());
		jsonToEncode.put("lastDate", entity.getLastDate());
		jsonToEncode.put("unpublishDate", entity.getUnpublishDate());
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
	