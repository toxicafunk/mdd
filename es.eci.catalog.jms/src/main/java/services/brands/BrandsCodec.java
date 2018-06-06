package services.brands;

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

public class BrandsCodec implements MessageCodec<JsonObject, Brand> {
	
	final Logger logger = LoggerFactory.getLogger(BrandsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Brand decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Brand brand = json2Entity(json);
		logger.info(brand);
		
		return brand;
	}
	
	@Override
	public Brand transform(JsonObject json) {
		Brand brand = json2Entity(json.encode());
		logger.info(brand);
		return brand;
	}
	
	public static Brand json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Brand json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String name = json.getString("name");
		String code = json.getString("code");
		String atgCode = json.getString("atgCode");
		String description = json.getString("description");
		Boolean displayBrand = json.getBoolean("displayBrand");
		int id = idJson.orElse(-1);
		
		Brand brand = new Brand();
		brand.setName(name);
		brand.setCode(code);
		brand.setAtgCode(atgCode);
		brand.setDescription(description);
		brand.setDisplayBrand(displayBrand);
		
		if (id != -1) {
			brand.setId(id);
		}
		
		return brand;
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
	