package com.adopt.a.tapir.persistence

import com.adopt.a.tapir.domain.AnimalShelterService
import com.adopt.a.tapir.domain.model.TapirAnimal
import com.adopt.a.tapir.persistence.QuillContext.PostgresConnectionPool
import com.adopt.a.tapir.persistence.model.TapirAnimalRow
import zio.{Task, ZIO}

import scala.concurrent.ExecutionContext

class QuillAnimalShelterService(connectionPool: PostgresConnectionPool) extends QuillContext(connectionPool) with AnimalShelterService {
  private val animalsTable = quote(querySchema[TapirAnimalRow]("shelter.animals"))

  override def register(animal: TapirAnimal): Task[TapirAnimal] =
    ZIO.succeed(TapirAnimalRow.fromDomain(animal)).flatMap(persist).map(_.toDomain)

  private def persist(entity: TapirAnimalRow): Task[TapirAnimalRow] =
    executeQueryToTask(insertOrUpdate(entity)(_))

  private def insertOrUpdate(entity: TapirAnimalRow)(implicit executionContext: ExecutionContext) =
    run(
      animalsTable.insert(lift(entity))
        .onConflictUpdate(_.id)(
          (t, e) => t.name -> e.name,
          (t, e) => t.registered -> e.registered,
          (t, e) => t.size -> e.size,
          (t, e) => t.adoptionStatus -> e.adoptionStatus
        ).returning[TapirAnimalRow](r => r)
    )
}
