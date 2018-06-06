package services.links;

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

public class LinksCodec implements MessageCodec<JsonObject, Link> {
	
	final Logger logger = LoggerFactory.getLogger(LinksCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Link decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Link link = json2Entity(json);
		logger.info(link);
		
		return link;
	}
	
	@Override
	public Link transform(JsonObject json) {
		Link link = json2Entity(json.encode());
		logger.info(link);
		return link;
	}
	
	public static Link json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Link json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String idExt = json.getString("idExt");
		String hierarchyId = json.getString("hierarchyId");
		String categoryId = json.getString("categoryId");
		int id = idJson.orElse(-1);
		
		Link link = new Link();
		link.setIdExt(idExt);
		link.setHierarchyId(hierarchyId);
		link.setCategoryId(categoryId);
		
		if (id != -1) {
			link.setId(id);
		}
		
		return link;
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
	