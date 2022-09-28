ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

addCommandAlias("fmt", "scalafmtSbt; scalafmtAll")

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
  ).enablePlugins(DockerPlugin)
  .settings(
    assembly / assemblyJarName := "animal-shelter-api.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "maven", "org.webjars", "swagger-ui", "pom.properties") =>
        MergeStrategy.singleOrError
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },
    docker := (docker dependsOn assembly).value,
    docker / dockerfile := {
      val artifact = assembly.value
      val artifactTargetPath = s"/app/${artifact.name}"
      new Dockerfile {
        from("openjdk:8-jre")
        add(artifact, artifactTargetPath)
        entryPoint("java", "-jar", artifactTargetPath)
      }
    }
  )

