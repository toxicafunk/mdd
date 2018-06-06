package graphql

import argonaut.Argonaut
import argonaut.Argonaut.CanBuildFromDecodeJson
import argonaut.Argonaut.OptionDecodeJson
import argonaut.Argonaut.OptionEncodeJson
import argonaut.Argonaut.StringDecodeJson
import argonaut.Argonaut.StringEncodeJson
import argonaut.Argonaut.StringToParseWrap
import argonaut.Argonaut.StringToStringWrap
import argonaut.Argonaut.TraversableOnceEncodeJson
import argonaut.Argonaut.jEmptyObject
import argonaut.CodecJson
import argonaut.DecodeJson
import argonaut.EncodeJson

	
case class Map (
	id: Int
	, key: String
	, value: String
)

object Map {
  implicit def MapCodecJson: CodecJson[Map ] =
    Argonaut.casecodec3(Map .apply, Map .unapply)(
    	"id"
    	,"key"
    	,"value"
    )
}
	
case class Link (
	id: Int
	, idExt: String
	, hierarchyId: String
	, categoryId: String
)

object Link {
  implicit def LinkCodecJson: CodecJson[Link ] =
    Argonaut.casecodec4(Link .apply, Link .unapply)(
    	"id"
    	,"idExt"
    	,"hierarchyId"
    	,"categoryId"
    )
}
	
case class GlossaryValue (
	id: Int
	, code: String
	, term: String
	, normalizedTerm: String
	, externalCode: String
	, description: String
)

object GlossaryValue {
  implicit def GlossaryValueCodecJson: CodecJson[GlossaryValue ] =
    Argonaut.casecodec6(GlossaryValue .apply, GlossaryValue .unapply)(
    	"id"
    	,"code"
    	,"term"
    	,"normalizedTerm"
    	,"externalCode"
    	,"description"
    )
}
	
case class Brand (
	id: Int
	, name: String
	, code: String
	, atgCode: String
	, description: String
	, displayBrand: Boolean
)

object Brand {
  implicit def BrandCodecJson: CodecJson[Brand ] =
    Argonaut.casecodec6(Brand .apply, Brand .unapply)(
    	"id"
    	,"name"
    	,"code"
    	,"atgCode"
    	,"description"
    	,"displayBrand"
    )
}
	
case class Category (
	id: Int
	, code: String
	, atgCode: String
	, parentCategory: Int
	, status: String
	, descriptions: List[Map]
	, fullDescriptions: List[Map]
	, startDate: String
	, endDate: String
	, invalidationDate: String
	, reference: Link
	, noveltyDays: Double
	, children: List[Int]
)

object Category {
  implicit def AttribDecodeJson: DecodeJson[Category] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Integer]
		code <- (c --\ "code").as[String]
		atgCode <- (c --\ "atgCode").as[String]
		parentCategory <- (c --\ "parentCategory").as[Int]
		status <- (c --\ "status").as[String]
		descriptions <- (c --\ "descriptions").as[List[Map]]
		fullDescriptions <- (c --\ "fullDescriptions").as[List[Map]]
		startDate <- (c --\ "startDate").as[String]
		endDate <- (c --\ "endDate").as[String]
		invalidationDate <- (c --\ "invalidationDate").as[String]
		reference <- (c --\ "reference").as[Link]
		noveltyDays <- (c --\ "noveltyDays").as[Double]
		children <- (c --\ "children").as[List[Int]]
    } yield Category(
    	id
		, code
		, atgCode
		, parentCategory
		, status
		, descriptions
		, fullDescriptions
		, startDate
		, endDate
		, invalidationDate
		, reference
		, noveltyDays
		, children
    	)
    )

  implicit def CategoryEncodeJson: EncodeJson[Category] =
    EncodeJson( (att: Category) =>
      ("id" := att.id) ->:
    	("code" := att.code) ->:
    	("atgCode" := att.atgCode) ->:
    	("parentCategory" := att.parentCategory) ->:
    	("status" := att.status) ->:
    	("descriptions" := att.descriptions) ->:
    	("fullDescriptions" := att.fullDescriptions) ->:
    	("startDate" := att.startDate) ->:
    	("endDate" := att.endDate) ->:
    	("invalidationDate" := att.invalidationDate) ->:
    	("reference" := att.reference) ->:
    	("noveltyDays" := att.noveltyDays) ->:
    	("children" := att.children) ->:
    jEmptyObject)
}
	
case class Image (
	id: Int
	, url: String
	, description: String
)

object Image {
  implicit def ImageCodecJson: CodecJson[Image ] =
    Argonaut.casecodec3(Image .apply, Image .unapply)(
    	"id"
    	,"url"
    	,"description"
    )
}
	
case class Video (
	id: Int
	, title: String
	, description: String
	, url: String
)

