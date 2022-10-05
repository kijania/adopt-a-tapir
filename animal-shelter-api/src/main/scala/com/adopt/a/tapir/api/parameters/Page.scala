package com.adopt.a.tapir.api.parameters

import sttp.tapir.Codec.PlainCodec
import sttp.tapir.{Codec, EndpointInput, query}

case class Page(value: Int)

object Page {
  implicit val codec: PlainCodec[Page] =
    Codec.int.map(Page(_))(_.value)

  val asQuery: EndpointInput.Query[Page] = query[Page]("page")
    .description("Number of page displayed")
}
