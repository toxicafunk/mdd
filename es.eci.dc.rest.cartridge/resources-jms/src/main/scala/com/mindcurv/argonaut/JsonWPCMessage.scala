package com.mindcurv.argonaut

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
import io.vertx.core.json.Json
import io.vertx.core.logging.LoggerFactory
import main.WPCType

/**
  * Created by erodriguez on 04/01/17.
  * 
  * // "001" -> Reference referenciaEspana (pimReference)
    // "011" -> PimMessage categoriaCampaign
    // "012" -> PimMessage categoriaFichaProducto (pimProductType)
    // "013" -> PimMessage categoriaVenta (pimSaleshirarchy) -> notifiedChange -> valueListCheckerRouter
    // "051" -> PimMessageWithSpecificAtt autores (PimMessageWithSpecificAtt) -> notifiedChange -> valueListCheckerRouter
    // "052" -> PimMessageWithSpecificAndLink actores (PimMessageWithSpecificAndLink) -> notifiedChange -> valueListCheckerRouter
    // "054" -> PimMessageWithSpecificAndLink glosario (pimGlossary)
    // "055" -> PimMessageWithSpecificAndLink interOut (pimPerformer) -> notifiedChange -> valueListCheckerRouterPimMessageWithSpecificAndLink
    // "056" -> PimMessageWithSpecificAtt marcas (PimMessageWithSpecificAtt) -> notifiedChange -> valueListCheckerRouter
    // "090" -> PimMessage features (pimFeatureWorker) -> notifiedChange -> valueListCheckerRouter
  */
case class Price(productId: String, centerId: String, regularPrice: String, catalogPrice: String, taxPercentage: String, igic: String)

case class Link(id_Ext: String, hierarchyId: String, categoryId: String, order: Option[String])

case class Attrib(id: String, `type`: String, value: Option[String], children: Option[List[Attrib]], featureGroupID: Option[String], id_Ext: Option[String])

case class CoreAttribs(id: String, `type`: String, children: List[Attrib]) // TODO ctype to Enum of Type

case class JsonWPCMessage(id_Ext: String, id: String, entryContainerID: String, movement: String, coreAttribs: List[CoreAttribs],
                          link: Option[List[Link]], specificAttribs: Option[List[Attrib]], stores: Option[List[Attrib]], prices: Option[List[Price]]) //TODO movement to Enum or Type

object Price {
  implicit def PriceCodecJson: CodecJson[Price] =
    Argonaut.casecodec6(Price.apply, Price.unapply)("productId", "centerId", "regularPrice", "catalogPrice", "taxPercentage", "igic")
}

object Link {
  implicit def LinkCodecJson: CodecJson[Link] =
    Argonaut.casecodec4(Link.apply, Link.unapply)("id_Ext", "hierarchyId", "categoryId", "order")
}

object Attrib {
  implicit def AttribDecodeJson: DecodeJson[Attrib] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[String]
      ctype <- (c --\ "type").as[String]
      value <- (c --\ "value").as[Option[String]]
      children <- (c --\ "children").as[Option[List[Attrib]]]
      featureGroupID <- (c --\ "featureGroupID").as[Option[String]]
      id_Ext <- (c --\ "id_Ext").as[Option[String]]
    } yield Attrib(id, ctype, value, children, featureGroupID, id_Ext)
    )

  implicit def AttribEncodeJson: EncodeJson[Attrib] =
    EncodeJson( (att: Attrib) =>
      ("id" := att.id) ->:
        ("type" := att.`type`) ->:
        ("value" := att.value) ->:
        ("children" := att.children) ->:
        ("featureGroupID" := att.featureGroupID) ->:
        ("id_Ext" := att.id_Ext) ->:
    jEmptyObject)
}

object CoreAttribs {
  implicit def CoreAttribsCodecJson: CodecJson[CoreAttribs] =
    Argonaut.casecodec3(CoreAttribs.apply, CoreAttribs.unapply)("id", "type", "children")
}

object JsonWPCMessage {
  
  val logger = LoggerFactory.getLogger("JsonWPCMessage")
  
  implicit def JsonWPCMessageCodecJson: CodecJson[JsonWPCMessage] =
    Argonaut.casecodec9(JsonWPCMessage.apply, JsonWPCMessage.unapply)("id_Ext", "id", "entryContainerID", "movement", "coreAttribs",
      "link", "specificAttribs", "stores", "prices")
      
  def decodeJson(json: String): Option[JsonWPCMessage] = json.decodeOption[JsonWPCMessage]
  
  def wpc2DC(json: String, jsonType: WPCType): String = {
		//val jsonWPC: JsonWPCMessage = decodeJson(json);
    decodeJson(json) match {
      case Some(jsonWPC) => constructResponse(jsonWPC, jsonType)
      case _ => "{}"
    }
  }
  
  def constructResponse(jsonMessage: JsonWPCMessage, jsonType: WPCType): String = {
		val response = jsonType match {
		  case WPCType.SALESHIERARCHY | WPCType.CAMPAIGNS => JsonWPCBuilder.constructCategory(jsonMessage)
		  case WPCType.BRANDS => JsonWPCBuilder.constructBrand(jsonMessage)
		  case WPCType.REFERENCESESP => JsonWPCBuilder.constructEan(jsonMessage)
		  case WPCType.PRODUCTTYPES => JsonWPCBuilder.constructProductType(jsonMessage)
		}
				
		val jsonResponse = Json.encode(response)
		jsonResponse
  }
  
}
