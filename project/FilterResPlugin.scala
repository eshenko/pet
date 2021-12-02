import sbt.PluginTrigger.AllRequirements
import sbt.{AutoPlugin, PluginTrigger, settingKey, taskKey}

import java.io.{File, PrintWriter}
import scala.io.Source

object FilterResPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = AllRequirements

  sealed trait Keys {
    lazy val replace = taskKey[Unit]("")
    lazy val dictionary = settingKey[Map[String, String]]("")
  }

  object autoImport extends Keys

  def replaceVars(res: File, dictionary: Map[String, String]): Unit = {
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