object Video {
  implicit def VideoCodecJson: CodecJson[Video ] =
    Argonaut.casecodec4(Video .apply, Video .unapply)(
    	"id"
    	,"title"
    	,"description"
    	,"url"
    )
}
	
case class ProductType (
	id: Int
	, code: String
	, atgCode: String
	, descriptions: List[Map]
	, parents: List[String]
	, isMarketplace: Boolean
	, categoryListCode: String
	, categoryListLabel: String
	, categoryCodes: List[Category]
	, importId: String
)

object ProductType {
  implicit def ProductTypeCodecJson: CodecJson[ProductType ] =
    Argonaut.casecodec10(ProductType .apply, ProductType .unapply)(
    	"id"
    	,"code"
    	,"atgCode"
    	,"descriptions"
    	,"parents"
    	,"isMarketplace"
    	,"categoryListCode"
    	,"categoryListLabel"
    	,"categoryCodes"
    	,"importId"
    )
}
	
case class Item (
	id: Int
	, ean: String
	, providerRef: String
	, startDate: String
	, name: String
	, description: String
	, tags: List[String]
	, extendedDescription: String
	, weight: Double
	, weightUnit: String
	, photos: List[String]
	, supplementaryItems: List[Int]
	, soldSeparately: Boolean
	, productId: String
	, productType: List[String]
	, regulations: List[String]
	, securityInfo: List[String]
	, category: Category
	, keywords: List[String]
	, image: String
	, secondaryImages: List[String]
	, videos: List[String]
	, subType: String
	, style: String
	, washingInstructions: String
	, serviceDate: String
	, serviceComments: String
	, brand: Brand
	, defaultCategories: List[Category]
	, additionalInformation: String
	, mainProduct: Int
	, color: String
	, color1: String
	, color2: String
	, size: String
)

object Item {
  implicit def AttribDecodeJson: DecodeJson[Item] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Integer]
		ean <- (c --\ "ean").as[String]
		providerRef <- (c --\ "providerRef").as[String]
		startDate <- (c --\ "startDate").as[String]
		name <- (c --\ "name").as[String]
		description <- (c --\ "description").as[String]
		tags <- (c --\ "tags").as[List[String]]
		extendedDescription <- (c --\ "extendedDescription").as[String]
		weight <- (c --\ "weight").as[Double]
		weightUnit <- (c --\ "weightUnit").as[String]
		photos <- (c --\ "photos").as[List[String]]
		supplementaryItems <- (c --\ "supplementaryItems").as[List[Int]]
		soldSeparately <- (c --\ "soldSeparately").as[Boolean]
		productId <- (c --\ "productId").as[String]
		productType <- (c --\ "productType").as[List[String]]
		regulations <- (c --\ "regulations").as[List[String]]
		securityInfo <- (c --\ "securityInfo").as[List[String]]
		category <- (c --\ "category").as[Category]
		keywords <- (c --\ "keywords").as[List[String]]
		image <- (c --\ "image").as[String]
		secondaryImages <- (c --\ "secondaryImages").as[List[String]]
		videos <- (c --\ "videos").as[List[String]]
		subType <- (c --\ "subType").as[String]
		style <- (c --\ "style").as[String]
		washingInstructions <- (c --\ "washingInstructions").as[String]
		serviceDate <- (c --\ "serviceDate").as[String]
		serviceComments <- (c --\ "serviceComments").as[String]
		brand <- (c --\ "brand").as[Brand]
		defaultCategories <- (c --\ "defaultCategories").as[List[Category]]
		additionalInformation <- (c --\ "additionalInformation").as[String]
		mainProduct <- (c --\ "mainProduct").as[Int]
		color <- (c --\ "color").as[String]
		color1 <- (c --\ "color1").as[String]
		color2 <- (c --\ "color2").as[String]
		size <- (c --\ "size").as[String]
    } yield Item(
    	id
		, ean
		, providerRef
		, startDate
		, name
		, description
		, tags
		, extendedDescription
		, weight
		, weightUnit
		, photos
		, supplementaryItems
		, soldSeparately
		, productId
		, productType
		, regulations
		, securityInfo
		, category
		, keywords
		, image
		, secondaryImages
		, videos
		, subType
		, style
		, washingInstructions
		, serviceDate
		, serviceComments
		, brand
		, defaultCategories
		, additionalInformation
		, mainProduct
		, color
		, color1
		, color2
		, size
    	)
    )

  implicit def ItemEncodeJson: EncodeJson[Item] =
    EncodeJson( (att: Item) =>
      ("id" := att.id) ->:
    	("ean" := att.ean) ->:
    	("providerRef" := att.providerRef) ->:
    	("startDate" := att.startDate) ->:
    	("name" := att.name) ->:
    	("description" := att.description) ->:
    	("tags" := att.tags) ->:
    	("extendedDescription" := att.extendedDescription) ->:
    	("weight" := att.weight) ->:
    	("weightUnit" := att.weightUnit) ->:
    	("photos" := att.photos) ->:
    	("supplementaryItems" := att.supplementaryItems) ->:
    	("soldSeparately" := att.soldSeparately) ->:
    	("productId" := att.productId) ->:
    	("productType" := att.productType) ->:
    	("regulations" := att.regulations) ->:
    	("securityInfo" := att.securityInfo) ->:
    	("category" := att.category) ->:
    	("keywords" := att.keywords) ->:
    	("image" := att.image) ->:
    	("secondaryImages" := att.secondaryImages) ->:
    	("videos" := att.videos) ->:
    	("subType" := att.subType) ->:
    	("style" := att.style) ->:
    	("washingInstructions" := att.washingInstructions) ->:
    	("serviceDate" := att.serviceDate) ->:
    	("serviceComments" := att.serviceComments) ->:
    	("brand" := att.brand) ->:
    	("defaultCategories" := att.defaultCategories) ->:
    	("additionalInformation" := att.additionalInformation) ->:
    	("mainProduct" := att.mainProduct) ->:
    	("color" := att.color) ->:
    	("color1" := att.color1) ->:
    	("color2" := att.color2) ->:
    	("size" := att.size) ->:
    jEmptyObject)
}
	
