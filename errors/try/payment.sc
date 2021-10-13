import scala.util.Try

object UserStore {
  type UserId = String

  def getUser(email: String): Either[String,UserId] = if (email == "a@gu.com") Right("1") else Left("Not found")
  def createUser(email: String): Either[String,UserId] = Right("2")
}

def validateEmail(email: String): Either[String,String] = if (email.contains("@")) Right(email) else Left("Invalid email")

object DodgyJavaSdk {
  // Might throw an exception!
  def executePayment(amount: Int, userId: String): Unit = if (amount > 0) () else throw new Exception("Invalid amount")
}

case class ContributionRequest(amount: Int, email: String)
type HttpStatus = Int

/**
 * 1. validate the email
 * 2. get or create a user ID
 * 3. execute the payment
 * 4. return a 200 status on success
 */
def processContribution(request: ContributionRequest): HttpStatus = {
  import UserStore._
  //TODO
}

assert(processContribution(ContributionRequest(1,"a@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"b@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"bgu.com")) == 500)
assert(processContribution(ContributionRequest(0,"b@gu.com")) == 500)
