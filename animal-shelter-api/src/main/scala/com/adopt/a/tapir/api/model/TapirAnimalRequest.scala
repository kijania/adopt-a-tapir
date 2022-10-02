package com.adopt.a.tapir.api.model

import com.adopt.a.tapir.domain.model.{AdoptionStatus, AnimalSize, TapirAnimal}
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import sttp.tapir.EndpointIO.Example
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.{EndpointIO, Schema}

import java.time.LocalDate
import java.util.UUID

case class TapirAnimalRequest(
    name: String,
    size: AnimalSize,
    adoptionStatus: AdoptionStatus
) {
  def toDomain: TapirAnimal =
    TapirAnimal(UUID.randomUUID(), name, LocalDate.now, size, adoptionStatus)
}

object TapirAnimalRequest {
  implicit val codec: Codec[TapirAnimalRequest]   = deriveCodec
  implicit val schema: Schema[TapirAnimalRequest] = Schema.derived

  private val example = Example.of(
    TapirAnimalRequest(
      "Native",
      AnimalSize.Small,
      AdoptionStatus.InQuarantine
    )
  )

  val asRequest: EndpointIO.Body[String, TapirAnimalRequest] =
    jsonBody[TapirAnimalRequest]
      .description("Information about new tapir which was registered in the animal shelter")
      .example(example)
}
