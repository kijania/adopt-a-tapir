package com.adopt.a.tapir.api

import cats.syntax.all._
import com.adopt.a.tapir.api.routes.AnimalRegistry
import com.adopt.a.tapir.domain.AnimalShelterServiceNaive
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import zio._
import zio.interop.catz._

object AnimalShelterApiApp extends ZIOAppDefault {

  def routes: HttpRoutes[AnimalRegistry.Effect]        = AnimalRegistry.routes
  def swaggerRoutes: HttpRoutes[AnimalRegistry.Effect] = AnimalRegistry.swaggerRoutes

  val serve: AnimalRegistry.Effect[Unit] = {
    ZIO.executor.flatMap { executor =>
      BlazeServerBuilder[AnimalRegistry.Effect]
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
      serve.provide(AnimalShelterServiceNaive.layer).exitCode
  }
}
