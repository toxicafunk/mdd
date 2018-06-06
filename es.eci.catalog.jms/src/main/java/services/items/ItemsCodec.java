package services.items;

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

public class ItemsCodec implements MessageCodec<JsonObject, Item> {
	
	final Logger logger = LoggerFactory.getLogger(ItemsCodec.class);
	
	@Override
	public void encodeToWire(Buffer buffer, JsonObject entity) {
		String json = entity.encode();
		buffer.appendInt(json.getBytes().length);
		buffer.appendString(json);
	}

	@Override
	public Item decodeFromWire(int pos, Buffer buffer) {
		int _pos = pos;
		int length = buffer.getInt(_pos);
		
		// Get JSON string by it`s length
		// Jump 4 because getInt() == 4 bytes
		String json = buffer.getString(_pos += 4, _pos += length);
		Item item = json2Entity(json);
		logger.info(item);
		
		return item;
	}
	
	@Override
	public Item transform(JsonObject json) {
		Item item = json2Entity(json.encode());
		logger.info(item);
		return item;
	}
	
	public static Item json2Entity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Entity(contentJson);
	}
	
	public static Item json2Entity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return null;
		
		Optional<Integer> idJson = Optional.ofNullable(json.getInteger("id"));
		
		String ean = json.getString("ean");
		String providerRef = json.getString("providerRef");
		String startDate = json.getString("startDate");
		String name = json.getString("name");
		String description = json.getString("description");
		String[] tags = (String[]) json.getValue("tags");
		String extendedDescription = json.getString("extendedDescription");
		Float weight = json.getFloat("weight");
		String weightUnit = json.getString("weightUnit");
		String[] photos = (String[]) json.getValue("photos");
		Optional<JsonArray> supplementaryItemsArr = Optional.ofNullable(json.getJsonArray("supplementaryItems"));
		List<Item> supplementaryItems = new ArrayList<Item>();
		supplementaryItemsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					supplementaryItems.add(services.items.ItemsCodec.json2Entity((JsonObject) j));
				}
			}
		);
		Boolean soldSeparately = json.getBoolean("soldSeparately");
		String productId = json.getString("productId");
		String[] productType = (String[]) json.getValue("productType");
		String[] regulations = (String[]) json.getValue("regulations");
		String[] securityInfo = (String[]) json.getValue("securityInfo");
		Category category = services.categories.CategoriesCodec.json2Entity((JsonObject) json.getValue("category"));
		String[] keywords = (String[]) json.getValue("keywords");
		String image = json.getString("image");
		String[] secondaryImages = (String[]) json.getValue("secondaryImages");
		String[] videos = (String[]) json.getValue("videos");
		String subType = json.getString("subType");
		String style = json.getString("style");
		String washingInstructions = json.getString("washingInstructions");
		String serviceDate = json.getString("serviceDate");
		String serviceComments = json.getString("serviceComments");
		Brand brand = services.brands.BrandsCodec.json2Entity((JsonObject) json.getValue("brand"));
		Optional<JsonArray> defaultCategoriesArr = Optional.ofNullable(json.getJsonArray("defaultCategories"));
		List<Category> defaultCategories = new ArrayList<Category>();
		defaultCategoriesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					defaultCategories.add(services.categories.CategoriesCodec.json2Entity((JsonObject) j));
				}
			}
		);
		String additionalInformation = json.getString("additionalInformation");
		Item mainProduct = services.items.ItemsCodec.json2Entity((JsonObject) json.getValue("mainProduct"));
		String color = json.getString("color");
		String color1 = json.getString("color1");
		String color2 = json.getString("color2");
		String size = json.getString("size");
		int id = idJson.orElse(-1);
		
		Item item = new Item();
		item.setEan(ean);
		item.setProviderRef(providerRef);
		item.setStartDate(startDate);
		item.setName(name);
		item.setDescription(description);
		item.setTags(tags);
		item.setExtendedDescription(extendedDescription);
		item.setWeight(weight);
		item.setWeightUnit(weightUnit);
		item.setPhotos(photos);
		item.setSupplementaryItems(supplementaryItems);
		item.setSoldSeparately(soldSeparately);
		item.setProductId(productId);
		item.setProductType(productType);
		item.setRegulations(regulations);
		item.setSecurityInfo(securityInfo);
		item.setCategory(category);
		item.setKeywords(keywords);
		item.setImage(image);
		item.setSecondaryImages(secondaryImages);
		item.setVideos(videos);
		item.setSubType(subType);
		item.setStyle(style);
		item.setWashingInstructions(washingInstructions);
		item.setServiceDate(serviceDate);
		item.setServiceComments(serviceComments);
		item.setBrand(brand);
		item.setDefaultCategories(defaultCategories);
		item.setAdditionalInformation(additionalInformation);
		item.setMainProduct(mainProduct);
		item.setColor(color);
		item.setColor1(color1);
		item.setColor2(color2);
		item.setSize(size);
		
		if (id != -1) {
			item.setId(id);
		}
		
		return item;
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
	