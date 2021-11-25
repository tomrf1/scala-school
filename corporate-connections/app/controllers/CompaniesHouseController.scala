package controllers

import play.api.mvc._
import services.CompaniesHouseService

import scala.concurrent.{ExecutionContext, Future}
import io.circe.syntax._
import models.{CompanyLink, Officer}
import models.OfficerAppointmentResponse.companiesEncoder
import models.CompanyOfficersResponse.officersEncoder

class CompaniesHouseController(components: ControllerComponents, companiesHouseService: CompaniesHouseService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getAppointments(officerId: String) = Action.async {
    companiesHouseService.getAppointmentsRaw(officerId).map(Ok(_))
  }

  def getOfficersFromCompanies(companies: List[CompanyLink]): Future[List[Officer]] = {
    val result = companies.map {
      company => companiesHouseService.getOfficers(company.company)
    }

    val futureList = Future.sequence(result).map {
      maybeOfficers => {
        maybeOfficers.collect {
          case Right(value) => value

        }.flatten
      }
    }

    futureList
  }

  def getConnections(officerId: String) = Action.async {
      companiesHouseService.getCompanies(officerId).flatMap {
        case Right(companies) => getOfficersFromCompanies(companies).map(officers => Ok(officers.toSet.asJson.noSpaces))
        case Left(error) => Future.successful(InternalServerError(error.getMessage()))
      }
  }

  def getOfficers(companyId: String) = Action.async {
    companiesHouseService.getOfficers(s"company/${companyId}").map {
        case Right(officers) => Ok(officers.toSet.asJson.noSpaces)
        case Left(error) => InternalServerError(error.getMessage())
    }
  }
}
