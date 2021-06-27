package wiring

import play.api.routing.Router
import play.api.ApplicationLoader.Context
import play.api.{BuiltInComponentsFromContext, NoHttpFiltersComponents}
import controllers._
import router.Routes

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) with NoHttpFiltersComponents with AssetsComponents {
  override lazy val router: Router = new Routes(
    httpErrorHandler,
    new HelloWorldController(controllerComponents)
  )
}
