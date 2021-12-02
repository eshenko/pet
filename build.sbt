import Dependencies._
import FilterResPlugin.replaceVars

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.9"

lazy val Prod  = config("prod")  extend Compile describedAs "Scope to build production packages."
lazy val Stage = config("stage") extend Compile describedAs "Scope to build stage packages."
lazy val Local = config("local") extend Compile describedAs "Scope to build local packages."

lazy val root = (project in file("."))
  .configs(Prod, Stage, Local)
  .enablePlugins(FilterResPlugin)
  .settings(
    name := "pet",
    libraryDependencies ++= akkaDependencies,
    sharedSettings
  )

lazy val sharedSettings = prodSettings ++ stageSettings ++ localSettings

lazy val defaults: Seq[Def.Setting[_]] = Defaults.configSettings ++ Defaults.configTasks ++ Defaults.resourceConfigPaths
lazy val prodSettings: Seq[Def.Setting[_]] = inConfig(Prod)(defaults ++ Seq(
  compile := (Compile / compile).dependsOn(replace).value,
  dictionary := Profiles.prod,
  replace := Def.task {
    replaceVars((Compile / resourceDirectory).value, dictionary.value)
  }.value
))
lazy val stageSettings: Seq[Def.Setting[_]] = inConfig(Stage)(defaults ++ Seq(
  compile := (Compile / compile).dependsOn(replace).value,
  dictionary := Profiles.stage,
  replace := Def.task {
    replaceVars((Compile / resourceDirectory).value, dictionary.value)
  }.value
))
lazy val localSettings: Seq[Def.Setting[_]] = inConfig(Local)(defaults ++ Seq(
  compile := (Compile / compile).dependsOn(replace).value,
  dictionary := Profiles.local,
  replace := Def.task {
    replaceVars((Compile / resourceDirectory).value, dictionary.value)
  }.value
))
