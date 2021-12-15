import sbt.Keys.unmanagedResourceDirectories

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.9"

val Prod  = config("prod")  extend Compile describedAs "Scope to build production packages."
val Stage = config("stage") extend Compile describedAs "Scope to build stage packages."
val Local = config("local") extend Compile describedAs "Scope to build local packages."

lazy val root = (project in file("."))
  .configs(Prod, Stage, Local)
  .enablePlugins(WarPlugin)
  .settings(
    name := "pet",
    libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
  )
  .settings(inConfig(Prod)(
    WarPlugin.projectSettings ++
      Classpaths.configSettings ++
      Defaults.configTasks ++
      Defaults.resourceConfigPaths ++
      Seq(
        unmanagedResourceDirectories := Seq(baseDirectory.value / "src" / "main" / configuration.value.name / "resources")
      )
  ): _*)
  .settings(inConfig(Stage)(
    WarPlugin.projectSettings ++
      Classpaths.configSettings ++
      Defaults.configTasks ++
      Defaults.resourceConfigPaths ++
      Seq(
        unmanagedResourceDirectories := Seq(baseDirectory.value / "src" / "main" / configuration.value.name / "resources")
      )
  ): _*)
  .settings(inConfig(Local)(
    WarPlugin.projectSettings ++
      Classpaths.configSettings ++
      Defaults.configTasks ++
      Defaults.resourceConfigPaths ++
      Seq(
        unmanagedResourceDirectories := Seq(baseDirectory.value / "src" / "main" / configuration.value.name / "resources")
      )
  ): _*)
