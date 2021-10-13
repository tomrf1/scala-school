object UserStore {
  type UserId = String

  def getUser(email: String): Option[UserId] = if (email == "a@gu.com") Some("1") else None
  def createUser(email: String): Option[UserId] = if (email.contains("@")) Some("2") else None
}

def validateEmail(email: String): Option[String] = if (email.contains("@")) Some(email) else None

def executePayment(amount: Int, userId: String): Boolean = if (amount > 0) true else false

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

  val result: Option[Boolean] = for {
    email <- validateEmail(request.email)
    userId <- getUser(email)
      .orElse(createUser(request.email))
  } yield executePayment(request.amount, userId)

  result match {
    case Some(true) => 200
    case _ => 500
  }
}

assert(processContribution(ContributionRequest(1,"a@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"b@gu.com")) == 200)
assert(processContribution(ContributionRequest(1,"bgu.com")) == 500)
assert(processContribution(ContributionRequest(0,"b@gu.com")) == 500)
