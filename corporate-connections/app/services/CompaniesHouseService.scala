package services

import play.api.libs.ws.{WSAuthScheme, WSClient}

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseService(wsClient: WSClient)(implicit ec: ExecutionContext) {

  private def fetch(url: String): Future[String] = {
    wsClient
      .url(url)
      .withAuth("API KEY GOES HERE","", WSAuthScheme.BASIC)
      .get
      .map(response => response.body)
  }
}
