package com.adopt.a.tapir.domain

import com.adopt.a.tapir.domain.model.{Pagination, TapirAnimal}
import zio._

trait AnimalShelterService {
  def register(animal: TapirAnimal): Task[TapirAnimal]
  def get(pagination: Pagination): Task[Seq[TapirAnimal]]
}

object AnimalShelterService {
  def register(animal: TapirAnimal): RIO[AnimalShelterService, TapirAnimal] =
    ZIO.serviceWithZIO[AnimalShelterService](_.register(animal))

  def get(pagination: Pagination): RIO[AnimalShelterService, Seq[TapirAnimal]] =
    ZIO.serviceWithZIO[AnimalShelterService](_.get(pagination))
}
