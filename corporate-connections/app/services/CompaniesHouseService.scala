package services

import play.api.libs.ws.{WSAuthScheme, WSClient}

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseService(wsClient: WSClient, apiKey: String)(implicit ec: ExecutionContext) {

  def getAppointmentsRaw(officerId: String): Future[String] = {
    wsClient
      .url(s"https://api.company-information.service.gov.uk/officers/${officerId}/appointments")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get
      .map(_.body)
  }
  
  // TODO - what should this return?
  def getCompanies(officerId: String): Unit = {
    // TODO - get list of companies linked to officer
  }

  // TODO - what should this return?
  def getOfficers(companyId: String): Unit = {
    // TODO - get list of officers linked to company
  }
}
