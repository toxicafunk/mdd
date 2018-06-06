package es.eci.dc.scala.utils

import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test


class FilterParserTest extends JUnitSuite {
  val FiltroSimple1:String = "name:value1"
  val FiltroSimple1Out:String= "name ILIKE '%value1%'"
  val FiltroSimple2:String = "name:value1,desc:value2"
  val FiltroSimple2Out:String= "name ILIKE '%value1%' AND desc ILIKE '%value2%'"
  val FiltroSimple3:String = "name:value1,desc:value2,code:value3"
  val FiltroSimple3Out:String= "name ILIKE '%value1%' AND desc ILIKE '%value2%' AND code ILIKE '%value3%'"
  val FiltroSimpleKO:String = "name,value1"
  val FiltroSimpleKOOut:String= ""
  
  val SimpleExpression1:String = "(name EQUALS ['name1','name2'])"
  val SimpleExpression1Out:String= "(name = name1 OR name = name2)"
  val SimpleExpression2:String = "(price RANGE ['1','100'])"
  val SimpleExpression2Out:String= "(price BETWEEN 1 AND 100)"
  val SimpleExpression3:String = "(price LESS ['1','100'])"
  val SimpleExpression3Out:String= "(price < 1 OR price < 100)"
  val SimpleExpression4:String= "(price GREATER ['1','100'])"
  val SimpleExpression4Out:String= "(price > 1 OR price > 100)"
  val SimpleExpression5:String = "(price CONTAINS ['1','100'])"
  val SimpleExpression5Out:String= "(price IN (1,100))"
  val SimpleExpressionKO:String = "(name EQUALS 'name1')"
  val SimpleExpressionKOOut:String= ""
  
  val ComplexExpression1:String = "(AND (name ENDSWITH ['end1','end2']) (desc STARTSWITH ['start1','start2']))"
  val ComplexExpression1Out:String= "( (name ILIKE '%end1' OR name ILIKE '%end2') AND (desc ILIKE 'start1%' OR desc ILIKE 'start2%') )"
  val ComplexExpression2:String = "(AND (name EMPTY) (desc NOTEMPTY))"
  val ComplexExpression2Out:String= "( (name IS NULL) AND (desc IS NOT NULL) )"
  val ComplexExpression3:String = "(AND (name WITHVALUE ['value1','value2']) (desc STARTSWITH ['start1','start2']))"
  val ComplexExpression3Out:String= "( (name ILIKE '%value1%' OR name ILIKE '%value2%') AND (desc ILIKE 'start1%' OR desc ILIKE 'start2%') )"
  val ComplexExpression4:String = "(AND (name WITHVALUE ['ñ1','ç2']) (desc STARTSWITH ['áéíóú','àèìòù']))"
  val ComplexExpression4Out:String= "( (name ILIKE '%ñ1%' OR name ILIKE '%ç2%') AND (desc ILIKE 'áéíóú%' OR desc ILIKE 'àèìòù%') )"
  val ComplexExpression5:String = "(NOT (name WITHVALUE ['a1','a2']) (desc STARTSWITH ['d1','d2']))"
  val ComplexExpression5Out:String= "( NOT (name ILIKE '%a1%' OR name ILIKE '%a2%') AND NOT (desc ILIKE 'd1%' OR desc ILIKE 'd2%') )"
  val ComplexExpressionKO:String = "(AND (name WITHVALUE [value1,value2]) (desc STARTSWITH ['start1','start2']))"
  val ComplexExpressionKOOut:String= ""
  
  val CComplexExpression1:String = "(OR (name ENDSWITH ['end1','end2']) (AND (code WITHVALUE ['value1','value2']) (desc STARTSWITH ['start1','start2'])))"
  val CComplexExpression1Out:String= "( (name ILIKE '%end1' OR name ILIKE '%end2') OR ( (code ILIKE '%value1%' OR code ILIKE '%value2%') AND (desc ILIKE 'start1%' OR desc ILIKE 'start2%') ) )"
  val CComplexExpression2:String = "(OR (name EMPTY) (AND (code EMPTY) (desc NOTEMPTY)))"
  val CComplexExpression2Out:String= "( (name IS NULL) OR ( (code IS NULL) AND (desc IS NOT NULL) ) )"
  val CComplexExpression3:String = "(AND (OR (name WITHVALUE ['value1','value2']) (desc STARTSWITH ['start1','start2'])) (code CONTAINS ['value1','value2']))"
  val CComplexExpression3Out:String= "( ( (name ILIKE '%value1%' OR name ILIKE '%value2%') OR (desc ILIKE 'start1%' OR desc ILIKE 'start2%') ) AND (code IN (value1,value2)) )"
  val CComplexExpressionKO:String = "(AND (OR (name X ['value1','value2']) (desc STARTSWITH ['start1','start2'])) (code CONTAINS ['value1','value2']))"
  val CComplexExpressionKOOut:String= ""
  
  
  
