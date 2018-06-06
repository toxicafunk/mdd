
package es.eci.catalog.model.converters;

import static es.eci.catalog.model.Tables.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;

import es.eci.catalog.model.pojos.*;
import es.eci.catalog.model.pojos.db.*;
import es.eci.catalog.model.tables.records.*;

public class POJOBeanConverter {

	public static MapBean entity2Bean(DSLContext create, Map entity) {
		MapBean bean = new MapBean();
		bean.setId(entity.getId());
		Optional<String> keyOpt = Optional.ofNullable(entity.getKey());
		String key = keyOpt.orElse(null);
		bean.setKey(key);
		Optional<String> valueOpt = Optional.ofNullable(entity.getValue());
		String value = valueOpt.orElse(null);
		bean.setValue(value);
		
		return bean;
	}
	
	public static LinkBean entity2Bean(DSLContext create, Link entity) {
		LinkBean bean = new LinkBean();
		bean.setId(entity.getId());
		Optional<String> idExtOpt = Optional.ofNullable(entity.getIdExt());
		String idExt = idExtOpt.orElse(null);
		bean.setIdExt(idExt);
		Optional<String> hierarchyIdOpt = Optional.ofNullable(entity.getHierarchyId());
		String hierarchyId = hierarchyIdOpt.orElse(null);
		bean.setHierarchyId(hierarchyId);
		Optional<String> categoryIdOpt = Optional.ofNullable(entity.getCategoryId());
		String categoryId = categoryIdOpt.orElse(null);
		bean.setCategoryId(categoryId);
		
		return bean;
	}
	
	public static GlossaryValueBean entity2Bean(DSLContext create, GlossaryValue entity) {
		GlossaryValueBean bean = new GlossaryValueBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> termOpt = Optional.ofNullable(entity.getTerm());
		String term = termOpt.orElse(null);
		bean.setTerm(term);
		Optional<String> normalizedTermOpt = Optional.ofNullable(entity.getNormalizedTerm());
		String normalizedTerm = normalizedTermOpt.orElse(null);
		bean.setNormalizedTerm(normalizedTerm);
		Optional<String> externalCodeOpt = Optional.ofNullable(entity.getExternalCode());
		String externalCode = externalCodeOpt.orElse(null);
		bean.setExternalCode(externalCode);
		Optional<String> descriptionOpt = Optional.ofNullable(entity.getDescription());
		String description = descriptionOpt.orElse(null);
		bean.setDescription(description);
		
		return bean;
	}
	
	public static BrandBean entity2Bean(DSLContext create, Brand entity) {
		BrandBean bean = new BrandBean();
		bean.setId(entity.getId());
		Optional<String> nameOpt = Optional.ofNullable(entity.getName());
		String name = nameOpt.orElse(null);
		bean.setName(name);
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> atgCodeOpt = Optional.ofNullable(entity.getAtgCode());
		String atgCode = atgCodeOpt.orElse(null);
		bean.setAtgCode(atgCode);
		Optional<String> descriptionOpt = Optional.ofNullable(entity.getDescription());
		String description = descriptionOpt.orElse(null);
		bean.setDescription(description);
		Optional<Boolean> displayBrandOpt = Optional.ofNullable(entity.getDisplayBrand());
		Boolean displayBrand = displayBrandOpt.orElse(false);
		bean.setDisplayBrand(displayBrand);
		
		return bean;
	}
	
