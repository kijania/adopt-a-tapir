package com.adopt.a.tapir.domain

import com.adopt.a.tapir.domain.model.TapirAnimal
import zio._

trait AnimalShelterService {
  def register(animal: TapirAnimal): Task[TapirAnimal]
}

object AnimalShelterService {
  def register(animal: TapirAnimal): RIO[AnimalShelterService, TapirAnimal] =
    ZIO.serviceWithZIO[AnimalShelterService](_.register(animal))

}

class AnimalShelterServiceNaive extends AnimalShelterService {
  override def register(animal: TapirAnimal): UIO[TapirAnimal] =
    ZIO.logInfo(s"Create new animal: $animal").map(_ => animal)
}

object AnimalShelterServiceNaive {
  def layer: ULayer[AnimalShelterService] =
    ZLayer.succeed(new AnimalShelterServiceNaive())
}