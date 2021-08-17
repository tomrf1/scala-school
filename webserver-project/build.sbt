scalaVersion := "2.13.6"

enablePlugins(PlayScala)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  ws,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion
)
