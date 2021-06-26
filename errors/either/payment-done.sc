type UserId = String
def getUser(email: String): Either[String,UserId] = ???
def createUser(email: String): Either[String,UserId] = ???

def executePayment(amount: Int, userId: String): Either[String,Unit] = ???

case class Request(amount: Int, email: String)
type HttpStatus = Int

def handleRequest(request: Request): HttpStatus = {
  val result: Either[String, Unit] = getUser(request.email)
    .orElse(createUser(request.email))
    .flatMap(id => executePayment(request.amount, id))

  result match {
    case Right(_) => 200
    case Left(err) =>
      println(err)
      500
  }
}
