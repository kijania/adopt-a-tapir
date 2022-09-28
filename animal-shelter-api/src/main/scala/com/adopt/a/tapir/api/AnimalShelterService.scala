package com.adopt.a.tapir.api

import com.adopt.a.tapir.api.model.TapirAnimal
import zio.{UIO, ULayer, URIO, ZIO, ZLayer}

trait AnimalShelterService {
  def register(animal: TapirAnimal): UIO[Unit]
}

object AnimalShelterService {
  def register(animal: TapirAnimal): URIO[AnimalShelterService, Unit] =
    ZIO.serviceWith[AnimalShelterService](_.register(animal))

}

class AnimalShelterServiceNaive extends AnimalShelterService {
  override def register(animal: TapirAnimal): UIO[Unit] =
    ZIO.logInfo(s"Create new animal: $animal")
}

object AnimalShelterServiceNaive {
  def layer: ULayer[AnimalShelterService] =
    ZLayer.succeed(new AnimalShelterServiceNaive())
}