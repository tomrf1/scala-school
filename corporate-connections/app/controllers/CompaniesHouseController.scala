package controllers

import play.api.mvc._
import services.CompaniesHouseService

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseController(components: ControllerComponents, companiesHouseService: CompaniesHouseService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getAppointments(officerId: String) = Action.async {
    //4qNZbLoGVKMeONMg6NznDe76JpnLjI1XcEXDhVeB
    companiesHouseService.getAppointmentsRaw(officerId).map(Ok(_))
  }

  def getConnections(officerId: String) = Action.async {
    Future {
      Ok(s"hello $officerId")
    }
  }
}
