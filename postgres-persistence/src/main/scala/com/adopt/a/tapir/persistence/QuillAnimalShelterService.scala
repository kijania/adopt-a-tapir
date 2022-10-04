package com.adopt.a.tapir.persistence

import com.adopt.a.tapir.domain.AnimalShelterService
import com.adopt.a.tapir.domain.model.TapirAnimal
import com.adopt.a.tapir.persistence.QuillAnimalShelterService.dataSourceLayer
import com.adopt.a.tapir.persistence.model.TapirAnimalRow
import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill
import zio.{Task, TaskLayer, ZIO, ZLayer}

case class QuillAnimalShelterService(quill: Quill.Postgres[SnakeCase]) extends AnimalShelterService {
  import quill._

  override def register(animal: TapirAnimal): Task[TapirAnimal] =
    ZIO
      .succeed(TapirAnimalRow.fromDomain(animal))
      .tap(animal => ZIO.logInfo(s"animal: $animal"))
      .flatMap(entity => insertOrUpdate(entity))
      .map(_.toDomain)

  private def insertOrUpdate(entity: TapirAnimalRow) = {
    run(
      quote(
        query[TapirAnimalRow]
          .insertValue(lift(entity))
          .onConflictUpdate(_.id)(
            (t, e) => t.name -> e.name,
            (t, e) => t.registered -> e.registered,
            (t, e) => t.size -> e.size,
            (t, e) => t.adoptionStatus -> e.adoptionStatus
          )
          .returning[TapirAnimalRow](r => r)
      )
    ).provide(dataSourceLayer)
  }
}

object QuillAnimalShelterService {
  private val dataSourceLayer = Quill.DataSource
    .fromPrefix("db")
    .tapError(error => ZIO.logError(error.getMessage))
  private val quillLayer = Quill.Postgres.fromNamingStrategy(SnakeCase)
  private val serviceLayer = ZLayer.fromFunction(QuillAnimalShelterService.apply _)

  val layer: TaskLayer[AnimalShelterService] =
    ZLayer.make[AnimalShelterService](dataSourceLayer, quillLayer, serviceLayer)
}
