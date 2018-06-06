package services.maps;

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

public class MapsCodec implements MessageCodec<JsonObject, Map> {
	
	final Logger logger = LoggerFactory.getLogger(MapsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Map decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Map map = json2Entity(json);
		logger.info(map);
		
		return map;
	}
	
	@Override
	public Map transform(JsonObject json) {
		Map map = json2Entity(json.encode());
		logger.info(map);
		return map;
	}
	
	public static Map json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Map json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String key = json.getString("key");
		String value = json.getString("value");
		int id = idJson.orElse(-1);
		
		Map map = new Map();
		map.setKey(key);
		map.setValue(value);
		
		if (id != -1) {
			map.setId(id);
		}
		
		return map;
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
	