package com.adopt.a.tapir.api.model

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import sttp.tapir.EndpointIO.Example
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.{EndpointIO, Schema}

import java.time.LocalDate

case class TapirAnimal(name: String, registered: Option[LocalDate], animalSize: AnimalSize, adoptionStatus: AdoptionStatus)
object TapirAnimal {
  implicit val codec: Codec[TapirAnimal] = deriveCodec
  implicit val schema: Schema[TapirAnimal] = Schema.derived

  private val example = Example.of(
    TapirAnimal(
      "Native",
      Some(LocalDate.now()),
      AnimalSize.Small,
      AdoptionStatus.InQuarantine
    )
  )

  val asRequest: EndpointIO.Body[String, TapirAnimal] =
    jsonBody[TapirAnimal]
      .description("Information about new tapir which was registered in the animal shelter")
      .example(example)
}