case class GenderTarget (
	id: Int
	, code: String
	, label: String
)

object GenderTarget {
  implicit def GenderTargetCodecJson: CodecJson[GenderTarget ] =
    Argonaut.casecodec3(GenderTarget .apply, GenderTarget .unapply)(
    	"id"
    	,"code"
    	,"label"
    )
}
	
case class RelatedItem (
	id: Int
	, items: List[Item]
)

object RelatedItem {
  implicit def RelatedItemCodecJson: CodecJson[RelatedItem ] =
    Argonaut.casecodec2(RelatedItem .apply, RelatedItem .unapply)(
    	"id"
    	,"items"
    )
}
	
case class Supplier (
	id: Int
	, code: String
	, name: String
)

object Supplier {
  implicit def SupplierCodecJson: CodecJson[Supplier ] =
    Argonaut.casecodec3(Supplier .apply, Supplier .unapply)(
    	"id"
    	,"code"
    	,"name"
    )
}
	
case class SupplierItem (
	id: Int
	, supplier: Supplier
	, item: Item
	, price: Double
	, stock: Double
	, activeFrom: String
	, activeTo: String
)

object SupplierItem {
  implicit def SupplierItemCodecJson: CodecJson[SupplierItem ] =
    Argonaut.casecodec7(SupplierItem .apply, SupplierItem .unapply)(
    	"id"
    	,"supplier"
    	,"item"
    	,"price"
    	,"stock"
    	,"activeFrom"
    	,"activeTo"
    )
}
	
case class Glossary (
	id: Int
	, values: List[GlossaryValue]
	, reference: Link
)

object Glossary {
  implicit def GlossaryCodecJson: CodecJson[Glossary ] =
    Argonaut.casecodec3(Glossary .apply, Glossary .unapply)(
    	"id"
    	,"values"
    	,"reference"
    )
}
	
case class Lkup (
	id: Int
	, code: String
	, name: String
	, lookupType: String
	, descriptions: Map
	, atgCode: String
	, externalCode: String
	, logo: String
	, audit: String
	, priority: String
	, glossary: Glossary
	, reference: Link
)

object Lkup {
  implicit def LkupCodecJson: CodecJson[Lkup ] =
    Argonaut.casecodec12(Lkup .apply, Lkup .unapply)(
    	"id"
    	,"code"
    	,"name"
    	,"lookupType"
    	,"descriptions"
    	,"atgCode"
    	,"externalCode"
    	,"logo"
    	,"audit"
    	,"priority"
    	,"glossary"
    	,"reference"
    )
}
	
case class Ean (
	id: Int
	, code: String
	, eciRef: String
	, eciRefList: List[String]
	, webVariantCode: String
	, internalProviderCode: String
	, referenceType: String
	, productTypeId: String
	, parentCategories: List[String]
	, lastDate: String
	, unpublishDate: String
)

object Ean {
  implicit def EanCodecJson: CodecJson[Ean ] =
    Argonaut.casecodec11(Ean .apply, Ean .unapply)(
    	"id"
    	,"code"
    	,"eciRef"
    	,"eciRefList"
    	,"webVariantCode"
    	,"internalProviderCode"
    	,"referenceType"
    	,"productTypeId"
    	,"parentCategories"
    	,"lastDate"
    	,"unpublishDate"
    )
}
