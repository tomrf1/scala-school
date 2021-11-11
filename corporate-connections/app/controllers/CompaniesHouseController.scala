package controllers

import play.api.mvc._
import services.CompaniesHouseService

import scala.concurrent.{ExecutionContext, Future}

class CompaniesHouseController(components: ControllerComponents, companiesHouseService: CompaniesHouseService)(implicit ec: ExecutionContext) extends AbstractController(components) {
  def getConnections(officerId: String) = Action.async {
    Future {
      Ok(s"hello $officerId")
    }
  }
}
