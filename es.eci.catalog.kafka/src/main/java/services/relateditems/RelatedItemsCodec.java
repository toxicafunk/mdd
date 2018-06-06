package services.relateditems;

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

public class RelatedItemsCodec implements MessageCodec<JsonObject, RelatedItem> {
	
	final Logger logger = LoggerFactory.getLogger(RelatedItemsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public RelatedItem decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		RelatedItem relateditem = json2Entity(json);
		logger.info(relateditem);
		
		return relateditem;
	}
	
	@Override
	public RelatedItem transform(JsonObject json) {
		RelatedItem relateditem = json2Entity(json.encode());
		logger.info(relateditem);
		return relateditem;
	}
	
	public static RelatedItem json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static RelatedItem json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		Optional<JsonArray> itemsArr = Optional.ofNullable(json.getJsonArray("items"));
		List<Item> items = new ArrayList<Item>();
		itemsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					items.add(services.items.ItemsCodec.json2Entity((JsonObject) j));
				}
			}
		);
		int id = idJson.orElse(-1);
		
		RelatedItem relateditem = new RelatedItem();
		relateditem.setItems(items);
		
		if (id != -1) {
			relateditem.setId(id);
		}
		
		return relateditem;
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
	