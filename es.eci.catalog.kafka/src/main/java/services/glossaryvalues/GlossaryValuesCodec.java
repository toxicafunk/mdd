package services.glossaryvalues;

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

public class GlossaryValuesCodec implements MessageCodec<JsonObject, GlossaryValue> {
	
	final Logger logger = LoggerFactory.getLogger(GlossaryValuesCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public GlossaryValue decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		GlossaryValue glossaryvalue = json2Entity(json);
		logger.info(glossaryvalue);
		
		return glossaryvalue;
	}
	
	@Override
	public GlossaryValue transform(JsonObject json) {
		GlossaryValue glossaryvalue = json2Entity(json.encode());
		logger.info(glossaryvalue);
		return glossaryvalue;
	}
	
	public static GlossaryValue json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static GlossaryValue json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String term = json.getString("term");
		String normalizedTerm = json.getString("normalizedTerm");
		String externalCode = json.getString("externalCode");
		String description = json.getString("description");
		int id = idJson.orElse(-1);
		
		GlossaryValue glossaryvalue = new GlossaryValue();
		glossaryvalue.setCode(code);
		glossaryvalue.setTerm(term);
		glossaryvalue.setNormalizedTerm(normalizedTerm);
		glossaryvalue.setExternalCode(externalCode);
		glossaryvalue.setDescription(description);
		
		if (id != -1) {
			glossaryvalue.setId(id);
		}
		
		return glossaryvalue;
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
	