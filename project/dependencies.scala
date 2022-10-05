import sbt._
import versions._

object dependencies {
  lazy val test: Seq[ModuleID] = Seq(
    "org.scalatest"     %% "scalatest"                                            % versions.ScalaTest,
    "org.scalacheck"    %% "scalacheck"                                           % versions.ScalaCheck,
    "org.scalatestplus" %% ("scalacheck-" + versions.ScalaCheck.majorHyphenMinor) % versions.ScalaTestPlusScalaCheck,
    "dev.zio"           %% "zio-test"                                             % versions.ZIO
  ).map(_ % Test)

  lazy val zio: Seq[ModuleID]  = Seq(
    "dev.zio"       %% "zio"              % versions.ZIO,
    "dev.zio"       %% "zio-interop-cats" % versions.ZIOInterop,
    "org.typelevel" %% "cats-effect-std"  % "3.2.9" // required by zio-interop
  )

  lazy val http4s: Seq[ModuleID] = Seq(
    "org.http4s" %% "http4s-core"         % versions.Http4s,
    "org.http4s" %% "http4s-dsl"          % versions.Http4s,
    "org.http4s" %% "http4s-circe"        % versions.Http4s,
    "org.http4s" %% "http4s-blaze-server" % versions.Http4sServer
  )

  lazy val tapir: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core"              % versions.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-http4s-server-zio" % versions.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-zio"               % versions.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe"        % versions.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % versions.Tapir
  )

  lazy val enumeration: Seq[ModuleID] = Seq(
    "com.beachape" %% "enumeratum-circe" % versions.Enumeration,
    "com.beachape" %% "enumeratum-quill" % versions.Enumeration,
    "com.beachape" %% "enumeratum"       % versions.Enumeration
  )

  lazy val quill: Seq[ModuleID] = Seq(
    "io.getquill"   %% "quill-jdbc-zio" % versions.Quill,
    "org.postgresql" % "postgresql"     % versions.PostgreSQL
  )

  lazy val config: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % versions.PureConfig,
    "com.typesafe"           % "config"     % versions.TypesafeConfig
  )
}
