package com.adopt.a.tapir.api

import cats.syntax.all._
import com.adopt.a.tapir.api.routes.Adoption
import com.adopt.a.tapir.api.routes.Adoption.swaggerRoutes
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import zio._
import zio.interop.catz._

object AnimalShelterApiApp extends ZIOAppDefault {

  def routes: HttpRoutes[Task] = Adoption.routes

  val serve: Task[Unit] = {
    ZIO.executor.flatMap { executor =>
      BlazeServerBuilder[Task]
        .withExecutionContext(executor.asExecutionContext)
        .bindHttp(8080, "localhost")
        .withHttpApp(Router("/" -> (routes <+> swaggerRoutes)).orNotFound)
        .serve
        .compile
        .drain
    }
  }

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZIO.logInfo("animal-service run") *>
      serve.exitCode
  }
}
