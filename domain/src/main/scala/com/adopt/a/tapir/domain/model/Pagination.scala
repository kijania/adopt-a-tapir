package com.adopt.a.tapir.domain.model

import zio.{Task, ZIO}

case class Pagination(perPage: Int, offset: Int)

object Pagination {
  def parse(perPage: Int, page: Int): Task[Pagination] = {
    if (perPage <= 0 || page <= 0) {
      ZIO.fail(new IllegalArgumentException("Pagination query parameters must be positive integers"))
    } else ZIO.succeed(
      Pagination(
        perPage,
        (page - 1) * perPage
      )
    )
  }
}
