import scala.util.Try

type UserId = String
def getUser(email: String): Either[String,UserId] = ???
def createUser(email: String): Either[String,UserId] = ???

object DodgyJavaSdk {
  // Might throw an exception!
  def executePayment(amount: Int, userId: String): Boolean = ???
}

case class Request(amount: Int, email: String)
type HttpStatus = Int

def handleRequest(request: Request): HttpStatus = {
  val result: Either[String, Boolean] = getUser(request.email)
    .orElse(createUser(request.email))
    .flatMap(id =>
      Try(DodgyJavaSdk.executePayment(request.amount, id))
        .toEither
        .left.map(_.getMessage)
    )

  result match {
    case Right(true) => 200
    case Right(false) => 500
    case Left(err) =>
      println(err)
      500
  }
}