	public static CategoryBean entity2Bean(DSLContext create, Category entity) {
		CategoryBean bean = new CategoryBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> atgCodeOpt = Optional.ofNullable(entity.getAtgCode());
		String atgCode = atgCodeOpt.orElse(null);
		bean.setAtgCode(atgCode);
		Optional<Category> parentCategoryOpt = Optional.ofNullable(entity.getParentCategory());
		Category parentCategory = parentCategoryOpt.orElse(new Category());
		CategoriesRecord parentCategoryR = create.newRecord(CATEGORIES, parentCategory);
		parentCategoryR.store();
		bean.setParentCategory(parentCategoryR.get_Id());
		Optional<String> statusOpt = Optional.ofNullable(entity.getStatus());
		String status = statusOpt.orElse(null);
		bean.setStatus(status);
		Optional<List<Map>> descriptionsOpt = Optional.ofNullable(entity.getDescriptions());
		List<Integer> descriptionsIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Map> descriptionsList = descriptionsOpt.orElse(Collections.EMPTY_LIST);
		for (Map  descriptions : descriptionsList ) {
			MapsRecord descriptionsR = create.newRecord(MAPS, descriptions);
			descriptionsR.store();
			descriptionsIds.add(descriptionsR.get_Id());
		}
		bean.setDescriptions(descriptionsIds.toArray(new Integer[descriptionsIds.size()]));
		Optional<List<Map>> fullDescriptionsOpt = Optional.ofNullable(entity.getFullDescriptions());
		List<Integer> fullDescriptionsIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Map> fullDescriptionsList = fullDescriptionsOpt.orElse(Collections.EMPTY_LIST);
		for (Map  fullDescriptions : fullDescriptionsList ) {
			MapsRecord fullDescriptionsR = create.newRecord(MAPS, fullDescriptions);
			fullDescriptionsR.store();
			fullDescriptionsIds.add(fullDescriptionsR.get_Id());
		}
		bean.setFullDescriptions(fullDescriptionsIds.toArray(new Integer[fullDescriptionsIds.size()]));
		Optional<String> startDateOpt = Optional.ofNullable(entity.getStartDate());
		String startDate = startDateOpt.orElse(null);
		bean.setStartDate(startDate);
		Optional<String> endDateOpt = Optional.ofNullable(entity.getEndDate());
		String endDate = endDateOpt.orElse(null);
		bean.setEndDate(endDate);
		Optional<String> invalidationDateOpt = Optional.ofNullable(entity.getInvalidationDate());
		String invalidationDate = invalidationDateOpt.orElse(null);
		bean.setInvalidationDate(invalidationDate);
		Optional<Link> referenceOpt = Optional.ofNullable(entity.getReference());
		Link reference = referenceOpt.orElse(new Link());
		LinksRecord referenceR = create.newRecord(LINKS, reference);
		referenceR.store();
		bean.setReference(referenceR.get_Id());
		Optional<Float> noveltyDaysOpt = Optional.ofNullable(entity.getNoveltyDays());
		Float noveltyDays = noveltyDaysOpt.orElse(null);
		bean.setNoveltyDays(noveltyDays);
		Optional<List<Category>> childrenOpt = Optional.ofNullable(entity.getChildren());
		List<Integer> childrenIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Category> childrenList = childrenOpt.orElse(Collections.EMPTY_LIST);
		for (Category  children : childrenList ) {
			CategoriesRecord childrenR = create.newRecord(CATEGORIES, children);
			childrenR.store();
			childrenIds.add(childrenR.get_Id());
		}
		bean.setChildren(childrenIds.toArray(new Integer[childrenIds.size()]));
		
		return bean;
	}
	
	public static ImageBean entity2Bean(DSLContext create, Image entity) {
		ImageBean bean = new ImageBean();
		bean.setId(entity.getId());
		Optional<String> urlOpt = Optional.ofNullable(entity.getUrl());
		String url = urlOpt.orElse(null);
		bean.setUrl(url);
		Optional<String> descriptionOpt = Optional.ofNullable(entity.getDescription());
		String description = descriptionOpt.orElse(null);
		bean.setDescription(description);
		
		return bean;
	}
	
	public static VideoBean entity2Bean(DSLContext create, Video entity) {
		VideoBean bean = new VideoBean();
		bean.setId(entity.getId());
		Optional<String> titleOpt = Optional.ofNullable(entity.getTitle());
		String title = titleOpt.orElse(null);
		bean.setTitle(title);
		Optional<String> descriptionOpt = Optional.ofNullable(entity.getDescription());
		String description = descriptionOpt.orElse(null);
		bean.setDescription(description);
		Optional<String> urlOpt = Optional.ofNullable(entity.getUrl());
		String url = urlOpt.orElse(null);
		bean.setUrl(url);
		
		return bean;
	}
	
