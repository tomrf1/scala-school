package controllers

import play.api.mvc._
import services.CompaniesHouseService

import scala.concurrent.{ExecutionContext, Future}
import io.circe.syntax._
import models.{CompanyLink, Officer}
import models.CompanyOfficersResponse.connectionsEncoder
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

  private def getOfficersForCompanies(companies: List[CompanyLink]): Future[Map[String,List[Officer]]] = {
    val futureResults = Future.sequence(
      companies
        .map(_.company)
        .map(companyId =>
          companiesHouseService
            .getOfficers(companyId)
            .map(result => companyId -> result)
        )
    )
    // get rid of the Either
    futureResults.map(results => {
      results
        .collect { case (companyId, Right(officers)) => companyId -> officers }
        .toMap
    })
  }

  def getConnections(officerId: String) = Action.async {
      companiesHouseService.getCompanies(officerId).flatMap {
        case Right(companies) => getOfficersForCompanies(companies).map(officers => Ok(officers.asJson.noSpaces))
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
