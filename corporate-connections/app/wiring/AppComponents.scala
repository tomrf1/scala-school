package wiring

import play.api.routing.Router
import play.api.ApplicationLoader.Context
import play.api.{BuiltInComponentsFromContext, NoHttpFiltersComponents}
import controllers._
import play.api.libs.ws.ahc.AhcWSComponents
import router.Routes
import services.CompaniesHouseService

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) with NoHttpFiltersComponents with AssetsComponents with AhcWSComponents {
  val apiKey = System.getenv("API_KEY")
  val companiesHouseService = new CompaniesHouseService(wsClient, apiKey)

  override lazy val router: Router = new Routes(
    httpErrorHandler,
    new CompaniesHouseController(controllerComponents, companiesHouseService)
  )
}
