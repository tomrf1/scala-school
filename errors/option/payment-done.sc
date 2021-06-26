type UserId = String
def getUser(email: String): Option[UserId] = ???
def createUser(email: String): Option[UserId] = ???

def executePayment(amount: Int, userId: String): Boolean = ???

case class Request(amount: Int, email: String)
type HttpStatus = Int

/**
 * get or create a user ID
 * execute the payment
 * return a 200 status on success
 */
def handleRequest(request: Request): HttpStatus = {
  val result: Option[Boolean] = getUser(request.email)
    .orElse(createUser(request.email))
    .map(id => executePayment(request.amount, id))

  result match {
    case Some(true) => 200
    case _ => 500
  }
}
