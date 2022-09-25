ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

lazy val `animal-shelter` = (project in file("."))
  .aggregate(`animal-shelter-api`)

lazy val `animal-shelter-api` = project
  .settings(
    name := "animal-shelter-api",
    libraryDependencies ++=
      dependencies.zio ++
        dependencies.http4s ++
        dependencies.test ++
        dependencies.http4s ++
        dependencies.tapir ++
        dependencies.circe ++
        dependencies.quill ++
        dependencies.others
  )

addCommandAlias("fmt", "scalafmtSbt; scalafmtAll")
