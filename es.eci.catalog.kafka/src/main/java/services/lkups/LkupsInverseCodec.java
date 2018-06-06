package services.lkups;

import java.util.Arrays;

import es.eci.catalog.model.pojos.Lkup;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class LkupsInverseCodec implements MessageCodec<Lkup, JsonObject> {
	
	final Logger logger = LoggerFactory.getLogger(LkupsInverseCodec.class);

	@Override
	public void encodeToWire(Buffer buffer, Lkup entity) {
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
	public JsonObject transform(Lkup entity) {
		return entity2JsonObject(entity);
	}
	
	public static JsonObject entity2JsonObject(Lkup entity) {
	
		if (entity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("code", entity.getCode());
		jsonToEncode.put("name", entity.getName());
		jsonToEncode.put("lookupType", entity.getLookupType());
		jsonToEncode.put("descriptions", services.maps.MapsInverseCodec.entity2JsonObject(entity.getDescriptions()));
		jsonToEncode.put("atgCode", entity.getAtgCode());
		jsonToEncode.put("externalCode", entity.getExternalCode());
		jsonToEncode.put("logo", entity.getLogo());
		jsonToEncode.put("audit", entity.getAudit());
		jsonToEncode.put("priority", entity.getPriority());
		jsonToEncode.put("glossary", services.glossaries.GlossariesInverseCodec.entity2JsonObject(entity.getGlossary()));
		jsonToEncode.put("reference", services.links.LinksInverseCodec.entity2JsonObject(entity.getReference()));
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
	