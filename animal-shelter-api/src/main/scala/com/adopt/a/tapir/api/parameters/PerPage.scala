package com.adopt.a.tapir.api.parameters

import sttp.tapir.{Codec, EndpointInput, query}
import sttp.tapir.Codec.PlainCodec

case class PerPage(value: Int)

object PerPage {
  implicit val codec: PlainCodec[PerPage] =
    Codec.int.map(PerPage(_))(_.value)

  val asQuery: EndpointInput.Query[PerPage] = query[PerPage]("perPage")
    .description("Number of entities displayed on one page")
}
