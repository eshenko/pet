import Settings._

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.9"

lazy val root = (project in file("."))
  .configs(Prod, Stage, Local)
  .settings(
    name := "pet",
    dependencySettings,
    sharedSettings
  )
