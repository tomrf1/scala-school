package controllers

import play.api.mvc._

class HelloWorldController(components: ControllerComponents) extends AbstractController(components) {
  def helloworld = Action {
    Ok("hello world")
  }
}
