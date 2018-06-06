package argonaut

import argonaut._
import argonaut.Argonaut._

import scalaz._
import Scalaz._


object parser {
  val json = """{"query1": "something"  }"""
	
	val query = jObjectPL >=> jsonObjectPL("query") >=> jStringPL
	val parsed = json.parseOption match {
      case Some(j) => j
      case _ => Json.jEmptyObject
    }
    
  query.get(parsed)
  
  case class Course (
		id: Int
		, teachingCenterId: Int
		, name: String
		, cycle: String
		, materials: Option[List[String]]
	)
	
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
	
	val res = """[{"id":3,"name":"Math 101","cycle":"Primero","materials":[],"teachingCenterId":1}]"""
	res.parse match {
		//case \/-(j) => (+j).\\.flatMap(_.--\("materials")).flatMap(_.\\)
		case \/-(j) => (+j).\\.flatMap(_.--\("materials")).map( (c:Cursor) => c.focus) == Some(jEmptyArray)
		case -\/(s) => s
	}
	
}