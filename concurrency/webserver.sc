import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 *  A much simplified version of how the Play framework defines request handlers.
 *  Play expects you to give it a Future, and is very good at async non-blocking handling of requests
 */

case class Request(headers: Map[String,String], path: String)
case class Result(status: Int, body: String)

type Action = Request => Future[Result]

class MyController {
  def helloWorld: Action = request => Future {
    Result(status = 200, body = "hello world!")
  }
}
