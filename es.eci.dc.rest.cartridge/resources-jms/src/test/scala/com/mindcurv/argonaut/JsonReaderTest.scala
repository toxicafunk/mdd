package com.mindcurv.argonaut

import argonaut.Argonaut.StringToParseWrap
import argonaut.CursorHistory

import scalaz._, Scalaz._
import argonaut.Parse
import argonaut._, Argonaut._


object JsonReaderTest {
  def main(args: Array[String]): Unit = {

    def process(filename: String): Unit = {
      val file = getClass.getResource("/" + filename).getFile()
      val json = scala.io.Source.fromFile(file).mkString
      
      val result: (String \/ (String, CursorHistory)) \/ JsonWPCMessage = Parse.decode[JsonWPCMessage](json)
      println(result)

      val jsonWPCMessageOpt: Option[JsonWPCMessage] = json.decodeOption[JsonWPCMessage]
      println(jsonWPCMessageOpt)
      val jsonWPCMessage = jsonWPCMessageOpt.get
      println(s"$filename : ${jsonWPCMessage.id_Ext}")
      jsonWPCMessage.coreAttribs.foreach { c =>  println(s"""KeyATG: ${JsonWPCBuilder.findAttribute("KeyATG")(c.children)}""") }
      jsonWPCMessage.coreAttribs.foreach { c =>  println(s"""es_ES: ${JsonWPCBuilder.findAttribute("es_ES")(c.children)}""") }
    }

    import scala.io.Source
    val source = Source.fromURL(getClass.getResource("/"))
    source.getLines.filter(f => f.endsWith(".json")).foreach(l => process(l))
  }
}