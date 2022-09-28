package com.adopt.a.tapir.api.routes

import com.adopt.a.tapir.api.endpoints.Adoption.route
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir._
import zio._

object Adoption {

  def routes: HttpRoutes[Task] =
    ZHttp4sServerInterpreter()
      .from(route.zServerLogic(tapirAnimal => ZIO.logInfo(s"Create new animal: $tapirAnimal")))
      .toRoutes

  def swaggerRoutes: HttpRoutes[Task] =
    ZHttp4sServerInterpreter()
      .from(SwaggerInterpreter().fromEndpoints[Task](List(route), "Animal shelter", "1.0"))
      .toRoutes
}
