package com.adopt.a.tapir.persistence

import com.adopt.a.tapir.persistence.QuillContext._
import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import io.getquill._
import io.getquill.context.jasync._
import zio._

import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.ListHasAsScala

class QuillContext(val connectionPool: PostgresConnectionPool)
    extends JAsyncContext[PostgresDialect, Literal, PostgreSQLConnection](PostgresDialect, Literal, connectionPool)
    with UUIDObjectEncoding {
  def executeQueryToTask[Q](run: ExecutionContext => Future[Q]): Task[Q] = ZIO.fromFuture(run)

  override protected def extractActionResult[O](returningAction: ReturnAction, extractor: Extractor[O])(result: QueryResult): O =
    result.getRows.asScala.headOption.map(extractor).getOrElse(throw NoRowsFoundException)
}

object QuillContext {
  type PostgresConnectionPool = ConnectionPool[PostgreSQLConnection]
  case object NoRowsFoundException extends IllegalStateException
}
