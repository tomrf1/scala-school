object UserStore {
  type UserId = String

  def getUser(email: String): Option[UserId] = if (email == "a@gu.com") Some("1") else None
  def createUser(email: String): Option[UserId] = Some("2")
}

def validateEmail(email: String): Option[String] = if (email.contains("@")) Some(email) else None

def executePayment(amount: Int, userId: String): Boolean = if (amount > 0) true else false

case class Request(amount: Int, email: String)
type HttpStatus = Int

/**
 * 1. validate the email
 * 2. get or create a user ID
 * 3. execute the payment
 * 4. return a 200 status on success
 */
def handleRequest(request: Request): HttpStatus = {
  import UserStore._
  // TODO
}

assert(handleRequest(Request(1,"a@gu.com")) == 200)
assert(handleRequest(Request(1,"b@gu.com")) == 200)
assert(handleRequest(Request(1,"bgu.com")) == 500)
assert(handleRequest(Request(0,"b@gu.com")) == 500)
