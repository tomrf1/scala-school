package controllers

import cats.data.EitherT
import play.api.mvc._
import services.CompaniesHouseService
import io.circe.syntax._
import models.CompanyOfficersResponse.officersEncoder
import models.{CompanyLink, Officer}

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseController(components: ControllerComponents, companiesHouseService: CompaniesHouseService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getAppointments(officerId: String) = Action.async {
    companiesHouseService
      .getAppointmentsRaw(officerId)
      .map(response => Ok(response))
  }

  private def getOfficersForCompanies(companies: List[CompanyLink]): Future[List[Officer]] = {
    val futureResults = Future.sequence(
      companies
        .map(_.company)
        .map(companiesHouseService.getOfficers)
    )
    // get rid of the Either
    futureResults.map(results => {
      results
        .collect { case Right(officers) => officers }
        .flatten
    })
  }

  def getConnections2(officerId: String) = Action.async {
    companiesHouseService
      .getCompanies(officerId)
      .flatMap {
        case Right(companies) =>
          val futureOfficers: Future[List[Officer]] = getOfficersForCompanies(companies)
          futureOfficers.map(officers => Ok(officers.toSet.asJson.spaces2))
        case Left(error) => Future.successful(InternalServerError(error.getMessage))
      }
  }

  def getConnections(officerId: String) = Action.async {
    EitherT(
      companiesHouseService
        .getCompanies(officerId)
    )
      .semiflatMap(getOfficersForCompanies)
      .value.map {
        case Right(officers) => Ok(officers.toSet.asJson.spaces2)
        case Left(error) => InternalServerError(error.getMessage)
      }
  }
}
