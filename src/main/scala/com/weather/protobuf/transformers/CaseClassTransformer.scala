package com.weather.protobuf.transformers

import java.io.File

import shapeless._
import shapeless.poly._

object CaseClassTransformer {

  def parseCaseClass[T](file: File):T={
    //todo parse case class
    None.asInstanceOf[T]
  }

  def convertToLabeledGeneric[T](cclass: T):LabelledGeneric[T]={
    val genc=LabelledGeneric[T]
    genc.to(cclass)
    genc
  }

}
