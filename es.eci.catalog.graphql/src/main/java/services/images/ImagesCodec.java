package services.images;

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

public class ImagesCodec implements MessageCodec<JsonObject, Image> {
	
	final Logger logger = LoggerFactory.getLogger(ImagesCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Image decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Image image = json2Entity(json);
		logger.info(image);
		
		return image;
	}
	
	@Override
	public Image transform(JsonObject json) {
		Image image = json2Entity(json.encode());
		logger.info(image);
		return image;
	}
	
	public static Image json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Image json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String url = json.getString("url");
		String description = json.getString("description");
		int id = idJson.orElse(-1);
		
		Image image = new Image();
		image.setUrl(url);
		image.setDescription(description);
		
		if (id != -1) {
			image.setId(id);
		}
		
		return image;
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
	