	public static ProductTypeBean entity2Bean(DSLContext create, ProductType entity) {
		ProductTypeBean bean = new ProductTypeBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> atgCodeOpt = Optional.ofNullable(entity.getAtgCode());
		String atgCode = atgCodeOpt.orElse(null);
		bean.setAtgCode(atgCode);
		Optional<List<Map>> descriptionsOpt = Optional.ofNullable(entity.getDescriptions());
		List<Integer> descriptionsIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Map> descriptionsList = descriptionsOpt.orElse(Collections.EMPTY_LIST);
		for (Map  descriptions : descriptionsList ) {
			MapsRecord descriptionsR = create.newRecord(MAPS, descriptions);
			descriptionsR.store();
			descriptionsIds.add(descriptionsR.get_Id());
		}
		bean.setDescriptions(descriptionsIds.toArray(new Integer[descriptionsIds.size()]));
		Optional<String[]> parentsOpt = Optional.ofNullable(entity.getParents());
		String[] parents = parentsOpt.orElse(null);
		bean.setParents(parents);
		Optional<Boolean> isMarketplaceOpt = Optional.ofNullable(entity.getIsMarketplace());
		Boolean isMarketplace = isMarketplaceOpt.orElse(false);
		bean.setIsMarketplace(isMarketplace);
		Optional<String> categoryListCodeOpt = Optional.ofNullable(entity.getCategoryListCode());
		String categoryListCode = categoryListCodeOpt.orElse(null);
		bean.setCategoryListCode(categoryListCode);
		Optional<String> categoryListLabelOpt = Optional.ofNullable(entity.getCategoryListLabel());
		String categoryListLabel = categoryListLabelOpt.orElse(null);
		bean.setCategoryListLabel(categoryListLabel);
		Optional<List<Category>> categoryCodesOpt = Optional.ofNullable(entity.getCategoryCodes());
		List<Integer> categoryCodesIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Category> categoryCodesList = categoryCodesOpt.orElse(Collections.EMPTY_LIST);
		for (Category  categoryCodes : categoryCodesList ) {
			CategoriesRecord categoryCodesR = create.newRecord(CATEGORIES, categoryCodes);
			categoryCodesR.store();
			categoryCodesIds.add(categoryCodesR.get_Id());
		}
		bean.setCategoryCodes(categoryCodesIds.toArray(new Integer[categoryCodesIds.size()]));
		Optional<String> importIdOpt = Optional.ofNullable(entity.getImportId());
		String importId = importIdOpt.orElse(null);
		bean.setImportId(importId);
		
		return bean;
	}
	
