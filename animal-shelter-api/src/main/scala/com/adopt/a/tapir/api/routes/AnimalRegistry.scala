package com.adopt.a.tapir.api.routes

import com.adopt.a.tapir.api.AnimalShelterService
import com.adopt.a.tapir.api.endpoints.AnimalRegistry.route
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir._
import zio._

object AnimalRegistry {

  type Effect[T] = RIO[AnimalShelterService, T]

  def routes: HttpRoutes[Effect] =
    ZHttp4sServerInterpreter()
      .from(route.zServerLogic(AnimalShelterService.register))
      .toRoutes

  def swaggerRoutes: HttpRoutes[Effect] =
    ZHttp4sServerInterpreter()
      .from(SwaggerInterpreter().fromEndpoints[Effect](List(route), "Animal shelter", "1.0"))
      .toRoutes
}
