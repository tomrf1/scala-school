package wiring

import play.api.routing.Router
import play.api.ApplicationLoader.Context
import play.api.{BuiltInComponentsFromContext, NoHttpFiltersComponents}
import controllers._
import play.api.libs.ws.ahc.AhcWSComponents
import router.Routes
import services.CapiService

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) with NoHttpFiltersComponents with AssetsComponents with AhcWSComponents {
  val capiService = new CapiService(wsClient)
  override lazy val router: Router = new Routes(
    httpErrorHandler,
    new HelloWorldController(controllerComponents),
    new ContentController(controllerComponents, capiService)
  )
}
