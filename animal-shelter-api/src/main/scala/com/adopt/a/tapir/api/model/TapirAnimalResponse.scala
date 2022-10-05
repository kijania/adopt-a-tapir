package com.adopt.a.tapir.api.model

import com.adopt.a.tapir.domain.model.{AdoptionStatus, AnimalSize, TapirAnimal}
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import sttp.model.StatusCode
import sttp.tapir.EndpointIO.Example
import sttp.tapir._
import sttp.tapir.json.circe.jsonBody

import java.time.LocalDate
import java.util.UUID

case class TapirAnimalResponse(
    id: UUID,
    name: String,
    registered: LocalDate,
    size: AnimalSize,
    adoptionStatus: AdoptionStatus
)

object TapirAnimalResponse {
  implicit val codec: Codec[TapirAnimalResponse]   = deriveCodec
  implicit val schema: Schema[TapirAnimalResponse] = Schema.derived

  def fromDomain(animal: TapirAnimal): TapirAnimalResponse =
    TapirAnimalResponse(animal.id, animal.name, animal.registered, animal.size, animal.adoptionStatus)

  private val example = Example.of(
    TapirAnimalResponse(
      UUID.randomUUID(),
      "Smelly Naomi",
      LocalDate.now(),
      AnimalSize.Small,
      AdoptionStatus.InQuarantine
    )
  )

  val asResponse: EndpointOutput[TapirAnimalResponse] =
    statusCode(StatusCode.Created)
      .description("Tapir model stored in the system")
      .and(jsonBody[TapirAnimalResponse].example(example))
}
