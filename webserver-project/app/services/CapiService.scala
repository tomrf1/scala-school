package services

import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class CapiService(wsClient: WSClient)(implicit ec: ExecutionContext) {
  val url = "https://content.guardianapis.com"
  val apiKey = "test"

  def fetchContent(path: String): Future[String] =
    wsClient
      .url(s"$url/$path?api-key=$apiKey")
      .get()
      .map(response => response.body)
}
