package es.eci.dc.scala.utils

import scala.util.parsing.combinator.JavaTokenParsers

import java.io.StringReader
import scala.BigDecimal
import scala.language.implicitConversions
import scala.language.postfixOps

import scalaz._
import Scalaz._

object FilterParser extends JavaTokenParsers {

  // (OR (code CONTAINS ['001','002']) (AND (saletype CONTAINS ['1','2']) (name CONTAINS ['Departamento1','Departamento2'])))

  private var f: java.util.Map[String, Array[String]] = new java.util.HashMap()
  
  def simple_name_value : Parser[String] =  name ~ ":" ~ value ~ opt(",")^^ {s =>
    var name: String = s._1._1._1
    var tail: String =""
    if(f.containsKey(name)) name=f.get(name)(0)
    if(s._2.isDefined) tail=" AND "
    name+" ILIKE " +s._1._2 + tail
    
  }
  
  def simple_filter : Parser[String] =  rep(simple_name_value)^^ {s =>
    s.mkString
  }
  
  def value: Parser[String] = regex("[^':,\\[\\]\"]+".r) ^^ {s => "'%"+s+"%'"} //valor para like

  def name: Parser[String] = regex("\\w+".r) ^^ {s => s  }

  def values: Parser[String] = regex("\\['[^':,\\[\\]\"]+'(,'[^':,\\[\\]\"]+')*\\]".r) ^^ {s => s.replace("[", "").replace("]","").replaceAll("'","")}

  def openExp: Parser[String] = "(" ^^ { s => s }

  def closeExp: Parser[String] = ")" ^^ { s => s }

  def operator: Parser[String] = "RANGE" | "EQUALS" | "CONTAINS" | "STARTSWITH" | "ENDSWITH" | "WITHVALUE" | "EMPTY" | "NOTEMPTY" | "LESS" | "LESSEQUALS" | "GREATER" | "GREATEREQUALS" ^^ { s => s }

  def logicOperator: Parser[String] = "OR" | "AND" | "NOT" ^^ { s => s }

  def simple_expression: Parser[String] = openExp ~ name ~ operator ~ opt(values) ~ closeExp ^^ {s => 
    var sep : String = "";
    var name: String = s._1._1._1._2
    var values: String = ""
    if(s._1._2.isDefined) values = s._1._2.get
    if(f.containsKey(s._1._1._1._2)) f.get(name)(1) match {
      case "STRING" | "DATE" | "RICH_TEXT"   => sep="'"
    }
    if(f.containsKey(s._1._1._1._2)) name=f.get(name)(0)

    s._1._1._2 match {
       case "RANGE" => "("+name+" BETWEEN "+sep+values.split(",").mkString(sep+" AND "+sep)+sep+")";
       case "EQUALS" => "("+name+" = "+sep+values.split(",").mkString(sep+" OR "+name+" = "+sep)+sep+")";
       case "LESS" => "("+name+" < "+sep+values.split(",").mkString(sep+" OR "+name+" < "+sep)+sep+")";
       case "LESSEQUALS" => "("+name+" <= "+sep+values.split(",").mkString(sep+" OR "+name+" <= "+sep)+sep+")";
       case "GREATER" => "("+name+" > "+sep+values.split(",").mkString(sep+" OR "+name+" > "+sep)+sep+")";
       case "GREATEREQUALS" => "("+name+" >= "+sep+values.split(",").mkString(sep+" OR "+name+" >= "+sep)+sep+")";
       case "CONTAINS" => "("+name+" IN ("+sep+values.split(",").mkString(sep+","+sep)+sep+")"+")";
       case "EMPTY" => "("+name+" IS NULL"+")";
       case "NOTEMPTY" => "("+name+" IS NOT NULL"+")";
       case "ENDSWITH" => "("+name+" ILIKE '%" +values.split(",").mkString("' OR "+name+" ILIKE '%" )+"'"+")";
       case "STARTSWITH" => "("+name+" ILIKE '" +values.split(",").mkString("%' OR "+name+" ILIKE '" )+"%'"+")";
       case "WITHVALUE" => "("+name+" ILIKE '%" +values.split(",").mkString("%' OR "+name+" ILIKE '%" )+"%'"+")";
    }

  }
  
  def complex_expression: Parser[String] = openExp ~ logicOperator ~ rep(simple_expression| complex_expression) ~ closeExp ^^ { s =>
    if(s._1._1._2.equals("NOT")) "( "+s._1._1._2+" "+s._1._2.mkString(" AND "+s._1._1._2+" ")+" )"
    else "( "+s._1._2.mkString(" "+s._1._1._2+" ")+" )"
   }
 

  def filter : Parser[String] = (simple_expression | complex_expression | simple_filter ) ^^ { s => s  }

  
  def parse (s: String, fields: java.util.Map[String, Array[String]]) : String = {
    f=fields
   return FilterParser.parse(filter, s) match {
      case Success(result, next) => result
      case Failure(msg, next) => msg
    }
 }
  
  
  val filtro = "(price GRETER ['1','100'])"
  val filtro2 = "(OR (price GREATER ['1','100']))"
  val filtro3 = "(NOT (name WITHVALUE ['a1','a2']) (desc STARTSWITH ['d1','d2']))"
  
  def main(args: Array[String]): Unit = {
    FilterParser.parse(filter, new StringReader(filtro3)) match {
      case Success(result, other) => println(s"$result")
      case e: NoSuccess => println(e.get) 
    }
    
    println(parse(filtro, new java.util.HashMap[String, Array[String]]()))
  }
  

}

  