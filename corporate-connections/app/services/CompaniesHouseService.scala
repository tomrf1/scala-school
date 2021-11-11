package services

import play.api.libs.ws.{WSAuthScheme, WSClient}

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseService(wsClient: WSClient, apiKey: String)(implicit ec: ExecutionContext) {
  /**
   * E.g.
   * curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/officers/aEdQfmEjiBuB7tLwOP_Wfg-JA-8/appointments
   *
   * curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/company/03114488/officers
   */

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
