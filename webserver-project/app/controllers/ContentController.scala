package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import services.CapiService

import scala.concurrent.ExecutionContext

class ContentController(components: ControllerComponents, capiService: CapiService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def content(path: String) = Action.async {
    capiService
      .fetchContent(path)
      .map(content => Ok(content))
      .recover { case error =>
        println(error)
        InternalServerError(error.getMessage)
      }
  }
}
