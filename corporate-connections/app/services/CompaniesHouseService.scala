package services

import play.api.libs.ws.{WSAuthScheme, WSClient}

import scala.concurrent.{ExecutionContext, Future}
import io.circe.parser.decode
import models.{CompanyLink, CompanyOfficersResponse, Officer, OfficerAppointmentResponse}

import scala.util.matching.Regex

class CompaniesHouseService(wsClient: WSClient, apiKey: String)(implicit ec: ExecutionContext) {

  def getAppointmentsRaw(officerId: String): Future[String] = {
    wsClient
      .url(s"https://api.company-information.service.gov.uk/officers/${officerId}/appointments")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get
      .map(_.body)
  }

  def getCompanies(officerId: String): Future[Either[io.circe.Error, List[CompanyLink]]] = {
    val appointmentsLinkPattern: Regex = "(?<=/)[A-Za-z0-9_-]*(?=/appointments$)".r
    val matches = appointmentsLinkPattern.findFirstIn(officerId)
    getAppointmentsRaw(matches.getOrElse(officerId))
      .map(json => decode[OfficerAppointmentResponse](json).map(_.items.map(_.links)))
  }

  def getOfficers(companyId: String): Future[Either[io.circe.Error, List[Officer]]] = {
    wsClient
      .url(s"https://api.company-information.service.gov.uk/${companyId}/officers")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get
      .map(_.body)
      .map(json => decode[CompanyOfficersResponse](json).map(_.items))
  }
}
