package services.lkups;

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

public class LkupsCodec implements MessageCodec<JsonObject, Lkup> {
	
	final Logger logger = LoggerFactory.getLogger(LkupsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Lkup decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Lkup lkup = json2Entity(json);
		logger.info(lkup);
		
		return lkup;
	}
	
	@Override
	public Lkup transform(JsonObject json) {
		Lkup lkup = json2Entity(json.encode());
		logger.info(lkup);
		return lkup;
	}
	
	public static Lkup json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Lkup json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String name = json.getString("name");
		String lookupType = json.getString("lookupType");
		Map descriptions = services.maps.MapsCodec.json2Entity((JsonObject) json.getValue("descriptions"));
		String atgCode = json.getString("atgCode");
		String externalCode = json.getString("externalCode");
		String logo = json.getString("logo");
		String audit = json.getString("audit");
		String priority = json.getString("priority");
		Glossary glossary = services.glossaries.GlossariesCodec.json2Entity((JsonObject) json.getValue("glossary"));
		Link reference = services.links.LinksCodec.json2Entity((JsonObject) json.getValue("reference"));
		int id = idJson.orElse(-1);
		
		Lkup lkup = new Lkup();
		lkup.setCode(code);
		lkup.setName(name);
		lkup.setLookupType(lookupType);
		lkup.setDescriptions(descriptions);
		lkup.setAtgCode(atgCode);
		lkup.setExternalCode(externalCode);
		lkup.setLogo(logo);
		lkup.setAudit(audit);
		lkup.setPriority(priority);
		lkup.setGlossary(glossary);
		lkup.setReference(reference);
		
		if (id != -1) {
			lkup.setId(id);
		}
		
		return lkup;
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
	