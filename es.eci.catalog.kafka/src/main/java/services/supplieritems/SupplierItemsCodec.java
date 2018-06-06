package services.supplieritems;

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

public class SupplierItemsCodec implements MessageCodec<JsonObject, SupplierItem> {
	
	final Logger logger = LoggerFactory.getLogger(SupplierItemsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public SupplierItem decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		SupplierItem supplieritem = json2Entity(json);
		logger.info(supplieritem);
		
		return supplieritem;
	}
	
	@Override
	public SupplierItem transform(JsonObject json) {
		SupplierItem supplieritem = json2Entity(json.encode());
		logger.info(supplieritem);
		return supplieritem;
	}
	
	public static SupplierItem json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static SupplierItem json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		Supplier supplier = services.suppliers.SuppliersCodec.json2Entity((JsonObject) json.getValue("supplier"));
		Item item = services.items.ItemsCodec.json2Entity((JsonObject) json.getValue("item"));
		Float price = json.getFloat("price");
		Float stock = json.getFloat("stock");
		String activeFrom = json.getString("activeFrom");
		String activeTo = json.getString("activeTo");
		int id = idJson.orElse(-1);
		
		SupplierItem supplieritem = new SupplierItem();
		supplieritem.setSupplier(supplier);
		supplieritem.setItem(item);
		supplieritem.setPrice(price);
		supplieritem.setStock(stock);
		supplieritem.setActiveFrom(activeFrom);
		supplieritem.setActiveTo(activeTo);
		
		if (id != -1) {
			supplieritem.setId(id);
		}
		
		return supplieritem;
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
	