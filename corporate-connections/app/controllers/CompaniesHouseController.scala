package controllers

import play.api.mvc._
import services.CompaniesHouseService
import scala.concurrent.{ExecutionContext, Future}
import io.circe.syntax._
import models.OfficerAppointmentResponse.companiesEncoder

class CompaniesHouseController(components: ControllerComponents, companiesHouseService: CompaniesHouseService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getAppointments(officerId: String) = Action.async {
    companiesHouseService.getAppointmentsRaw(officerId).map(Ok(_))
  }

  def getConnections(officerId: String) = Action.async {
      companiesHouseService.getCompanies(officerId).map { 
        case Right(companies) => Ok(companies.asJson.noSpaces)
        case Left(error) => InternalServerError(error.getMessage())
      }
  }
}
