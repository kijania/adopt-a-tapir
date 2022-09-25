package com.adopt.a.tapir.api.endpoints

import com.adopt.a.tapir.api.model.TapirAnimal
import sttp.model.StatusCode
import sttp.tapir.Endpoint
import sttp.tapir.generic.auto.SchemaDerivation
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir._

object Adoption extends SchemaDerivation {

  val route: Endpoint[Unit, TapirAnimal, Unit, Unit, Any] =
    endpoint
      .in("animal-shelter" / "animal")
      .post
      .summary("Register new tapir in the animal shelter")
      .name("add tapir")
      .in(jsonBody[TapirAnimal])
      .out(statusCode(StatusCode.Created))
}