	public static ItemBean entity2Bean(DSLContext create, Item entity) {
		ItemBean bean = new ItemBean();
		bean.setId(entity.getId());
		Optional<String> eanOpt = Optional.ofNullable(entity.getEan());
		String ean = eanOpt.orElse(null);
		bean.setEan(ean);
		Optional<String> providerRefOpt = Optional.ofNullable(entity.getProviderRef());
		String providerRef = providerRefOpt.orElse(null);
		bean.setProviderRef(providerRef);
		Optional<String> startDateOpt = Optional.ofNullable(entity.getStartDate());
		String startDate = startDateOpt.orElse(null);
		bean.setStartDate(startDate);
		Optional<String> nameOpt = Optional.ofNullable(entity.getName());
		String name = nameOpt.orElse(null);
		bean.setName(name);
		Optional<String> descriptionOpt = Optional.ofNullable(entity.getDescription());
		String description = descriptionOpt.orElse(null);
		bean.setDescription(description);
		Optional<String[]> tagsOpt = Optional.ofNullable(entity.getTags());
		String[] tags = tagsOpt.orElse(null);
		bean.setTags(tags);
		Optional<String> extendedDescriptionOpt = Optional.ofNullable(entity.getExtendedDescription());
		String extendedDescription = extendedDescriptionOpt.orElse(null);
		bean.setExtendedDescription(extendedDescription);
		Optional<Float> weightOpt = Optional.ofNullable(entity.getWeight());
		Float weight = weightOpt.orElse(null);
		bean.setWeight(weight);
		Optional<String> weightUnitOpt = Optional.ofNullable(entity.getWeightUnit());
		String weightUnit = weightUnitOpt.orElse(null);
		bean.setWeightUnit(weightUnit);
		Optional<String[]> photosOpt = Optional.ofNullable(entity.getPhotos());
		String[] photos = photosOpt.orElse(null);
		bean.setPhotos(photos);
		Optional<List<Item>> supplementaryItemsOpt = Optional.ofNullable(entity.getSupplementaryItems());
		List<Integer> supplementaryItemsIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Item> supplementaryItemsList = supplementaryItemsOpt.orElse(Collections.EMPTY_LIST);
		for (Item  supplementaryItems : supplementaryItemsList ) {
			ItemsRecord supplementaryItemsR = create.newRecord(ITEMS, supplementaryItems);
			supplementaryItemsR.store();
			supplementaryItemsIds.add(supplementaryItemsR.get_Id());
		}
		bean.setSupplementaryItems(supplementaryItemsIds.toArray(new Integer[supplementaryItemsIds.size()]));
		Optional<Boolean> soldSeparatelyOpt = Optional.ofNullable(entity.getSoldSeparately());
		Boolean soldSeparately = soldSeparatelyOpt.orElse(true);
		bean.setSoldSeparately(soldSeparately);
		Optional<String> productIdOpt = Optional.ofNullable(entity.getProductId());
		String productId = productIdOpt.orElse(null);
		bean.setProductId(productId);
		Optional<String[]> productTypeOpt = Optional.ofNullable(entity.getProductType());
		String[] productType = productTypeOpt.orElse(null);
		bean.setProductType(productType);
		Optional<String[]> regulationsOpt = Optional.ofNullable(entity.getRegulations());
		String[] regulations = regulationsOpt.orElse(null);
		bean.setRegulations(regulations);
		Optional<String[]> securityInfoOpt = Optional.ofNullable(entity.getSecurityInfo());
		String[] securityInfo = securityInfoOpt.orElse(null);
		bean.setSecurityInfo(securityInfo);
		Optional<Category> categoryOpt = Optional.ofNullable(entity.getCategory());
		Category category = categoryOpt.orElse(new Category());
		CategoriesRecord categoryR = create.newRecord(CATEGORIES, category);
		categoryR.store();
		bean.setCategory(categoryR.get_Id());
		Optional<String[]> keywordsOpt = Optional.ofNullable(entity.getKeywords());
		String[] keywords = keywordsOpt.orElse(null);
		bean.setKeywords(keywords);
		Optional<String> imageOpt = Optional.ofNullable(entity.getImage());
		String image = imageOpt.orElse(null);
		bean.setImage(image);
		Optional<String[]> secondaryImagesOpt = Optional.ofNullable(entity.getSecondaryImages());
		String[] secondaryImages = secondaryImagesOpt.orElse(null);
		bean.setSecondaryImages(secondaryImages);
		Optional<String[]> videosOpt = Optional.ofNullable(entity.getVideos());
		String[] videos = videosOpt.orElse(null);
		bean.setVideos(videos);
		Optional<String> subTypeOpt = Optional.ofNullable(entity.getSubType());
		String subType = subTypeOpt.orElse(null);
		bean.setSubType(subType);
		Optional<String> styleOpt = Optional.ofNullable(entity.getStyle());
		String style = styleOpt.orElse(null);
		bean.setStyle(style);
		Optional<String> washingInstructionsOpt = Optional.ofNullable(entity.getWashingInstructions());
		String washingInstructions = washingInstructionsOpt.orElse(null);
		bean.setWashingInstructions(washingInstructions);
		Optional<String> serviceDateOpt = Optional.ofNullable(entity.getServiceDate());
		String serviceDate = serviceDateOpt.orElse(null);
		bean.setServiceDate(serviceDate);
		Optional<String> serviceCommentsOpt = Optional.ofNullable(entity.getServiceComments());
		String serviceComments = serviceCommentsOpt.orElse(null);
		bean.setServiceComments(serviceComments);
		Optional<Brand> brandOpt = Optional.ofNullable(entity.getBrand());
		Brand brand = brandOpt.orElse(new Brand());
		BrandsRecord brandR = create.newRecord(BRANDS, brand);
		brandR.store();
		bean.setBrand(brandR.get_Id());
		Optional<List<Category>> defaultCategoriesOpt = Optional.ofNullable(entity.getDefaultCategories());
		List<Integer> defaultCategoriesIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Category> defaultCategoriesList = defaultCategoriesOpt.orElse(Collections.EMPTY_LIST);
		for (Category  defaultCategories : defaultCategoriesList ) {
			CategoriesRecord defaultCategoriesR = create.newRecord(CATEGORIES, defaultCategories);
			defaultCategoriesR.store();
			defaultCategoriesIds.add(defaultCategoriesR.get_Id());
		}
		bean.setDefaultCategories(defaultCategoriesIds.toArray(new Integer[defaultCategoriesIds.size()]));
		Optional<String> additionalInformationOpt = Optional.ofNullable(entity.getAdditionalInformation());
		String additionalInformation = additionalInformationOpt.orElse(null);
		bean.setAdditionalInformation(additionalInformation);
		Optional<Item> mainProductOpt = Optional.ofNullable(entity.getMainProduct());
		Item mainProduct = mainProductOpt.orElse(new Item());
		ItemsRecord mainProductR = create.newRecord(ITEMS, mainProduct);
		mainProductR.store();
		bean.setMainProduct(mainProductR.get_Id());
		Optional<String> colorOpt = Optional.ofNullable(entity.getColor());
		String color = colorOpt.orElse(null);
		bean.setColor(color);
		Optional<String> color1Opt = Optional.ofNullable(entity.getColor1());
		String color1 = color1Opt.orElse(null);
		bean.setColor1(color1);
		Optional<String> color2Opt = Optional.ofNullable(entity.getColor2());
		String color2 = color2Opt.orElse(null);
		bean.setColor2(color2);
		Optional<String> sizeOpt = Optional.ofNullable(entity.getSize());
		String size = sizeOpt.orElse(null);
		bean.setSize(size);
		
		return bean;
	}
	
