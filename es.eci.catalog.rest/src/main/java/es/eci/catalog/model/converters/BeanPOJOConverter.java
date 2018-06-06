
package es.eci.catalog.model.converters;

import static es.eci.catalog.model.Tables.*;

import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;

import es.eci.catalog.model.pojos.*;
import es.eci.catalog.model.pojos.db.*;
import es.eci.catalog.model.tables.records.*;
import es.eci.catalog.model.tables.records.SuppliersRecord;

public class BeanPOJOConverter {

  public static Map toMap(DSLContext create, MapBean bean) {
		Map entity = new Map();
		
		entity.setId(bean.getId());
		entity.setKey(bean.getKey());
		entity.setValue(bean.getValue());
		
		return entity;
	}
	
  public static Link toLink(DSLContext create, LinkBean bean) {
		Link entity = new Link();
		
		entity.setId(bean.getId());
		entity.setIdExt(bean.getIdExt());
		entity.setHierarchyId(bean.getHierarchyId());
		entity.setCategoryId(bean.getCategoryId());
		
		return entity;
	}
	
  public static GlossaryValue toGlossaryValue(DSLContext create, GlossaryValueBean bean) {
		GlossaryValue entity = new GlossaryValue();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setTerm(bean.getTerm());
		entity.setNormalizedTerm(bean.getNormalizedTerm());
		entity.setExternalCode(bean.getExternalCode());
		entity.setDescription(bean.getDescription());
		
		return entity;
	}
	
  public static Brand toBrand(DSLContext create, BrandBean bean) {
		Brand entity = new Brand();
		
		entity.setId(bean.getId());
		entity.setName(bean.getName());
		entity.setCode(bean.getCode());
		entity.setAtgCode(bean.getAtgCode());
		entity.setDescription(bean.getDescription());
		entity.setDisplayBrand(bean.getDisplayBrand());
		
		return entity;
	}
	
  public static Category toCategory(DSLContext create, CategoryBean bean) {
		Category entity = new Category();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setAtgCode(bean.getAtgCode());
		CategoriesRecord parentCategoryRecord = create.fetchOne(CATEGORIES, CATEGORIES._ID.eq(bean.getParentCategory()));
		if (parentCategoryRecord != null) {
			CategoryBean parentCategory = new CategoryBean();
			parentCategoryRecord.into(parentCategory);
			entity.setParentCategory(toCategory(create, parentCategory));
		}
		entity.setStatus(bean.getStatus());
		List<MapBean> descriptionsBeans = create.select().from(MAPS).where(MAPS._ID.in(bean.getDescriptions())).fetchInto(MapBean.class);
		List<Map> descriptions = descriptionsBeans.stream().map(b -> toMap(create, b)).collect(Collectors.toList());
		entity.setDescriptions(descriptions);
		List<MapBean> fullDescriptionsBeans = create.select().from(MAPS).where(MAPS._ID.in(bean.getFullDescriptions())).fetchInto(MapBean.class);
		List<Map> fullDescriptions = fullDescriptionsBeans.stream().map(b -> toMap(create, b)).collect(Collectors.toList());
		entity.setFullDescriptions(fullDescriptions);
		entity.setStartDate(bean.getStartDate());
		entity.setEndDate(bean.getEndDate());
		entity.setInvalidationDate(bean.getInvalidationDate());
		LinksRecord referenceRecord = create.fetchOne(LINKS, LINKS._ID.eq(bean.getReference()));
		if (referenceRecord != null) {
			LinkBean reference = new LinkBean();
			referenceRecord.into(reference);
			entity.setReference(toLink(create, reference));
		}
		entity.setNoveltyDays(bean.getNoveltyDays());
		List<CategoryBean> childrenBeans = create.select().from(CATEGORIES).where(CATEGORIES._ID.in(bean.getChildren())).fetchInto(CategoryBean.class);
		List<Category> children = childrenBeans.stream().map(b -> toCategory(create, b)).collect(Collectors.toList());
		entity.setChildren(children);
		
		return entity;
	}
	
  public static Image toImage(DSLContext create, ImageBean bean) {
		Image entity = new Image();
		
		entity.setId(bean.getId());
		entity.setUrl(bean.getUrl());
		entity.setDescription(bean.getDescription());
		
		return entity;
	}
	
  public static Video toVideo(DSLContext create, VideoBean bean) {
		Video entity = new Video();
		
		entity.setId(bean.getId());
		entity.setTitle(bean.getTitle());
		entity.setDescription(bean.getDescription());
		entity.setUrl(bean.getUrl());
		
		return entity;
	}
	
