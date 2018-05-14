package com.weather.protobuf.transformers

import shapeless._
import shapeless.poly._

object CaseClassTransformer {

  def convertToLabeledGeneric[T](cclass: T):LabelledGeneric[T]={
    val genc=LabelledGeneric[T]
    genc.to(cclass)
    genc
  }

}
