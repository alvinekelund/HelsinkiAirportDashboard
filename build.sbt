ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"
libraryDependencies += "org.scalafx" % "scalafx_3" % "20.0.0-R31"
lazy val root = (project in file("."))
  .settings(
    name := "OS2Projekti"

  )
