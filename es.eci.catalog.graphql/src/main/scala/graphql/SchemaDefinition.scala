package graphql


import argonaut._, Argonaut._

import sangria.macros.derive._
import sangria.schema._

import sangria.marshalling._
import sangria.marshalling.argonaut._
import sangria.execution.deferred.DeferredResolver
import sangria.ast.Document
import sangria.execution.ErrorWithResolver
import sangria.execution.Executor
import sangria.execution.QueryAnalysisError
import sangria.parser._

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerResponse

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object SchemaDefinition {
	
	  
  val MapType = ObjectType(
    "Map",
    "",

	fields[Unit, Map](
	  Field("id", IntType, 
      	description = Some("Id of Map"),
      	resolve = _.value.id)
	, Field("key", StringType,
        description = Some(""),
        resolve = _.value.key)
	, Field("value", StringType,
        description = Some(""),
        resolve = _.value.value)
    )
  )
	
	  
  val LinkType = ObjectType(
    "Link",
    "",

	fields[Unit, Link](
	  Field("id", IntType, 
      	description = Some("Id of Link"),
      	resolve = _.value.id)
	, Field("idExt", StringType,
        description = Some(""),
        resolve = _.value.idExt)
	, Field("hierarchyId", StringType,
        description = Some(""),
        resolve = _.value.hierarchyId)
	, Field("categoryId", StringType,
        description = Some(""),
        resolve = _.value.categoryId)
    )
  )
	
	  
  val GlossaryValueType = ObjectType(
    "GlossaryValue",
    "",

	fields[Unit, GlossaryValue](
	  Field("id", IntType, 
      	description = Some("Id of GlossaryValue"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("term", StringType,
        description = Some(""),
        resolve = _.value.term)
	, Field("normalizedTerm", StringType,
        description = Some(""),
        resolve = _.value.normalizedTerm)
	, Field("externalCode", StringType,
        description = Some(""),
        resolve = _.value.externalCode)
	, Field("description", StringType,
        description = Some(""),
        resolve = _.value.description)
    )
  )
	
	  
  val BrandType = ObjectType(
    "Brand",
    "",

	fields[Unit, Brand](
	  Field("id", IntType, 
      	description = Some("Id of Brand"),
      	resolve = _.value.id)
	, Field("name", StringType,
        description = Some(""),
        resolve = _.value.name)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("atgCode", StringType,
        description = Some(""),
        resolve = _.value.atgCode)
	, Field("description", StringType,
        description = Some(""),
        resolve = _.value.description)
	, Field("displayBrand", BooleanType,
        description = Some(""),
        resolve = _.value.displayBrand)
    )
  )
	
	  
  val CategoryType = ObjectType(
    "Category",
    "The product Category",

	fields[Unit, Category](
	  Field("id", IntType, 
      	description = Some("Id of Category"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("atgCode", StringType,
        description = Some(""),
        resolve = _.value.atgCode)
	, Field("parentCategory", IntType,
        description = Some(""),
        resolve = _.value.parentCategory)
	, Field("status", StringType,
        description = Some(""),
        resolve = _.value.status)
	, Field("descriptions", ListType(MapType),
        description = Some(""),
        resolve = _.value.descriptions)
	, Field("fullDescriptions", ListType(MapType),
        description = Some(""),
        resolve = _.value.fullDescriptions)
	, Field("startDate", StringType,
        description = Some(""),
        resolve = _.value.startDate)
	, Field("endDate", StringType,
        description = Some(""),
        resolve = _.value.endDate)
	, Field("invalidationDate", StringType,
        description = Some(""),
        resolve = _.value.invalidationDate)
	, Field("reference", LinkType,
        description = Some(""),
        resolve = _.value.reference)
	, Field("noveltyDays", FloatType,
        description = Some(""),
        resolve = _.value.noveltyDays)
	, Field("children", ListType(IntType),
        description = Some(""),
        resolve = _.value.children)
    )
  )
	
	  
  val ImageType = ObjectType(
    "Image",
    "",

	fields[Unit, Image](
	  Field("id", IntType, 
      	description = Some("Id of Image"),
      	resolve = _.value.id)
	, Field("url", StringType,
        description = Some(""),
        resolve = _.value.url)
	, Field("description", StringType,
        description = Some(""),
        resolve = _.value.description)
    )
  )
	
	  
  val VideoType = ObjectType(
    "Video",
    "",

	fields[Unit, Video](
	  Field("id", IntType, 
      	description = Some("Id of Video"),
      	resolve = _.value.id)
	, Field("title", StringType,
        description = Some(""),
        resolve = _.value.title)
	, Field("description", StringType,
        description = Some(""),
        resolve = _.value.description)
	, Field("url", StringType,
        description = Some(""),
        resolve = _.value.url)
    )
  )
	
	  
  val ProductTypeType = ObjectType(
    "ProductType",
    "",

	fields[Unit, ProductType](
	  Field("id", IntType, 
      	description = Some("Id of ProductType"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("atgCode", StringType,
        description = Some(""),
        resolve = _.value.atgCode)
	, Field("descriptions", ListType(MapType),
        description = Some(""),
        resolve = _.value.descriptions)
	, Field("parents", ListType(StringType),
        description = Some(""),
        resolve = _.value.parents)
	, Field("isMarketplace", BooleanType,
        description = Some(""),
        resolve = _.value.isMarketplace)
	, Field("categoryListCode", StringType,
        description = Some(""),
        resolve = _.value.categoryListCode)
	, Field("categoryListLabel", StringType,
        description = Some(""),
        resolve = _.value.categoryListLabel)
	, Field("categoryCodes", ListType(CategoryType),
        description = Some(""),
        resolve = _.value.categoryCodes)
	, Field("importId", StringType,
        description = Some(""),
        resolve = _.value.importId)
    )
  )
	
	  
  val ItemType = ObjectType(
    "Item",
    "Ítems facturables al cliente. Ejemplos de ítems pueden ser los productos, los servicios, ...",

	fields[Unit, Item](
	  Field("id", IntType, 
      	description = Some("Id of Item"),
      	resolve = _.value.id)
	, Field("ean", StringType,
        description = Some(""),
        resolve = _.value.ean)
	, Field("providerRef", StringType,
        description = Some(""),
        resolve = _.value.providerRef)
	, Field("startDate", StringType,
        description = Some(""),
        resolve = _.value.startDate)
	, Field("name", StringType,
        description = Some(""),
        resolve = _.value.name)
	, Field("description", StringType,
        description = Some(""),
        resolve = _.value.description)
	, Field("tags", ListType(StringType),
        description = Some(""),
        resolve = _.value.tags)
	, Field("extendedDescription", StringType,
        description = Some(""),
        resolve = _.value.extendedDescription)
	, Field("weight", FloatType,
        description = Some(""),
        resolve = _.value.weight)
	, Field("weightUnit", StringType,
        description = Some(""),
        resolve = _.value.weightUnit)
	, Field("photos", ListType(StringType),
        description = Some(""),
        resolve = _.value.photos)
	, Field("supplementaryItems", ListType(IntType),
        description = Some(""),
        resolve = _.value.supplementaryItems)
	, Field("soldSeparately", BooleanType,
        description = Some("It indicates if this item is sold separately or must be acquired as an additional optional item when another item is bought."),
        resolve = _.value.soldSeparately)
	, Field("productId", StringType,
        description = Some(""),
        resolve = _.value.productId)
	, Field("productType", ListType(StringType),
        description = Some(""),
        resolve = _.value.productType)
	, Field("regulations", ListType(StringType),
        description = Some(""),
        resolve = _.value.regulations)
	, Field("securityInfo", ListType(StringType),
        description = Some(""),
        resolve = _.value.securityInfo)
	, Field("category", CategoryType,
        description = Some(""),
        resolve = _.value.category)
	, Field("keywords", ListType(StringType),
        description = Some(""),
        resolve = _.value.keywords)
	, Field("image", StringType,
        description = Some(""),
        resolve = _.value.image)
	, Field("secondaryImages", ListType(StringType),
        description = Some(""),
        resolve = _.value.secondaryImages)
	, Field("videos", ListType(StringType),
        description = Some(""),
        resolve = _.value.videos)
	, Field("subType", StringType,
        description = Some(""),
        resolve = _.value.subType)
	, Field("style", StringType,
        description = Some(""),
        resolve = _.value.style)
	, Field("washingInstructions", StringType,
        description = Some(""),
        resolve = _.value.washingInstructions)
	, Field("serviceDate", StringType,
        description = Some(""),
        resolve = _.value.serviceDate)
	, Field("serviceComments", StringType,
        description = Some(""),
        resolve = _.value.serviceComments)
	, Field("brand", BrandType,
        description = Some(""),
        resolve = _.value.brand)
	, Field("defaultCategories", ListType(CategoryType),
        description = Some(""),
        resolve = _.value.defaultCategories)
	, Field("additionalInformation", StringType,
        description = Some(""),
        resolve = _.value.additionalInformation)
	, Field("mainProduct", IntType,
        description = Some(""),
        resolve = _.value.mainProduct)
	, Field("color", StringType,
        description = Some(""),
        resolve = _.value.color)
	, Field("color1", StringType,
        description = Some(""),
        resolve = _.value.color1)
	, Field("color2", StringType,
        description = Some(""),
        resolve = _.value.color2)
	, Field("size", StringType,
        description = Some(""),
        resolve = _.value.size)
    )
  )
	
	  
  val GenderTargetType = ObjectType(
    "GenderTarget",
    "",

	fields[Unit, GenderTarget](
	  Field("id", IntType, 
      	description = Some("Id of GenderTarget"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("label", StringType,
        description = Some(""),
        resolve = _.value.label)
    )
  )
	
	  
  val RelatedItemType = ObjectType(
    "RelatedItem",
    "",

	fields[Unit, RelatedItem](
	  Field("id", IntType, 
      	description = Some("Id of RelatedItem"),
      	resolve = _.value.id)
	, Field("items", ListType(ItemType),
        description = Some(""),
        resolve = _.value.items)
    )
  )
	
	  
  val SupplierType = ObjectType(
    "Supplier",
    "",

	fields[Unit, Supplier](
	  Field("id", IntType, 
      	description = Some("Id of Supplier"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("name", StringType,
        description = Some(""),
        resolve = _.value.name)
    )
  )
	
	  
  val SupplierItemType = ObjectType(
    "SupplierItem",
    "",

	fields[Unit, SupplierItem](
	  Field("id", IntType, 
      	description = Some("Id of SupplierItem"),
      	resolve = _.value.id)
	, Field("supplier", SupplierType,
        description = Some(""),
        resolve = _.value.supplier)
	, Field("item", ItemType,
        description = Some(""),
        resolve = _.value.item)
	, Field("price", FloatType,
        description = Some(""),
        resolve = _.value.price)
	, Field("stock", FloatType,
        description = Some(""),
        resolve = _.value.stock)
	, Field("activeFrom", StringType,
        description = Some(""),
        resolve = _.value.activeFrom)
	, Field("activeTo", StringType,
        description = Some(""),
        resolve = _.value.activeTo)
    )
  )
	
	  
  val GlossaryType = ObjectType(
    "Glossary",
    "",

	fields[Unit, Glossary](
	  Field("id", IntType, 
      	description = Some("Id of Glossary"),
      	resolve = _.value.id)
	, Field("values", ListType(GlossaryValueType),
        description = Some(""),
        resolve = _.value.values)
	, Field("reference", LinkType,
        description = Some(""),
        resolve = _.value.reference)
    )
  )
	
	  
  val LkupType = ObjectType(
    "Lkup",
    "",

	fields[Unit, Lkup](
	  Field("id", IntType, 
      	description = Some("Id of Lkup"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("name", StringType,
        description = Some(""),
        resolve = _.value.name)
	, Field("lookupType", StringType,
        description = Some(""),
        resolve = _.value.lookupType)
	, Field("descriptions", MapType,
        description = Some(""),
        resolve = _.value.descriptions)
	, Field("atgCode", StringType,
        description = Some(""),
        resolve = _.value.atgCode)
	, Field("externalCode", StringType,
        description = Some(""),
        resolve = _.value.externalCode)
	, Field("logo", StringType,
        description = Some(""),
        resolve = _.value.logo)
	, Field("audit", StringType,
        description = Some(""),
        resolve = _.value.audit)
	, Field("priority", StringType,
        description = Some(""),
        resolve = _.value.priority)
	, Field("glossary", GlossaryType,
        description = Some(""),
        resolve = _.value.glossary)
	, Field("reference", LinkType,
        description = Some(""),
        resolve = _.value.reference)
    )
  )
	
	  
  val EanType = ObjectType(
    "Ean",
    "",

	fields[Unit, Ean](
	  Field("id", IntType, 
      	description = Some("Id of Ean"),
      	resolve = _.value.id)
	, Field("code", StringType,
        description = Some(""),
        resolve = _.value.code)
	, Field("eciRef", StringType,
        description = Some(""),
        resolve = _.value.eciRef)
	, Field("eciRefList", ListType(StringType),
        description = Some(""),
        resolve = _.value.eciRefList)
	, Field("webVariantCode", StringType,
        description = Some(""),
        resolve = _.value.webVariantCode)
	, Field("internalProviderCode", StringType,
        description = Some(""),
        resolve = _.value.internalProviderCode)
	, Field("referenceType", StringType,
        description = Some(""),
        resolve = _.value.referenceType)
	, Field("productTypeId", StringType,
        description = Some(""),
        resolve = _.value.productTypeId)
	, Field("parentCategories", ListType(StringType),
        description = Some(""),
        resolve = _.value.parentCategories)
	, Field("lastDate", StringType,
        description = Some(""),
        resolve = _.value.lastDate)
	, Field("unpublishDate", StringType,
        description = Some(""),
        resolve = _.value.unpublishDate)
    )
  )

  val Id = Argument("id", StringType)

  val QueryType = ObjectType("Query", fields[CatalogRepo, Unit](
    Field("category", OptionType(CategoryType),
      description = Some("Returns a category with specific `id`."),
      arguments = Id :: Nil,
      resolve = c => c.ctx.category(c arg Id)),

    Field("categories", ListType(CategoryType),
      description = Some("Returns a list of all available categories."),
      resolve = _.ctx.categories)
  ))

  val schema = Schema(QueryType)
}

object QueryProcessor {
  
  def decodeQuery(json: String): Option[Json] = for {
     parsed <- json.parseOption
  } yield parsed
  
  
  
  def process(json: String, response: HttpServerResponse, vertx: Vertx) = {
    val parsed = decodeQuery(json).get
    val query = jObjectPL >=> jsonObjectPL("query") >=> jStringPL
    val operationName = jObjectPL >=> jsonObjectPL("operationName") >=> jStringPL
    val variables = jObjectPL >=> jsonObjectPL("variables") >=> jObjectPL
    
    //variables.get(parsed).get
   
    QueryParser.parse(query.get(parsed).get) match {
      // query parsed successfully, time to execute it!
      case Success(queryAst) =>
        executeGraphQLQuery(queryAst, operationName.get(parsed), jEmptyObject, response, vertx)

      // can't parse GraphQL query, return error
      case Failure(error) =>
        output(response, 500, Json("error"-> jString(error.getMessage)))
    }    
  }
  
  def executeGraphQLQuery(query: Document, op: Option[String], vars: Json, response: HttpServerResponse, vertx: Vertx) =
    Executor.execute(SchemaDefinition.schema, query, CatalogRepo(vertx), operationName = op)
      .map(output(response, 200, _))
      .recover {
        case error: QueryAnalysisError => output(response, 501, error.resolveError)
        case error: ErrorWithResolver=> output(response, 503, error.resolveError)
      }
  
  def output(response: HttpServerResponse, statusCode:Int, json: Json) =
    response.end(json.toString());
}

