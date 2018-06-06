
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 15282911405438L;

	private Integer id;	
	private String ean;
	private String providerRef;
	private String startDate;
	private String name;
	private String description;
	private String[] tags;
	private String extendedDescription;
	private Float weight;
	private String weightUnit;
	private String[] photos;
	private List<Item> supplementaryItems;
	private Boolean soldSeparately;
	private String productId;
	private String[] productType;
	private String[] regulations;
	private String[] securityInfo;
	private Category category;
	private String[] keywords;
	private String image;
	private String[] secondaryImages;
	private String[] videos;
	private String subType;
	private String style;
	private String washingInstructions;
	private String serviceDate;
	private String serviceComments;
	private Brand brand;
	private List<Category> defaultCategories;
	private String additionalInformation;
	private Item mainProduct;
	private String color;
	private String color1;
	private String color2;
	private String size;
	
	public Item() {}
	
	public Item(
		Integer id,
	String ean
	,	String providerRef
	,	String startDate
	,	String name
	,	String description
	,	String[] tags
	,	String extendedDescription
	,	Float weight
	,	String weightUnit
	,	String[] photos
	,	List<Item> supplementaryItems
	,	Boolean soldSeparately
	,	String productId
	,	String[] productType
	,	String[] regulations
	,	String[] securityInfo
	,	Category category
	,	String[] keywords
	,	String image
	,	String[] secondaryImages
	,	String[] videos
	,	String subType
	,	String style
	,	String washingInstructions
	,	String serviceDate
	,	String serviceComments
	,	Brand brand
	,	List<Category> defaultCategories
	,	String additionalInformation
	,	Item mainProduct
	,	String color
	,	String color1
	,	String color2
	,	String size
	) {
		this.id = id;
		this.ean = ean;
		this.providerRef = providerRef;
		this.startDate = startDate;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.extendedDescription = extendedDescription;
		this.weight = weight;
		this.weightUnit = weightUnit;
		this.photos = photos;
		this.supplementaryItems = supplementaryItems;
		this.soldSeparately = soldSeparately;
		this.productId = productId;
		this.productType = productType;
		this.regulations = regulations;
		this.securityInfo = securityInfo;
		this.category = category;
		this.keywords = keywords;
		this.image = image;
		this.secondaryImages = secondaryImages;
		this.videos = videos;
		this.subType = subType;
		this.style = style;
		this.washingInstructions = washingInstructions;
		this.serviceDate = serviceDate;
		this.serviceComments = serviceComments;
		this.brand = brand;
		this.defaultCategories = defaultCategories;
		this.additionalInformation = additionalInformation;
		this.mainProduct = mainProduct;
		this.color = color;
		this.color1 = color1;
		this.color2 = color2;
		this.size = size;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public String getEan() {
  		return this.ean;
  	}
  	
  	public void setEan(String ean) {
  		this.ean = ean;
  	}
  	public String getProviderRef() {
  		return this.providerRef;
  	}
  	
  	public void setProviderRef(String providerRef) {
  		this.providerRef = providerRef;
  	}
  	public String getStartDate() {
  		return this.startDate;
  	}
  	
  	public void setStartDate(String startDate) {
  		this.startDate = startDate;
  	}
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
  	public String getDescription() {
  		return this.description;
  	}
  	
  	public void setDescription(String description) {
  		this.description = description;
  	}
  	public String[] getTags() {
  		return this.tags;
  	}
  	
  	public void setTags(String[] tags) {
  		this.tags = tags;
  	}
  	public String getExtendedDescription() {
  		return this.extendedDescription;
  	}
  	
  	public void setExtendedDescription(String extendedDescription) {
  		this.extendedDescription = extendedDescription;
  	}
  	public Float getWeight() {
  		return this.weight;
  	}
  	
  	public void setWeight(Float weight) {
  		this.weight = weight;
  	}
  	public String getWeightUnit() {
  		return this.weightUnit;
  	}
  	
  	public void setWeightUnit(String weightUnit) {
  		this.weightUnit = weightUnit;
  	}
  	public String[] getPhotos() {
  		return this.photos;
  	}
  	
  	public void setPhotos(String[] photos) {
  		this.photos = photos;
  	}
  	public List<Item> getSupplementaryItems() {
  		return this.supplementaryItems;
  	}
  	
  	public void setSupplementaryItems(List<Item> supplementaryItems) {
  		this.supplementaryItems = supplementaryItems;
  	}
  	public Boolean getSoldSeparately() {
  		return this.soldSeparately;
  	}
  	
  	public void setSoldSeparately(Boolean soldSeparately) {
  		this.soldSeparately = soldSeparately;
  	}
  	public String getProductId() {
  		return this.productId;
  	}
  	
  	public void setProductId(String productId) {
  		this.productId = productId;
  	}
  	public String[] getProductType() {
  		return this.productType;
  	}
  	
  	public void setProductType(String[] productType) {
  		this.productType = productType;
  	}
  	public String[] getRegulations() {
  		return this.regulations;
  	}
  	
  	public void setRegulations(String[] regulations) {
  		this.regulations = regulations;
  	}
  	public String[] getSecurityInfo() {
  		return this.securityInfo;
  	}
  	
  	public void setSecurityInfo(String[] securityInfo) {
  		this.securityInfo = securityInfo;
  	}
  	public Category getCategory() {
  		return this.category;
  	}
  	
  	public void setCategory(Category category) {
  		this.category = category;
  	}
  	public String[] getKeywords() {
  		return this.keywords;
  	}
  	
  	public void setKeywords(String[] keywords) {
  		this.keywords = keywords;
  	}
  	public String getImage() {
  		return this.image;
  	}
  	
  	public void setImage(String image) {
  		this.image = image;
  	}
  	public String[] getSecondaryImages() {
  		return this.secondaryImages;
  	}
  	
  	public void setSecondaryImages(String[] secondaryImages) {
  		this.secondaryImages = secondaryImages;
  	}
  	public String[] getVideos() {
  		return this.videos;
  	}
  	
  	public void setVideos(String[] videos) {
  		this.videos = videos;
  	}
  	public String getSubType() {
  		return this.subType;
  	}
  	
  	public void setSubType(String subType) {
  		this.subType = subType;
  	}
  	public String getStyle() {
  		return this.style;
  	}
  	
  	public void setStyle(String style) {
  		this.style = style;
  	}
  	public String getWashingInstructions() {
  		return this.washingInstructions;
  	}
  	
  	public void setWashingInstructions(String washingInstructions) {
  		this.washingInstructions = washingInstructions;
  	}
  	public String getServiceDate() {
  		return this.serviceDate;
  	}
  	
  	public void setServiceDate(String serviceDate) {
  		this.serviceDate = serviceDate;
  	}
  	public String getServiceComments() {
  		return this.serviceComments;
  	}
  	
  	public void setServiceComments(String serviceComments) {
  		this.serviceComments = serviceComments;
  	}
  	public Brand getBrand() {
  		return this.brand;
  	}
  	
  	public void setBrand(Brand brand) {
  		this.brand = brand;
  	}
  	public List<Category> getDefaultCategories() {
  		return this.defaultCategories;
  	}
  	
  	public void setDefaultCategories(List<Category> defaultCategories) {
  		this.defaultCategories = defaultCategories;
  	}
  	public String getAdditionalInformation() {
  		return this.additionalInformation;
  	}
  	
  	public void setAdditionalInformation(String additionalInformation) {
  		this.additionalInformation = additionalInformation;
  	}
  	public Item getMainProduct() {
  		return this.mainProduct;
  	}
  	
  	public void setMainProduct(Item mainProduct) {
  		this.mainProduct = mainProduct;
  	}
  	public String getColor() {
  		return this.color;
  	}
  	
  	public void setColor(String color) {
  		this.color = color;
  	}
  	public String getColor1() {
  		return this.color1;
  	}
  	
  	public void setColor1(String color1) {
  		this.color1 = color1;
  	}
  	public String getColor2() {
  		return this.color2;
  	}
  	
  	public void setColor2(String color2) {
  		this.color2 = color2;
  	}
  	public String getSize() {
  		return this.size;
  	}
  	
  	public void setSize(String size) {
  		this.size = size;
  	}
}			
		