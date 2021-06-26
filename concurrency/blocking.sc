import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import UserStore._

object UserStore {
  case class User(name: String)

  def deserializeUser(raw: String): User = User(raw)

  def fetch(id: String): Future[String] = Future.successful(s"Mr Test$id")
}

def fetchUserNameBlocking(id: String): String = {
  // blocks this thread until fetch completes
  val raw: String = Await.result(
    fetch(id),
    2.seconds
  )
  deserializeUser(raw).name
}

// No blocking, but how do we use this?
def fetchUserNameNonBlocking(id: String): Future[String] =
  fetch(id)
    .map(deserializeUser)
    .map(user => user.name)

print("fetchUserNameBlocking: ")
println(fetchUserNameBlocking("1"))

print("fetchUserNameNonBlocking: ")
println(fetchUserNameNonBlocking("2"))
