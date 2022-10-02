package com.adopt.a.tapir.api.endpoints

import com.adopt.a.tapir.api.model.{ErrorInfo, TapirAnimalRequest, TapirAnimalResponse}
import sttp.tapir.PublicEndpoint
import sttp.tapir.generic.auto.SchemaDerivation
import sttp.tapir.ztapir._

object AnimalRegistry extends SchemaDerivation {

  val route: PublicEndpoint[TapirAnimalRequest, ErrorInfo, TapirAnimalResponse, Any] =
    endpoint
      .in("animal-shelter" / "animal")
      .post
      .summary("Register new tapir in the animal shelter")
      .name("add tapir")
      .in(TapirAnimalRequest.asRequest)
      .out(TapirAnimalResponse.asResponse)
      .errorOut(stringBody.map(ErrorInfo)(_.message))
}