	public static GenderTargetBean entity2Bean(DSLContext create, GenderTarget entity) {
		GenderTargetBean bean = new GenderTargetBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> labelOpt = Optional.ofNullable(entity.getLabel());
		String label = labelOpt.orElse(null);
		bean.setLabel(label);
		
		return bean;
	}
	
	public static RelatedItemBean entity2Bean(DSLContext create, RelatedItem entity) {
		RelatedItemBean bean = new RelatedItemBean();
		bean.setId(entity.getId());
		Optional<List<Item>> itemsOpt = Optional.ofNullable(entity.getItems());
		List<Integer> itemsIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<Item> itemsList = itemsOpt.orElse(Collections.EMPTY_LIST);
		for (Item  items : itemsList ) {
			ItemsRecord itemsR = create.newRecord(ITEMS, items);
			itemsR.store();
			itemsIds.add(itemsR.get_Id());
		}
		bean.setItems(itemsIds.toArray(new Integer[itemsIds.size()]));
		
		return bean;
	}
	
	public static SupplierBean entity2Bean(DSLContext create, Supplier entity) {
		SupplierBean bean = new SupplierBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> nameOpt = Optional.ofNullable(entity.getName());
		String name = nameOpt.orElse(null);
		bean.setName(name);
		
		return bean;
	}
	
	public static SupplierItemBean entity2Bean(DSLContext create, SupplierItem entity) {
		SupplierItemBean bean = new SupplierItemBean();
		bean.setId(entity.getId());
		Optional<Supplier> supplierOpt = Optional.ofNullable(entity.getSupplier());
		Supplier supplier = supplierOpt.orElse(new Supplier());
		SuppliersRecord supplierR = create.newRecord(SUPPLIERS, supplier);
		supplierR.store();
		bean.setSupplier(supplierR.get_Id());
		Optional<Item> itemOpt = Optional.ofNullable(entity.getItem());
		Item item = itemOpt.orElse(new Item());
		ItemsRecord itemR = create.newRecord(ITEMS, item);
		itemR.store();
		bean.setItem(itemR.get_Id());
		Optional<Float> priceOpt = Optional.ofNullable(entity.getPrice());
		Float price = priceOpt.orElse(null);
		bean.setPrice(price);
		Optional<Float> stockOpt = Optional.ofNullable(entity.getStock());
		Float stock = stockOpt.orElse(null);
		bean.setStock(stock);
		Optional<String> activeFromOpt = Optional.ofNullable(entity.getActiveFrom());
		String activeFrom = activeFromOpt.orElse(null);
		bean.setActiveFrom(activeFrom);
		Optional<String> activeToOpt = Optional.ofNullable(entity.getActiveTo());
		String activeTo = activeToOpt.orElse(null);
		bean.setActiveTo(activeTo);
		
		return bean;
	}
	
	public static GlossaryBean entity2Bean(DSLContext create, Glossary entity) {
		GlossaryBean bean = new GlossaryBean();
		bean.setId(entity.getId());
		Optional<List<GlossaryValue>> valuesOpt = Optional.ofNullable(entity.getValues());
		List<Integer> valuesIds = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		List<GlossaryValue> valuesList = valuesOpt.orElse(Collections.EMPTY_LIST);
		for (GlossaryValue  values : valuesList ) {
			GlossaryValuesRecord valuesR = create.newRecord(GLOSSARY_VALUES, values);
			valuesR.store();
			valuesIds.add(valuesR.get_Id());
		}
		bean.setValues(valuesIds.toArray(new Integer[valuesIds.size()]));
		Optional<Link> referenceOpt = Optional.ofNullable(entity.getReference());
		Link reference = referenceOpt.orElse(new Link());
		LinksRecord referenceR = create.newRecord(LINKS, reference);
		referenceR.store();
		bean.setReference(referenceR.get_Id());
		
		return bean;
	}
	
