package com.adopt.a.tapir.api.endpoints

import com.adopt.a.tapir.api.model.TapirAnimal
import sttp.model.StatusCode
import sttp.tapir.PublicEndpoint
import sttp.tapir.generic.auto.SchemaDerivation
import sttp.tapir.ztapir._

object AnimalRegistry extends SchemaDerivation {

  val route: PublicEndpoint[TapirAnimal, Unit, Unit, Any] =
    endpoint
      .in("animal-shelter" / "animal")
      .post
      .summary("Register new tapir in the animal shelter")
      .name("add tapir")
      .in(TapirAnimal.asRequest)
      .out(statusCode(StatusCode.Created))
}
