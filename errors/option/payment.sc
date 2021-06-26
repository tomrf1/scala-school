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
}
