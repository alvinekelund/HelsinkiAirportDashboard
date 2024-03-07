ThisBuild / scalaVersion := "3.3.0"


libraryDependencies += "org.scalafx" % "scalafx_3" % "20.0.0-R31"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.5.3"
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.3.7"
libraryDependencies += "com.softwaremill.sttp.client3" %% "circe" % "3.3.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0"


lazy val root = (project in file("."))
  .settings(
    name := "OS2Projekti",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.2",
      "io.circe" %% "circe-generic" % "0.14.2",
      "io.circe" %% "circe-parser" % "0.14.2"


  )
 )

