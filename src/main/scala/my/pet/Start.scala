package my.pet

import java.util.Properties
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Start extends HttpServlet {

  override def doGet(
                      req: HttpServletRequest,
                      res: HttpServletResponse
                    ): Unit = {

    res.setContentType("text/html")
    res.setCharacterEncoding("UTF-8")

    val properties = new Properties
    properties.load(this.getClass.getResourceAsStream("/app.properties"))

    val port = properties.getProperty("port").toInt

    val responseBody: String =
      s"""|<html>
         |  <body>
         |    <h1>Hello, world! PORT IS: $port</h1>
         |  </body>
         |</html>""".stripMargin
    res.getWriter.write(responseBody)
  }
}