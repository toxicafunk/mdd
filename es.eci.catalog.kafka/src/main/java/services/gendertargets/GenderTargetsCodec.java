package services.gendertargets;

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

public class GenderTargetsCodec implements MessageCodec<JsonObject, GenderTarget> {
	
	final Logger logger = LoggerFactory.getLogger(GenderTargetsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public GenderTarget decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		GenderTarget gendertarget = json2Entity(json);
		logger.info(gendertarget);
		
		return gendertarget;
	}
	
	@Override
	public GenderTarget transform(JsonObject json) {
		GenderTarget gendertarget = json2Entity(json.encode());
		logger.info(gendertarget);
		return gendertarget;
	}
	
	public static GenderTarget json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static GenderTarget json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String label = json.getString("label");
		int id = idJson.orElse(-1);
		
		GenderTarget gendertarget = new GenderTarget();
		gendertarget.setCode(code);
		gendertarget.setLabel(label);
		
		if (id != -1) {
			gendertarget.setId(id);
		}
		
		return gendertarget;
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
	