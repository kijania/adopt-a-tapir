package com.adopt.a.tapir.api.endpoints

import com.adopt.a.tapir.api.model.{ErrorInfo, TapirAnimalRequest, TapirAnimalResponse}
import com.adopt.a.tapir.api.parameters.{Page, PerPage}
import sttp.tapir.PublicEndpoint
import sttp.tapir.generic.auto.SchemaDerivation
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir._

object AnimalRegistry extends SchemaDerivation {

  val registerAnimalEndpoint: PublicEndpoint[TapirAnimalRequest, ErrorInfo, TapirAnimalResponse, Any] =
    endpoint
      .in("animal-shelter" / "animals")
      .post
      .summary("Register new tapir in the animal shelter")
      .name("Add tapir")
      .in(TapirAnimalRequest.asRequest)
      .out(TapirAnimalResponse.asResponse)
      .errorOut(stringBody.map(ErrorInfo)(_.message))

  val getAnimalsEndpoint: PublicEndpoint[(PerPage, Page), ErrorInfo, Seq[TapirAnimalResponse], Any] =
    endpoint
      .in("animal-shelter" / "animals")
      .get
      .summary("Get tapirs registered in the animal shelter")
      .name("Get tapirs")
      .in(PerPage.asQuery)
      .in(Page.asQuery)
      .out(jsonBody[Seq[TapirAnimalResponse]])
      .errorOut(stringBody.map(ErrorInfo)(_.message))
}
