object UserStore {
  type UserId = String

  def getUser(email: String): Either[String,UserId] = if (email == "a@gu.com") Right("1") else Left("Not found")
  def createUser(email: String): Either[String,UserId] = Right("2")
}

def validateEmail(email: String): Either[String,String] = if (email.contains("@")) Right(email) else Left("Invalid email")

def executePayment(amount: Int, userId: String): Either[String,Unit] = if (amount > 0) Right(()) else Left("Invalid amount")

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

  val result: Either[String, Unit] = for {
    email <- validateEmail(request.email)
    userId <- getUser(request.email).orElse(createUser(request.email))
    execution <- executePayment(request.amount, userId)
  } yield execution

  result match {
    case Right(_) => 200
    case Left(err) =>
      println(err)
      500
  }
}

assert(processContribution(ContributionRequest(1,"a@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"b@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"bgu.com")) == 500)
assert(processContribution(ContributionRequest(0,"b@gu.com")) == 500)
