package controllers

import services.{CapiNotFound, CapiServerError, CapiService}
import models.Content.contentEncoder
import play.api.mvc.{AbstractController, ControllerComponents}
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class ContentController(components: ControllerComponents, capiService: CapiService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def content(path: String) = Action.async {
    capiService
      .fetchContent(path)
      .map {
        case Right(content) => Ok(content.asJson.noSpaces)
        case Left(CapiNotFound()) => NotFound(s"Not found: $path")
        case Left(CapiServerError(message)) => InternalServerError(s"Capi says: $message")
      }
      .recover { case error =>
        println(error)
        InternalServerError(error.getMessage)
      }
  }
}