  public static ProductType toProductType(DSLContext create, ProductTypeBean bean) {
		ProductType entity = new ProductType();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setAtgCode(bean.getAtgCode());
		List<MapBean> descriptionsBeans = create.select().from(MAPS).where(MAPS._ID.in(bean.getDescriptions())).fetchInto(MapBean.class);
		List<Map> descriptions = descriptionsBeans.stream().map(b -> toMap(create, b)).collect(Collectors.toList());
		entity.setDescriptions(descriptions);
		entity.setParents(bean.getParents());
		entity.setIsMarketplace(bean.getIsMarketplace());
		entity.setCategoryListCode(bean.getCategoryListCode());
		entity.setCategoryListLabel(bean.getCategoryListLabel());
		List<CategoryBean> categoryCodesBeans = create.select().from(CATEGORIES).where(CATEGORIES._ID.in(bean.getCategoryCodes())).fetchInto(CategoryBean.class);
		List<Category> categoryCodes = categoryCodesBeans.stream().map(b -> toCategory(create, b)).collect(Collectors.toList());
		entity.setCategoryCodes(categoryCodes);
		entity.setImportId(bean.getImportId());
		
		return entity;
	}
	
  public static Item toItem(DSLContext create, ItemBean bean) {
		Item entity = new Item();
		
		entity.setId(bean.getId());
		entity.setEan(bean.getEan());
		entity.setProviderRef(bean.getProviderRef());
		entity.setStartDate(bean.getStartDate());
		entity.setName(bean.getName());
		entity.setDescription(bean.getDescription());
		entity.setTags(bean.getTags());
		entity.setExtendedDescription(bean.getExtendedDescription());
		entity.setWeight(bean.getWeight());
		entity.setWeightUnit(bean.getWeightUnit());
		entity.setPhotos(bean.getPhotos());
		List<ItemBean> supplementaryItemsBeans = create.select().from(ITEMS).where(ITEMS._ID.in(bean.getSupplementaryItems())).fetchInto(ItemBean.class);
		List<Item> supplementaryItems = supplementaryItemsBeans.stream().map(b -> toItem(create, b)).collect(Collectors.toList());
		entity.setSupplementaryItems(supplementaryItems);
		entity.setSoldSeparately(bean.getSoldSeparately());
		entity.setProductId(bean.getProductId());
		entity.setProductType(bean.getProductType());
		entity.setRegulations(bean.getRegulations());
		entity.setSecurityInfo(bean.getSecurityInfo());
		CategoriesRecord categoryRecord = create.fetchOne(CATEGORIES, CATEGORIES._ID.eq(bean.getCategory()));
		if (categoryRecord != null) {
			CategoryBean category = new CategoryBean();
			categoryRecord.into(category);
			entity.setCategory(toCategory(create, category));
		}
		entity.setKeywords(bean.getKeywords());
		entity.setImage(bean.getImage());
		entity.setSecondaryImages(bean.getSecondaryImages());
		entity.setVideos(bean.getVideos());
		entity.setSubType(bean.getSubType());
		entity.setStyle(bean.getStyle());
		entity.setWashingInstructions(bean.getWashingInstructions());
		entity.setServiceDate(bean.getServiceDate());
		entity.setServiceComments(bean.getServiceComments());
		BrandsRecord brandRecord = create.fetchOne(BRANDS, BRANDS._ID.eq(bean.getBrand()));
		if (brandRecord != null) {
			BrandBean brand = new BrandBean();
			brandRecord.into(brand);
			entity.setBrand(toBrand(create, brand));
		}
		List<CategoryBean> defaultCategoriesBeans = create.select().from(CATEGORIES).where(CATEGORIES._ID.in(bean.getDefaultCategories())).fetchInto(CategoryBean.class);
		List<Category> defaultCategories = defaultCategoriesBeans.stream().map(b -> toCategory(create, b)).collect(Collectors.toList());
		entity.setDefaultCategories(defaultCategories);
		entity.setAdditionalInformation(bean.getAdditionalInformation());
		ItemsRecord mainProductRecord = create.fetchOne(ITEMS, ITEMS._ID.eq(bean.getMainProduct()));
		if (mainProductRecord != null) {
			ItemBean mainProduct = new ItemBean();
			mainProductRecord.into(mainProduct);
			entity.setMainProduct(toItem(create, mainProduct));
		}
		entity.setColor(bean.getColor());
		entity.setColor1(bean.getColor1());
		entity.setColor2(bean.getColor2());
		entity.setSize(bean.getSize());
		
		return entity;
	}
	
  public static GenderTarget toGenderTarget(DSLContext create, GenderTargetBean bean) {
		GenderTarget entity = new GenderTarget();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setLabel(bean.getLabel());
		
		return entity;
	}
	
