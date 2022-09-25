package com.adopt.a.tapir.api.model

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

import java.time.LocalDate

case class TapirAnimal(name: String, registered: Option[LocalDate], animalSize: AnimalSize, adoptionStatus: AdoptionStatus)
object TapirAnimal {
  implicit val codec: Codec[TapirAnimal] = deriveCodec
}
