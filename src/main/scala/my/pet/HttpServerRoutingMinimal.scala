package my.pet

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import my.pet.controller.FirstController
import java.util.Properties

object HttpServerRoutingMinimal extends App {

  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext

  val properties = new Properties
  properties.load(this.getClass.getResourceAsStream("/app.properties"))

  val port = properties.getProperty("port").toInt

  val route = new FirstController().route
  val bindingFuture = Http().newServerAt("localhost", port).bind(route)

  println(s"Server now online. Please navigate to http://localhost:$port/hello")
}
