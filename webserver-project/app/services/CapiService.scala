package services

import models.{Content, CapiResponse}
import models.CapiResponse.capiResponseDecoder
import play.api.libs.ws.WSClient
import io.circe.parser.decode

import scala.concurrent.{ExecutionContext, Future}

sealed trait CapiError
case class CapiNotFound() extends CapiError
case class CapiServerError(message: String) extends CapiError
case class CapiParseError(message: String) extends CapiError

class CapiService(wsClient: WSClient)(implicit ec: ExecutionContext) {
  val url = "https://content.guardianapis.com"
  val apiKey = "test"

  private def parseResponse(body: String): Either[CapiError, Content] =
    decode[CapiResponse](body)
      .left.map(err => CapiParseError(err.getMessage))
      .map(_.response.content)

  def fetchContent(path: String): Future[Either[CapiError, Content]] =
    wsClient
      .url(s"$url/$path?show-fields=headline,standfirst,byline&api-key=$apiKey")
      .get()
      .map(response => response.status match {
        case 200 => parseResponse(response.body)
        case 404 => Left(CapiNotFound())
        case _ => Left(CapiServerError(response.statusText))
      })
}
