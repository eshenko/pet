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
      IO.copyDirectory(from.value,to.value)
      replaceVars(to.value, dictionary.value, extensions.value)
    }.value
  ))

  val stageSettings: Seq[Def.Setting[_]] = inConfig(Stage)(defaults ++ Seq(
    compile := (Compile / compile).dependsOn(replace).value,
    dictionary := Profiles.stage,
    replace := Def.task {
      IO.copyDirectory(from.value,to.value)
      replaceVars(to.value, dictionary.value, extensions.value)
    }.value
  ))

  val localSettings: Seq[Def.Setting[_]] = inConfig(Local)(defaults ++ Seq(
    compile := (Compile / compile).dependsOn(replace).value,
    dictionary := Profiles.local,
    replace := Def.task {
      IO.copyDirectory(from.value,to.value)
      replaceVars(to.value, dictionary.value, extensions.value)
    }.value
  ))

  val sharedSettings = prodSettings ++ stageSettings ++ localSettings ++ Seq(
    mainVersion := scalaVersion.value.split("""\.""").take(2).mkString("."),
    to := target.value / ("scala-" + mainVersion.value) / "classes",
    from := baseDirectory.value / "src" / "main" / "resources",
    extensions := Seq(".properties", ".conf", ".xml"),
    Compile / unmanagedResources :=
      (Compile / unmanagedResources).value.filter(_.getName.endsWith(extensions.value))
  )
}