	public static LkupBean entity2Bean(DSLContext create, Lkup entity) {
		LkupBean bean = new LkupBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> nameOpt = Optional.ofNullable(entity.getName());
		String name = nameOpt.orElse(null);
		bean.setName(name);
		Optional<String> lookupTypeOpt = Optional.ofNullable(entity.getLookupType());
		String lookupType = lookupTypeOpt.orElse(null);
		bean.setLookupType(lookupType);
		Optional<Map> descriptionsOpt = Optional.ofNullable(entity.getDescriptions());
		Map descriptions = descriptionsOpt.orElse(new Map());
		MapsRecord descriptionsR = create.newRecord(MAPS, descriptions);
		descriptionsR.store();
		bean.setDescriptions(descriptionsR.get_Id());
		Optional<String> atgCodeOpt = Optional.ofNullable(entity.getAtgCode());
		String atgCode = atgCodeOpt.orElse(null);
		bean.setAtgCode(atgCode);
		Optional<String> externalCodeOpt = Optional.ofNullable(entity.getExternalCode());
		String externalCode = externalCodeOpt.orElse(null);
		bean.setExternalCode(externalCode);
		Optional<String> logoOpt = Optional.ofNullable(entity.getLogo());
		String logo = logoOpt.orElse(null);
		bean.setLogo(logo);
		Optional<String> auditOpt = Optional.ofNullable(entity.getAudit());
		String audit = auditOpt.orElse(null);
		bean.setAudit(audit);
		Optional<String> priorityOpt = Optional.ofNullable(entity.getPriority());
		String priority = priorityOpt.orElse(null);
		bean.setPriority(priority);
		Optional<Glossary> glossaryOpt = Optional.ofNullable(entity.getGlossary());
		Glossary glossary = glossaryOpt.orElse(new Glossary());
		GlossariesRecord glossaryR = create.newRecord(GLOSSARIES, glossary);
		glossaryR.store();
		bean.setGlossary(glossaryR.get_Id());
		Optional<Link> referenceOpt = Optional.ofNullable(entity.getReference());
		Link reference = referenceOpt.orElse(new Link());
		LinksRecord referenceR = create.newRecord(LINKS, reference);
		referenceR.store();
		bean.setReference(referenceR.get_Id());
		
		return bean;
	}
	
	public static EanBean entity2Bean(DSLContext create, Ean entity) {
		EanBean bean = new EanBean();
		bean.setId(entity.getId());
		Optional<String> codeOpt = Optional.ofNullable(entity.getCode());
		String code = codeOpt.orElse(null);
		bean.setCode(code);
		Optional<String> eciRefOpt = Optional.ofNullable(entity.getEciRef());
		String eciRef = eciRefOpt.orElse(null);
		bean.setEciRef(eciRef);
		Optional<String[]> eciRefListOpt = Optional.ofNullable(entity.getEciRefList());
		String[] eciRefList = eciRefListOpt.orElse(null);
		bean.setEciRefList(eciRefList);
		Optional<String> webVariantCodeOpt = Optional.ofNullable(entity.getWebVariantCode());
		String webVariantCode = webVariantCodeOpt.orElse(null);
		bean.setWebVariantCode(webVariantCode);
		Optional<String> internalProviderCodeOpt = Optional.ofNullable(entity.getInternalProviderCode());
		String internalProviderCode = internalProviderCodeOpt.orElse(null);
		bean.setInternalProviderCode(internalProviderCode);
		Optional<String> referenceTypeOpt = Optional.ofNullable(entity.getReferenceType());
		String referenceType = referenceTypeOpt.orElse(null);
		bean.setReferenceType(referenceType);
		Optional<String> productTypeIdOpt = Optional.ofNullable(entity.getProductTypeId());
		String productTypeId = productTypeIdOpt.orElse(null);
		bean.setProductTypeId(productTypeId);
		Optional<String[]> parentCategoriesOpt = Optional.ofNullable(entity.getParentCategories());
		String[] parentCategories = parentCategoriesOpt.orElse(null);
		bean.setParentCategories(parentCategories);
		Optional<String> lastDateOpt = Optional.ofNullable(entity.getLastDate());
		String lastDate = lastDateOpt.orElse(null);
		bean.setLastDate(lastDate);
		Optional<String> unpublishDateOpt = Optional.ofNullable(entity.getUnpublishDate());
		String unpublishDate = unpublishDateOpt.orElse(null);
		bean.setUnpublishDate(unpublishDate);
		
		return bean;
	}
	
}
