import scala.util.{Failure, Success, Try}

type UserId = String
def getUser(email: String): Either[String,UserId] = ???
def createUser(email: String): Either[String,UserId] = ???

object DodgyJavaSdk {
  // Might throw an exception!
  def executePayment(amount: Int, userId: String): Boolean = ???
}

case class Request(amount: Int, email: String)
type HttpStatus = Int

/**
 * get or create a user ID
 * execute the payment, handling any exceptions
 * return a 200 status on success
 */
def handleRequest(request: Request): HttpStatus = {
}
