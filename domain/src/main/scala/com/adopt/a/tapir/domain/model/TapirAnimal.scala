package com.adopt.a.tapir.domain.model

import java.time.LocalDate
import java.util.UUID

case class TapirAnimal(
    id: UUID,
    name: String,
    registered: LocalDate,
    size: AnimalSize,
    adoptionStatus: AdoptionStatus
                      )

