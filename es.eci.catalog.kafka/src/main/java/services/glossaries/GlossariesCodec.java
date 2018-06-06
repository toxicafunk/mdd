package services.glossaries;

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

public class GlossariesCodec implements MessageCodec<JsonObject, Glossary> {
	
	final Logger logger = LoggerFactory.getLogger(GlossariesCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Glossary decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Glossary glossary = json2Entity(json);
		logger.info(glossary);
		
		return glossary;
	}
	
	@Override
	public Glossary transform(JsonObject json) {
		Glossary glossary = json2Entity(json.encode());
		logger.info(glossary);
		return glossary;
	}
	
	public static Glossary json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Glossary json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		Optional<JsonArray> valuesArr = Optional.ofNullable(json.getJsonArray("values"));
		List<GlossaryValue> values = new ArrayList<GlossaryValue>();
		valuesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					values.add(services.glossaryvalues.GlossaryValuesCodec.json2Entity((JsonObject) j));
				}
			}
		);
		Link reference = services.links.LinksCodec.json2Entity((JsonObject) json.getValue("reference"));
		int id = idJson.orElse(-1);
		
		Glossary glossary = new Glossary();
		glossary.setValues(values);
		glossary.setReference(reference);
		
		if (id != -1) {
			glossary.setId(id);
		}
		
		return glossary;
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
	