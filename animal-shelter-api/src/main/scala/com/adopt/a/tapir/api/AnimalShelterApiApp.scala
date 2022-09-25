package com.adopt.a.tapir.api

import com.adopt.a.tapir.api.routes.Adoption
import com.adopt.a.tapir.api.services.AdoptionServiceLive
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import zio._
import zio.interop.catz._

object AnimalShelterApiApp extends ZIOAppDefault {

  def routes: HttpRoutes[Task] = Adoption.route

  val serve: Task[Unit] = {
    ZIO.executor.flatMap { executor =>
      BlazeServerBuilder[Task]
        .withExecutionContext(executor.asExecutionContext)
        .bindHttp(8080, "localhost")
        .withHttpApp(routes.orNotFound)
        .serve
        .compile
        .drain
    }
  }

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    ZIO.logInfo("animal-service run") *>
      serve.provideLayer(AdoptionServiceLive.layer).exitCode
  }
}
