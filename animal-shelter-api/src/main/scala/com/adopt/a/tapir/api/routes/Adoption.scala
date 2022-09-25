package com.adopt.a.tapir.api.routes

import com.adopt.a.tapir.api.endpoints
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.ztapir._
import zio._

object Adoption {

  def route: HttpRoutes[Task] =
    ZHttp4sServerInterpreter()
      .from(endpoints.Adoption.route.zServerLogic(tapirAnimal => ZIO.logInfo(s"Create new animal: $tapirAnimal")))
      .toRoutes
}
