import sbt.PluginTrigger.AllRequirements
import sbt.{AutoPlugin, IO, PluginTrigger, settingKey, taskKey}

import java.io.{File, PrintWriter}
import scala.io.Source

object FilterResPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = AllRequirements

  sealed trait Keys {
    lazy val replace = taskKey[Unit]("Task to replace variables from dictionary")
    lazy val dictionary = settingKey[Map[String, String]]("Variables dictionary")
    lazy val extensions = settingKey[Seq[String]]("Config extensions")
    lazy val mainVersion = settingKey[String]("Scala version")
    lazy val from = settingKey[File]("Resource directory")
    lazy val to = settingKey[File]("Artifact directory")
  }

  object autoImport extends Keys {
    def replaceVars(res: File, dictionary: Map[String, String], extensions: Seq[String]): Unit =
      Option(res.listFiles) foreach {
        _.foreach {
          case file if file.isFile => editFile(file, dictionary, extensions)
          case file if file.isDirectory => replaceVars(file, dictionary, extensions)
          case _ =>
        }
      }

    private def editFile(file: File, dictionary: Map[String, String], extensions: Seq[String]) = {
      if (extensions.exists(file.getName.contains)) {
        val tmp = new File(file.getPath.replace(file.getName, "tmp.conf"))
        val out = new PrintWriter(tmp)
        IO.readLines(file).map { line =>
          dictionary.foldLeft(line) { case (cur, (from, to)) =>
            cur.replace("${" + from + "}", to)
          }
        }.foreach(out.println)
        out.close()
        file.delete()
        tmp.renameTo(file)
      }
    }

/*    private def revertFile(file: File) = {
      val extension = ".backup"
      if (file.getName.contains(extension)) {
        val tmp = new File(file.getPath.replace(extension, ""))
        tmp.delete()
        file.renameTo(tmp)
      }
    }*/
  }
}


