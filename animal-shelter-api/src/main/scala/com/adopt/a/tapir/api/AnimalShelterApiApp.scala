package com.adopt.a.tapir.api

import cats.syntax.all._
import com.adopt.a.tapir.api.routes.AnimalRegistry
import com.adopt.a.tapir.domain.AnimalShelterService
import com.adopt.a.tapir.persistence.QuillAnimalShelterService
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import zio._
import zio.interop.catz._

object AnimalShelterApiApp extends ZIOAppDefault {

  type AppEffect[T] = RIO[AnimalShelterService with AppConfig, T]

  def routes: HttpRoutes[AppEffect] = AnimalRegistry.routes.asInstanceOf[HttpRoutes[AppEffect]]

  def swaggerRoutes: HttpRoutes[AppEffect] = AnimalRegistry.swaggerRoutes

  val serve: AppEffect[Unit] =
    for {
      executor  <- ZIO.executor
      appConfig <- ZIO.service[AppConfig]
      server    <- BlazeServerBuilder[AppEffect]
                     .withExecutionContext(executor.asExecutionContext)
                     .bindHttp(appConfig.http.port, appConfig.http.host)
                     .withHttpApp(Router("/" -> (routes <+> swaggerRoutes)).orNotFound)
                     .serve
                     .compile
                     .drain
    } yield server

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZIO.logInfo("animal-service run") *>
      serve
        .provide(
          AppConfig.layer,
          QuillAnimalShelterService.layer
        )
        .exitCode
  }
}
