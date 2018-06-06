package services.eans;

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

public class EansCodec implements MessageCodec<JsonObject, Ean> {
	
	final Logger logger = LoggerFactory.getLogger(EansCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Ean decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Ean ean = json2Entity(json);
		logger.info(ean);
		
		return ean;
	}
	
	@Override
	public Ean transform(JsonObject json) {
		Ean ean = json2Entity(json.encode());
		logger.info(ean);
		return ean;
	}
	
	public static Ean json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Ean json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String code = json.getString("code");
		String eciRef = json.getString("eciRef");
		String[] eciRefList = (String[]) json.getValue("eciRefList");
		String webVariantCode = json.getString("webVariantCode");
		String internalProviderCode = json.getString("internalProviderCode");
		String referenceType = json.getString("referenceType");
		String productTypeId = json.getString("productTypeId");
		String[] parentCategories = (String[]) json.getValue("parentCategories");
		String lastDate = json.getString("lastDate");
		String unpublishDate = json.getString("unpublishDate");
		int id = idJson.orElse(-1);
		
		Ean ean = new Ean();
		ean.setCode(code);
		ean.setEciRef(eciRef);
		ean.setEciRefList(eciRefList);
		ean.setWebVariantCode(webVariantCode);
		ean.setInternalProviderCode(internalProviderCode);
		ean.setReferenceType(referenceType);
		ean.setProductTypeId(productTypeId);
		ean.setParentCategories(parentCategories);
		ean.setLastDate(lastDate);
		ean.setUnpublishDate(unpublishDate);
		
		if (id != -1) {
			ean.setId(id);
		}
		
		return ean;
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
	