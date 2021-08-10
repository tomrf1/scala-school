package services

import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

sealed trait CapiError
case class CapiNotFound() extends CapiError
case class CapiServerError(message: String) extends CapiError

class CapiService(wsClient: WSClient)(implicit ec: ExecutionContext) {
  val url = "https://content.guardianapis.com"
  val apiKey = "test"

  def fetchContent(path: String): Future[Either[CapiError, String]] =
    wsClient
      .url(s"$url/$path?api-key=$apiKey")
      .get()
      .map(response => response.status match {
        case 200 => Right(response.body)
        case 404 => Left(CapiNotFound())
        case _ => Left(CapiServerError(response.statusText))
      })
}
