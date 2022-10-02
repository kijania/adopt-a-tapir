package com.adopt.a.tapir.persistence.model

import com.adopt.a.tapir.domain.model.{AdoptionStatus, AnimalSize, TapirAnimal}

import java.time.LocalDate
import java.util.UUID

case class TapirAnimalRow(
                           id: UUID,
                           name: String,
                           registered: LocalDate,
                           size: AnimalSize,
                           adoptionStatus: AdoptionStatus
                         ) {
  def toDomain: TapirAnimal = TapirAnimal(id, name, registered, size, adoptionStatus)
}

object TapirAnimalRow {
  def fromDomain(domain: TapirAnimal): TapirAnimalRow =
    TapirAnimalRow(
      domain.id,
      domain.name,
      domain.registered,
      domain.size,
      domain.adoptionStatus
    )
}
