package services

import play.api.libs.ws.{WSAuthScheme, WSClient}

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseService(wsClient: WSClient, apiKey: String)(implicit ec: ExecutionContext) {
  
  // TODO - what should this return?
  def getCompanies(officerId: String): Unit = {
    // TODO - get list of companies linked to officer
    wsClient
      .url("TODO")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get
  }

  // TODO - what should this return?
  def getOfficers(companyId: String): Unit = {
    // TODO - get list of officers linked to company
  }
}
