import Dependencies._
import FilterResPlugin.autoImport._
import sbt.Keys._
import sbt._

object Settings {
  val Prod  = config("prod")  extend Compile describedAs "Scope to build production packages."
  val Stage = config("stage") extend Compile describedAs "Scope to build stage packages."
  val Local = config("local") extend Compile describedAs "Scope to build local packages."

  val dependencySettings = libraryDependencies ++= akkaDependencies

  val defaults: Seq[Def.Setting[_]] = Defaults.configSettings ++ Defaults.configTasks ++ Defaults.resourceConfigPaths

  val prodSettings: Seq[Def.Setting[_]] = inConfig(Prod)(defaults ++ Seq(
    compile := (Compile / compile).dependsOn(replace).value,
    dictionary := Profiles.prod,
    replace := Def.task {
      replaceVars((Compile / resourceDirectory).value, dictionary.value)
    }.value
  ))

  val stageSettings: Seq[Def.Setting[_]] = inConfig(Stage)(defaults ++ Seq(
    compile := (Compile / compile).dependsOn(replace).value,
    dictionary := Profiles.stage,
    replace := Def.task {
      replaceVars((Compile / resourceDirectory).value, dictionary.value)
    }.value
  ))

  val localSettings: Seq[Def.Setting[_]] = inConfig(Local)(defaults ++ Seq(
    compile := (Compile / compile).dependsOn(replace).value,
    dictionary := Profiles.local,
    replace := Def.task {
      replaceVars((Compile / resourceDirectory).value, dictionary.value)
    }.value
  ))

  val profileSettings = prodSettings ++ stageSettings ++ localSettings
}
