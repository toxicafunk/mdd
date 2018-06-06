package argonaut

import argonaut._
import argonaut.Argonaut._

import scalaz._
import Scalaz._

object parser {
  ; import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String]) = $execute {
    ; $skip(158);
    val json = """{"query1": "something"  }"""; System.out.println("""json  : String = """ + $show(json)); $skip(65);

    val query = jObjectPL >=> jsonObjectPL("query") >=> jStringPL; System.out.println("""query  : scalaz.PLensFamily[argonaut.Json,argonaut.Json,argonaut.Argonaut.JsonString,argonaut.Argonaut.JsonString] = """ + $show(query)); $skip(103);
    val parsed = json.parseOption match {
      case Some(j) ⇒ j
      case _ ⇒ Json.jEmptyObject
    }; System.out.println("""parsed  : argonaut.Json = """ + $show(parsed)); $skip(25); val res$0 =

      query.get(parsed)

    case class Course(
      id: Int, teachingCenterId: Int, name: String, cycle: String, materials: Option[List[String]])

    object Course {
      implicit def CourseCodecJson: CodecJson[Course] =
        Argonaut.casecodec5(Course.apply, Course.unapply)(
          "id", "teachingCenterId", "name", "cycle", "materials")
    }; System.out.println("""res0: Option[argonaut.Argonaut.JsonString] = """ + $show(res$0)); $skip(461);

    val res = """{"id":3,"name":"Math 101","cycle":"Primero","materials":[],"teachingCenterId":1}"""; System.out.println("""res  : String = """ + $show(res)); $skip(198); val res$1 =
      res.parse match {
        //case \/-(j) => (+j).\\.flatMap(_.--\("materials")).flatMap(_.\\)
        case \/-(j) ⇒ (+j).--\("materials").map((c: Cursor) ⇒ c.focus) == Some(jEmptyArray)
        case -\/(s) ⇒ s
      }; System.out.println("""res1: Any = """ + $show(res$1))
  }

}