  public static RelatedItem toRelatedItem(DSLContext create, RelatedItemBean bean) {
		RelatedItem entity = new RelatedItem();
		
		entity.setId(bean.getId());
		List<ItemBean> itemsBeans = create.select().from(ITEMS).where(ITEMS._ID.in(bean.getItems())).fetchInto(ItemBean.class);
		List<Item> items = itemsBeans.stream().map(b -> toItem(create, b)).collect(Collectors.toList());
		entity.setItems(items);
		
		return entity;
	}
	
  public static Supplier toSupplier(DSLContext create, SupplierBean bean) {
		Supplier entity = new Supplier();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setName(bean.getName());
		
		return entity;
	}
	
  public static SupplierItem toSupplierItem(DSLContext create, SupplierItemBean bean) {
		SupplierItem entity = new SupplierItem();
		
		entity.setId(bean.getId());
		SuppliersRecord supplierRecord = create.fetchOne(SUPPLIERS, SUPPLIERS._ID.eq(bean.getSupplier()));
		if (supplierRecord != null) {
			SupplierBean supplier = new SupplierBean();
			supplierRecord.into(supplier);
			entity.setSupplier(toSupplier(create, supplier));
		}
		ItemsRecord itemRecord = create.fetchOne(ITEMS, ITEMS._ID.eq(bean.getItem()));
		if (itemRecord != null) {
			ItemBean item = new ItemBean();
			itemRecord.into(item);
			entity.setItem(toItem(create, item));
		}
		entity.setPrice(bean.getPrice());
		entity.setStock(bean.getStock());
		entity.setActiveFrom(bean.getActiveFrom());
		entity.setActiveTo(bean.getActiveTo());
		
		return entity;
	}
	
  public static Glossary toGlossary(DSLContext create, GlossaryBean bean) {
		Glossary entity = new Glossary();
		
		entity.setId(bean.getId());
		List<GlossaryValueBean> valuesBeans = create.select().from(GLOSSARY_VALUES).where(GLOSSARY_VALUES._ID.in(bean.getValues())).fetchInto(GlossaryValueBean.class);
		List<GlossaryValue> values = valuesBeans.stream().map(b -> toGlossaryValue(create, b)).collect(Collectors.toList());
		entity.setValues(values);
		LinksRecord referenceRecord = create.fetchOne(LINKS, LINKS._ID.eq(bean.getReference()));
		if (referenceRecord != null) {
			LinkBean reference = new LinkBean();
			referenceRecord.into(reference);
			entity.setReference(toLink(create, reference));
		}
		
		return entity;
	}
	
  public static Lkup toLkup(DSLContext create, LkupBean bean) {
		Lkup entity = new Lkup();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setName(bean.getName());
		entity.setLookupType(bean.getLookupType());
		MapsRecord descriptionsRecord = create.fetchOne(MAPS, MAPS._ID.eq(bean.getDescriptions()));
		if (descriptionsRecord != null) {
			MapBean descriptions = new MapBean();
			descriptionsRecord.into(descriptions);
			entity.setDescriptions(toMap(create, descriptions));
		}
		entity.setAtgCode(bean.getAtgCode());
		entity.setExternalCode(bean.getExternalCode());
		entity.setLogo(bean.getLogo());
		entity.setAudit(bean.getAudit());
		entity.setPriority(bean.getPriority());
		GlossariesRecord glossaryRecord = create.fetchOne(GLOSSARIES, GLOSSARIES._ID.eq(bean.getGlossary()));
		if (glossaryRecord != null) {
			GlossaryBean glossary = new GlossaryBean();
			glossaryRecord.into(glossary);
			entity.setGlossary(toGlossary(create, glossary));
		}
		LinksRecord referenceRecord = create.fetchOne(LINKS, LINKS._ID.eq(bean.getReference()));
		if (referenceRecord != null) {
			LinkBean reference = new LinkBean();
			referenceRecord.into(reference);
			entity.setReference(toLink(create, reference));
		}
		
		return entity;
	}
	
  public static Ean toEan(DSLContext create, EanBean bean) {
		Ean entity = new Ean();
		
		entity.setId(bean.getId());
		entity.setCode(bean.getCode());
		entity.setEciRef(bean.getEciRef());
		entity.setEciRefList(bean.getEciRefList());
		entity.setWebVariantCode(bean.getWebVariantCode());
		entity.setInternalProviderCode(bean.getInternalProviderCode());
		entity.setReferenceType(bean.getReferenceType());
		entity.setProductTypeId(bean.getProductTypeId());
		entity.setParentCategories(bean.getParentCategories());
		entity.setLastDate(bean.getLastDate());
		entity.setUnpublishDate(bean.getUnpublishDate());
		
		return entity;
	}
	
}
