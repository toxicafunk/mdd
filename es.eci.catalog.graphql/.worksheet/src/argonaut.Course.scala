package argonaut

import argonaut._
import argonaut.Argonaut._

import scalaz._
import Scalaz._

case class Course(
  id: Int, teachingCenter_id: Int, name: String, cycle: String, materials: Option[List[String]])

object Course {
  ; import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String]) = $execute {
    ; $skip(435);
    implicit def CourseCodecJson: CodecJson[Course] =
      Argonaut.casecodec5(Course.apply, Course.unapply)(
        "id", "teachingCenter_id", "name", "cycle", "materials"); System.out.println("""CourseCodecJson: => argonaut.CodecJson[argonaut.Course]""")
  }
}

object parser {
  val res = """[{"id":3,"name":"Math 101","cycle":"Primero","materials":[],"teachingCenterId":1}]"""
  res.decodeOption[List[Course]] match {
    case Some(j) ⇒ j
    case None ⇒ "Nothing"
  }
  /*res.parse match {
		case \/-(j) => (+j).\\.flatMap(_.--\("materials")).map( (c:Cursor) => c.focus) == Some(jEmptyArray)
		case -\/(s) => s
	}*/

}
