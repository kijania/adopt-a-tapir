package com.adopt.a.tapir.api.routes

import com.adopt.a.tapir.api.AnimalShelterApiApp.AppEffect
import com.adopt.a.tapir.api.endpoints.AnimalRegistry.{getAnimalsEndpoint, registerAnimalEndpoint}
import com.adopt.a.tapir.api.model.{ErrorInfo, TapirAnimalResponse}
import com.adopt.a.tapir.domain.AnimalShelterService
import com.adopt.a.tapir.domain.model.Pagination
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir._
import zio._

object AnimalRegistry {

  type Effect[T] = RIO[AnimalShelterService, T]

  def routes: HttpRoutes[Effect] =
    ZHttp4sServerInterpreter()
      .from(
        registerAnimalEndpoint.zServerLogic(request =>
          AnimalShelterService
            .register(request.toDomain)
            .map(TapirAnimalResponse.fromDomain)
            .mapError(throwable => ErrorInfo(throwable.getMessage))
        ) ::
          getAnimalsEndpoint.zServerLogic { case (perPage, page) =>
            (
              for {
                pagination <- Pagination.parse(perPage.value, page.value)
                animals    <- AnimalShelterService.get(pagination).map(_.map(TapirAnimalResponse.fromDomain))
              } yield animals
            ).mapError(throwable => ErrorInfo(throwable.getMessage))
          } ::
          Nil
      )
      .toRoutes

  def swaggerRoutes: HttpRoutes[AppEffect] =
    ZHttp4sServerInterpreter()
      .from(
        SwaggerInterpreter().fromEndpoints[AppEffect](List(registerAnimalEndpoint, getAnimalsEndpoint), "Animal shelter", "1.0")
      )
      .toRoutes
}
