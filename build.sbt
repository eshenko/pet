import Dependencies._

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.9"

/*lazy val Prod  = config("prod")  extend Compile describedAs "Scope to build production packages."
lazy val Stage = config("stage") extend Compile describedAs "Scope to build stage packages."
lazy val Local = config("local") extend Compile describedAs "Scope to build local packages."*/

lazy val root = (project in file("."))
  .enablePlugins(FilterResPlugin)
  .settings(
    name := "pet",
    Compile / compile := (Compile / compile).dependsOn(replace).value,
    libraryDependencies ++= akkaDependencies
    //sharedSettings,
    //mainClass := Some("my.pet.HttpServerRoutingMinimal")
  )

/*
lazy val sharedSettings =
  prodSettings ++ stageSettings ++ localSettings

lazy val defaults = Defaults.configSettings ++ Defaults.configTasks ++ Defaults.resourceConfigPaths

lazy val prodSettings = inConfig(Prod)(defaults ++ Seq(
  dictionary ++= Profiles.prod
))

lazy val stageSettings = inConfig(Stage)(defaults ++ Seq(
  dictionary ++= Profiles.stage
))

lazy val localSettings = inConfig(Local)(defaults ++ Seq(
  dictionary ++= Profiles.local
))*/
