val pattern = """\$\{((\w|\.)+)\}""".r
val lines = Seq("port = ${port}", "address = ${address}")
val dictionary = Map("port" -> "8081", "address" -> "https://...")
lines.map { line =>
  line.replace("${port}", "8081")
/*  dictionary.foldLeft(line) { case (cur, (from, to)) =>
    cur.replace("${" + from + "}", to)
  }*/
}.foreach(println)
