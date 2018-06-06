
package com.mindcurv.argonaut

import JsonWPCMessage._
import scalaz._
import scalaz.Scalaz._
import scalaz.Kleisli._
import es.eci.catalog.model.pojos._
import java.sql.Timestamp
import java.util.Calendar
import java.text.SimpleDateFormat
import monocle.function.Curry.curry
import es.eci.catalog.model.pojos.Brand
import scala.collection.JavaConversions._

object JsonWPCBuilder {
  
  val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZZ")
  
  implicit class AttribUtils(val attrib: Attrib) {
    def asString: String = attrib.value match { 
      case Some(s) => s.toString 
      case _ => ""
    }
    
    def asBoolean: Boolean = attrib.asString.toBoolean
    
    def asFloat: Float = attrib.asString.toFloat
    
    def asMap: es.eci.catalog.model.pojos.Map = attrib.children match {
      case Some(children) => {
        val maps = new Map()
        children.foreach { c => maps.setKey(c.id); maps.setValue(c.asString) } 
        maps
      }
      case None => new Map()
    }
    
    def asArray: Array[Integer] = attrib.children match {
      case Some(children) => children.map { c => Integer.decode(c.asString) }.toArray
      case None => Array.empty
    }
    
    def asListMap: List[es.eci.catalog.model.pojos.Map] = attrib.children match {
      case Some(children) => children.map { 
          c => new Map(null, c.id, c.asString)
      }
      case None => Nil
    }
  }
  
  implicit class LinkUtils(val links: List[Link]) {
    def asArray: Array[String] =
      if (links.isEmpty) Array.empty
      else links.map { l => s"${l.hierarchyId}.${l.id_Ext}" }.toArray
    
    
    def asString: String =
      if (links.isEmpty) ""
      else links(0).categoryId
  }
  
  def findAttribute(key: String): List[Attrib] => Option[Attrib] = (children: List[Attrib]) => children match {
      case h :: t if h.id == key => Some(h)
      case h :: t => h.children match {
        case Some(c) => findAttribute(key)(c) orElse findAttribute(key)(t)
        case None => findAttribute(key)(t)
      }
      case _ => None
    }
  
  def findAttributeFromCore(key: String): CoreAttribs => Option[Attrib] = (xs: CoreAttribs) =>
    findAttribute(key)(xs.children)
  
  def findCoreAttribById(coreAttribs: List[CoreAttribs]): String => Option[CoreAttribs] = 
     (id: String) => {
      coreAttribs.filter { cAttr => cAttr.id == id } match {
        case h :: t => Some(h)
        case _ => None
      }
  }
  
  def getAttrFromLink(links: Option[List[Link]])(id: String): Option[List[Link]] = links match {
    case Some(attribs) => Option(attribs.filter { l => l.hierarchyId == id })
    case _ => None
  }
  
  def getAttrK(id: String)(coreAttribs: List[CoreAttribs]): String => Option[Attrib] =  (key: String) => {
    val k = kleisli(findAttributeFromCore(key)) composeK findCoreAttribById(coreAttribs)
    k(id)
  }
     
  def getAttr(id: String)(coreAttribs: List[CoreAttribs]): String => Option[Attrib] = (key: String) => for {
    coreAttrib <- findCoreAttribById(coreAttribs)(id)
    attr <- findAttributeFromCore(key)(coreAttrib)
  } yield attr
  
  def constructBrand(jsonMessage: JsonWPCMessage): es.eci.catalog.model.pojos.Brand = {
  	
    val getAttrFromCore = getAttrK("/Marcas_CPS")(jsonMessage.coreAttribs)
    
    val brand = (
    	-1.some
    	|@| getAttrFromCore("Code")
    	|@| getAttrFromCore("CodeATG")
    	|@| getAttrFromCore("Description")
    	|@| getAttrFromCore("DisplayMarca")
    ) {(
    	id: Int
      ,	code
      ,	atgCode
      ,	description
      ,	displayBrand
    ) => new es.eci.catalog.model.pojos.Brand(
      null
      , null
      , code.asString
      , atgCode.asString
      , description.asString
      , displayBrand.asBoolean
     )
    }
     
    println(brand)
    brand.get
  }
  
  def constructCategory(jsonMessage: JsonWPCMessage): es.eci.catalog.model.pojos.Category = {
  	
    val getAttrFromCore = getAttrK("/Product_HPS")(jsonMessage.coreAttribs)
    
    val category = (
    	-1.some
    	|@| getAttrFromCore("Key")
    	|@| getAttrFromCore("KeyATG")
    	|@| getAttrFromCore("Unpublish")
    	|@| getAttrFromCore("Description")
    	|@| getAttrFromCore("StartDate")
    	|@| getAttrFromCore("FinishDate")
    	|@| getAttrFromCore("DiasNovedad")
    ) {(
    	id: Int
      ,	code
      ,	atgCode
      ,	status
      ,	descriptions
      ,	startDate
      ,	endDate
      ,	noveltyDays
    ) => new es.eci.catalog.model.pojos.Category(
      null
      , code.asString
      , atgCode.asString
      , null
      , status.asString
      , descriptions.asListMap
      , null
      , startDate.asString
      , endDate.asString
      , null
      , null
      , noveltyDays.asFloat
      , null
     )
    }
     
    println(category)
    category.get
  }
  
  def constructProductType(jsonMessage: JsonWPCMessage): es.eci.catalog.model.pojos.ProductType = {
  	
    val getAttrFromCore = getAttrK("/ProductType_HPS")(jsonMessage.coreAttribs)
    
    val productType = (
    	-1.some
    	|@| getAttrFromCore("Key")
    	|@| getAttrFromCore("KeyATG")
    	|@| getAttrFromCore("Description")
    ) {(
    	id: Int
      ,	code
      ,	atgCode
      ,	descriptions
    ) => new es.eci.catalog.model.pojos.ProductType(
      null
      , code.asString
      , atgCode.asString
      , descriptions.asListMap
      , null
      , null
      , null
      , null
      , null
      , null
     )
    }
     
    println(productType)
    productType.get
  }
  
  def constructEan(jsonMessage: JsonWPCMessage): es.eci.catalog.model.pojos.Ean = {
  	
    val getAttrFromCore = getAttrK("/Product_CPS")(jsonMessage.coreAttribs)
    
    val ean = (
    	-1.some
    	|@| getAttrFromCore("EanGtin")
    	|@| getAttrFromCore("RefECI")
    	|@| getAttrFromCore("CodigoVarianteWeb")
    	|@| getAttrFromCore("NumeroProveedor")
    	|@| getAttrFromCore("TipoReferencia")
    	|@| getAttrFromLink(jsonMessage.link)("998")
    	|@| getAttrFromLink(jsonMessage.link)("999")
    	|@| getAttrFromCore("FechaDespublicacionWWW")
    ) {(
    	id: Int
      ,	code
      ,	eciRef
      ,	webVariantCode
      ,	internalProviderCode
      ,	referenceType
      ,	productTypeId
      ,	parentCategories
      ,	unpublishDate
    ) => new es.eci.catalog.model.pojos.Ean(
      null
      , code.asString
      , eciRef.asString
      , null
      , webVariantCode.asString
      , internalProviderCode.asString
      , referenceType.asString
      , productTypeId.asString
      , parentCategories.asArray
      , null
      , unpublishDate.asString
     )
    }
     
    println(ean)
    ean.get
  }
  
}
