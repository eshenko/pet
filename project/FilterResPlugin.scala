import sbt.Keys._
import sbt.PluginTrigger.AllRequirements
import sbt.{AutoPlugin, Compile, Def, PluginTrigger, Setting, Zero, settingKey, taskKey}

import java.io.{File, PrintWriter}
import scala.io.Source

object FilterResPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = AllRequirements

  sealed trait Keys {
    lazy val pattern = settingKey[String]("")
    lazy val replace = taskKey[Unit]("")
    lazy val dictionary = settingKey[Map[String, String]]("")
  }

  object autoImport extends Keys

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    pattern := """\$\{((\w|\.)+)\}""",
    dictionary := Map(
      "access.properties.path" -> "local/access.conf",
      "port" -> "8081",
      "log.strategy" -> "STDOUT",
      "log.level" -> "DEBUG",
      "audit.log.save.days" -> "30",
      "warehouse.log.save.days" -> "30",
      "dataimport.log.save.days" -> "30"
    ),
    replace := Def.task {
      replaceVar((Compile / resourceDirectory).value, dictionary.value)
    }.value
  )

  def replaceVar(res: File, dictionary: Map[String, String]): Unit = {
    res.listFiles foreach { file =>
      if (file.isFile) {
        val in = Source.fromFile(file)
        val tmp = new File(file.getPath.replace(file.getName, "tmp.conf"))
        val out = new PrintWriter(tmp)
        in.getLines.map { line =>
            dictionary.foldLeft(line) { case (cur, (from, to)) =>
              cur.replace("${" + from + "}", to)
            }
        }.foreach(out.println)
        in.close()
        out.close()
        file.delete()
        tmp.renameTo(file)
      }
    }
  }
}


