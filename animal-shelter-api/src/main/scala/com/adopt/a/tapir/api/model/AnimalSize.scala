package com.adopt.a.tapir.api.model

import enumeratum.{CirceEnum, Enum, EnumEntry, QuillEnum}
import enumeratum.EnumEntry.Lowercase
import sttp.tapir.Schema

sealed trait AnimalSize extends EnumEntry

object AnimalSize extends Enum[AnimalSize] with QuillEnum[AnimalSize] with CirceEnum[AnimalSize] {
  val values: IndexedSeq[AnimalSize] = findValues
  implicit val schema: Schema[AnimalSize] = Schema.derived

  case object Small  extends AnimalSize with Lowercase
  case object Medium extends AnimalSize with Lowercase
  case object Large  extends AnimalSize with Lowercase
}
