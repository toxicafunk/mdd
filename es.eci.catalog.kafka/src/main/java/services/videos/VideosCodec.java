package services.videos;

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

public class VideosCodec implements MessageCodec<JsonObject, Video> {
	
	final Logger logger = LoggerFactory.getLogger(VideosCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Video decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Video video = json2Entity(json);
		logger.info(video);
		
		return video;
	}
	
	@Override
	public Video transform(JsonObject json) {
		Video video = json2Entity(json.encode());
		logger.info(video);
		return video;
	}
	
	public static Video json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Video json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String title = json.getString("title");
		String description = json.getString("description");
		String url = json.getString("url");
		int id = idJson.orElse(-1);
		
		Video video = new Video();
		video.setTitle(title);
		video.setDescription(description);
		video.setUrl(url);
		
		if (id != -1) {
			video.setId(id);
		}
		
		return video;
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
	