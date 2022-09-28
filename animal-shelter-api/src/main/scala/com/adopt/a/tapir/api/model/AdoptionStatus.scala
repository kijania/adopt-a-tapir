package com.adopt.a.tapir.api.model

import enumeratum.EnumEntry.Hyphencase
import enumeratum.{CirceEnum, Enum, EnumEntry, QuillEnum}
import sttp.tapir.Schema

sealed trait AdoptionStatus extends EnumEntry

object AdoptionStatus extends Enum[AdoptionStatus] with QuillEnum[AdoptionStatus] with CirceEnum[AdoptionStatus] {
  val values: IndexedSeq[AdoptionStatus] = findValues
  implicit val schema: Schema[AdoptionStatus] = Schema.derived

  case object InQuarantine           extends AdoptionStatus with Hyphencase
  case object WaitingForAdoption     extends AdoptionStatus with Hyphencase
  case object Adopted                extends AdoptionStatus with Hyphencase
  case object BehindTheRainbowBridge extends AdoptionStatus with Hyphencase

}
