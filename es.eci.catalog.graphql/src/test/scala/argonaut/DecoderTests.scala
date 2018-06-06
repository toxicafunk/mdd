package argonaut


import argonaut._
import argonaut.Argonaut._

import scalaz._
import Scalaz._

sealed trait Catalog

	
case class ItemImage (
	id: Int
	, itemId: Int
	, altText: String
	, image: String
) extends Catalog

object ItemImage {
  implicit def ItemImageCodecJson: CodecJson[ItemImage ] =
    Argonaut.casecodec4(ItemImage .apply, ItemImage .unapply)(
    	"id"
	  	, "itemId"
    	,"altText"
    	,"image"
    )
}

	
case class Item (
	id: Int
	, name: String
	, description: Option[String]
	, code: Option[String]
	, images: Option[List[ItemImage]]
	, price: Option[Double]
	, retailPrice: Option[Double]
	, costPrice: Option[Double]
) extends Catalog

object Item {
  implicit def ItemCodecJson: CodecJson[Item ] =
    Argonaut.casecodec8(Item .apply, Item .unapply)(
    	"id"
    	,"name"
    	,"description"
    	,"code"
    	,"images"
    	,"price"
    	,"retailPrice"
    	,"costPrice"
    )
}

	
case class Material (
	id: Int
	, courseId: Int
	, quantity: Double
	, item: Item
) extends Catalog

object Material {
  implicit def MaterialCodecJson: CodecJson[Material ] =
    Argonaut.casecodec4(Material .apply, Material .unapply)(
    	"id"
	  	, "courseId"
    	,"quantity"
    	,"item"
    )
}

	
case class Course (
	id: Int
	, teachingCenterId: Int
	, name: String
	, cycle: String
	, materials: Option[List[Material]]
) extends Catalog

object Course {
  implicit def CourseCodecJson: CodecJson[Course ] =
    Argonaut.casecodec5(Course .apply, Course .unapply)(
    	"id"
	  	, "teachingCenterId"
    	,"name"
    	,"cycle"
    	,"materials"
    )
}

	
case class TeachingCenter (
	id: Int
	, name: String
	, genericName: Option[String]
	, email: Option[String]
	, phone: Option[String]
	, image: Option[String]
	, code: String
	, `type`: Option[String]
	, nature: Option[String]
	, address: Option[String]
	, formattedAddress: Option[String]
	, postalCode: Option[String]
	, town: Option[String]
	, province: Option[String]
	, autonomousRegion: Option[String]
	, latitude: Option[Double]
	, longitude: Option[Double]
	, courses: Option[List[Course]]
) extends Catalog

object TeachingCenter {
  implicit def TeachingCenterCodecJson: CodecJson[TeachingCenter ] =
    Argonaut.casecodec18(TeachingCenter .apply, TeachingCenter .unapply)(
    	"id"
    	,"name"
    	,"genericName"
    	,"email"
    	,"phone"
    	,"image"
    	,"code"
    	,"type"
    	,"nature"
    	,"address"
    	,"formattedAddress"
    	,"postalCode"
    	,"town"
    	,"province"
    	,"autonomousRegion"
    	,"latitude"
    	,"longitude"
    	,"courses"
    )
}

object DecoderTests {
  
  val json = """[{"name":"Math101","cycle":"First","materials":[{"quantity":1.0,"item":1,"courseId":1,"id":1},{"quantity":1.0,"item":2,"courseId":1,"id":2},{"quantity":1.0,"item":3,"courseId":1,"id":3}],"teachingCenterId":1,"id":1}]"""
  
  def main(args: Array[String]): Unit = {
    val r = json.parse match {
  		//case \/-(j) => (+j).\\.flatMap(_.--\("materials")).flatMap(_.\\)
  		case \/-(j) => (+j).\\.flatMap(_.--\("materials")).map( (c:Cursor) => c.focus)
  		case -\/(s) => s
  	}
    println(r)
    
    val rs = json.decodeEither[List[Course]] match {
      case -\/(s) =>  s
      case \/-(lst) => lst
    }
    
    println(rs)
    
    //val errors = """{"errors": ["Error al borrar record"]}"""
    val errors = "{}"
    val err = errors.parse match {
      case -\/(s) => { jString(s)}
      case \/-(j) => j
    }
    
    println (err)
  }
}