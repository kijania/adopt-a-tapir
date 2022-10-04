package com.adopt.a.tapir.api.routes

import com.adopt.a.tapir.api.AnimalShelterApiApp.AppEffect
import com.adopt.a.tapir.api.endpoints.AnimalRegistry.route
import com.adopt.a.tapir.api.model.{ErrorInfo, TapirAnimalResponse}
import com.adopt.a.tapir.domain.AnimalShelterService
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
        route.zServerLogic(request =>
          AnimalShelterService
            .register(request.toDomain)
            .map(TapirAnimalResponse.fromDomain)
            .mapError(throwable => ErrorInfo(throwable.getMessage))
        )
      )
      .toRoutes

  def swaggerRoutes: HttpRoutes[AppEffect] =
    ZHttp4sServerInterpreter()
      .from(SwaggerInterpreter().fromEndpoints[AppEffect](List(route), "Animal shelter", "1.0"))
      .toRoutes
}
