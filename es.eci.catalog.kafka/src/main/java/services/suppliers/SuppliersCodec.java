package services.suppliers;

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

public class SuppliersCodec implements MessageCodec<JsonObject, Supplier> {
	
	final Logger logger = LoggerFactory.getLogger(SuppliersCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Supplier decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Supplier supplier = json2Entity(json);
		logger.info(supplier);
		
		return supplier;
	}
	
	@Override
	public Supplier transform(JsonObject json) {
		Supplier supplier = json2Entity(json.encode());
		logger.info(supplier);
		return supplier;
	}
	
	public static Supplier json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Supplier json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String name = json.getString("name");
		int id = idJson.orElse(-1);
		
		Supplier supplier = new Supplier();
		supplier.setCode(code);
		supplier.setName(name);
		
		if (id != -1) {
			supplier.setId(id);
		}
		
		return supplier;
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
	