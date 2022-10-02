package com.adopt.a.tapir.domain.model

import enumeratum.EnumEntry.Hyphencase
import enumeratum._
import sttp.tapir.Schema

sealed trait AdoptionStatus extends EnumEntry

object AdoptionStatus extends Enum[AdoptionStatus] with QuillEnum[AdoptionStatus] with CirceEnum[AdoptionStatus] {
  val values: IndexedSeq[AdoptionStatus]      = findValues
  implicit val schema: Schema[AdoptionStatus] = Schema.derived

  case object InQuarantine           extends AdoptionStatus with Hyphencase
  case object WaitingForAdoption     extends AdoptionStatus with Hyphencase
  case object Adopted                extends AdoptionStatus with Hyphencase
  case object BehindTheRainbowBridge extends AdoptionStatus with Hyphencase

}
