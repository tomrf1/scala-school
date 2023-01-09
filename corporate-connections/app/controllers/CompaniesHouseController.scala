package controllers

import play.api.mvc._
import services.CompaniesHouseService

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseController(components: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getAppointmentsProxy(officerId: String) = Action.async {
    Future {
      Ok(s"hello $officerId")
    }
  }
}
