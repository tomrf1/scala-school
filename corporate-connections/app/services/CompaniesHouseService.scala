package services

import play.api.libs.ws.{WSAuthScheme, WSClient}
import io.circe.parser.decode
import scala.concurrent.{ExecutionContext, Future}
import models._

class CompaniesHouseService(wsClient: WSClient, apiKey: String)(implicit ec: ExecutionContext) {

  def getAppointmentsRaw(officerId: String): Future[String] = {
    wsClient
      .url(s"https://api.company-information.service.gov.uk/officers/${officerId}/appointments")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get()
      .map(_.body)
  }

  def getOfficersRaw(companyId: String): Future[String] = {
    wsClient
      .url(s"https://api.company-information.service.gov.uk/${companyId}/officers")
      .withAuth(apiKey,"", WSAuthScheme.BASIC)
      .get()
      .map(_.body)
  }
  
  // TODO - what should this return?
  def getCompanies(officerId: String): Future[Either[io.circe.Error, List[CompanyLink]]] = {
    // TODO - get list of companies linked to officer
    getAppointmentsRaw(officerId).map { rawAppoints => 
      decode[OfficerAppointmentResponse](rawAppoints)
        .map { appointments => 
          appointments.items.map(_.links)
        }
      }
  }

  // TODO - what should this return?
  def getOfficers(companyId: String): Future[Either[io.circe.Error, List[Officer]]] = {
    // TODO - get list of officers linked to company
    getOfficersRaw(companyId).map { rawOfficers => 
      decode[CompanyOfficersResponse](rawOfficers)
        .map(_.items)
      }
  }
}
