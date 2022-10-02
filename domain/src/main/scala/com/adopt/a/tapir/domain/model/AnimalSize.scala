package com.adopt.a.tapir.domain.model

import enumeratum.EnumEntry.Lowercase
import enumeratum._
import sttp.tapir.Schema

sealed trait AnimalSize extends EnumEntry

object AnimalSize extends Enum[AnimalSize] with QuillEnum[AnimalSize] with CirceEnum[AnimalSize] {
  val values: IndexedSeq[AnimalSize]      = findValues
  implicit val schema: Schema[AnimalSize] = Schema.derived

  case object Small  extends AnimalSize with Lowercase
  case object Medium extends AnimalSize with Lowercase
  case object Large  extends AnimalSize with Lowercase
}
