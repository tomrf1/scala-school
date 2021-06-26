type UserId = String
def getUser(email: String): Either[String,UserId] = ???
def createUser(email: String): Either[String,UserId] = ???

def executePayment(amount: Int, userId: String): Either[String,Unit] = ???

case class Request(amount: Int, email: String)
type HttpStatus = Int

/**
 * get or create a user ID
 * execute the payment
 * return a 200 status on success
 */
def handleRequest(request: Request): HttpStatus = {
}