  @Test def verifyFiltroSimple(): Unit = { 
    var out1:String = FilterParser.parse(FiltroSimple1,new java.util.HashMap[String,Array[String]]())
    assertEquals(out1,FiltroSimple1Out)
    var out2 = FilterParser.parse(FiltroSimple2,new java.util.HashMap[String,Array[String]]())
    assertEquals(out2,FiltroSimple2Out)
    var out3 = FilterParser.parse(FiltroSimple3,new java.util.HashMap[String,Array[String]]())
    assertEquals(out3,FiltroSimple3Out)
    var out4 = FilterParser.parse(FiltroSimpleKO,new java.util.HashMap[String,Array[String]]())
    assertEquals(out4,FiltroSimpleKOOut)
    
  }
  
  @Test def verifySimpleExpression(): Unit = { 
    var out1:String = FilterParser.parse(SimpleExpression1,new java.util.HashMap[String,Array[String]]())
    assertEquals(out1,SimpleExpression1Out)
    var out2 = FilterParser.parse(SimpleExpression2,new java.util.HashMap[String,Array[String]]())
    assertEquals(out2,SimpleExpression2Out)
    var out3 = FilterParser.parse(SimpleExpression3,new java.util.HashMap[String,Array[String]]())
    assertEquals(out3,SimpleExpression3Out)
    var out4 = FilterParser.parse(SimpleExpression4,new java.util.HashMap[String,Array[String]]())
    assertEquals(out4,SimpleExpression4Out)
    var out5 = FilterParser.parse(SimpleExpression5,new java.util.HashMap[String,Array[String]]())
    assertEquals(out5,SimpleExpression5Out)
    var out6 = FilterParser.parse(SimpleExpressionKO,new java.util.HashMap[String,Array[String]]())
    assertEquals(out6,SimpleExpressionKOOut)
    
  }
  
  @Test def verifyComplexExpression(): Unit = { 
    var out1:String = FilterParser.parse(ComplexExpression1,new java.util.HashMap[String,Array[String]]())
    assertEquals(out1,ComplexExpression1Out)
    var out2 = FilterParser.parse(ComplexExpression2,new java.util.HashMap[String,Array[String]]())
    assertEquals(out2,ComplexExpression2Out)
    var out3 = FilterParser.parse(ComplexExpression3,new java.util.HashMap[String,Array[String]]())
    assertEquals(out3,ComplexExpression3Out)
    var out4 = FilterParser.parse(ComplexExpression4,new java.util.HashMap[String,Array[String]]())
    assertEquals(out4,ComplexExpression4Out)
    var out5 = FilterParser.parse(ComplexExpression5,new java.util.HashMap[String,Array[String]]())
    assertEquals(out5,ComplexExpression5Out)
    var out6 = FilterParser.parse(ComplexExpressionKO,new java.util.HashMap[String,Array[String]]())
    assertEquals(out6,ComplexExpressionKOOut)
    
  }
  
  @Test def verifyCComplexExpression(): Unit = { 
    var out1:String = FilterParser.parse(CComplexExpression1,new java.util.HashMap[String,Array[String]]())
    assertEquals(out1,CComplexExpression1Out)
    var out2 = FilterParser.parse(CComplexExpression2,new java.util.HashMap[String,Array[String]]())
    assertEquals(out2,CComplexExpression2Out)
    var out3 = FilterParser.parse(CComplexExpression3,new java.util.HashMap[String,Array[String]]())
    assertEquals(out3,CComplexExpression3Out)
    var out6 = FilterParser.parse(CComplexExpressionKO,new java.util.HashMap[String,Array[String]]())
    assertEquals(out6,CComplexExpressionKOOut)
    
  }


}