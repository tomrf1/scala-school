package wiring

import play.api.ApplicationLoader.Context
import play.api._

class AppLoader extends ApplicationLoader {

  override def load(context: Context): Application = {
    try {
      new AppComponents(context).application
    } catch {
      case err: Throwable => {
        println("Could not start application", err)
        throw err
      }
    }
  }
}
