package services.supplieritems;

import java.util.Arrays;

import es.eci.catalog.model.pojos.SupplierItem;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class SupplierItemsInverseCodec implements MessageCodec<SupplierItem, JsonObject> {
	
	final Logger logger = LoggerFactory.getLogger(SupplierItemsInverseCodec.class);

	@Override
	public void encodeToWire(Buffer buffer, SupplierItem entity) {
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
	public JsonObject transform(SupplierItem entity) {
		return entity2JsonObject(entity);
	}
	
	public static JsonObject entity2JsonObject(SupplierItem entity) {
	
		if (entity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("supplier", services.suppliers.SuppliersInverseCodec.entity2JsonObject(entity.getSupplier()));
		jsonToEncode.put("item", services.items.ItemsInverseCodec.entity2JsonObject(entity.getItem()));
		jsonToEncode.put("price", entity.getPrice());
		jsonToEncode.put("stock", entity.getStock());
		jsonToEncode.put("activeFrom", entity.getActiveFrom());
		jsonToEncode.put("activeTo", entity.getActiveTo());
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
	