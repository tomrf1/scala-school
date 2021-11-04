import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object CompaniesHouseService {
  def getCompaniesOwned(person: Person): Future[List[Company]] = Future.successful(List(
    Company(1, "Greensill Capital"),
    Company(2, "McDonalds")
  ))
  def getOwners(company: Company): Future[List[Person]] = company match {
    case Company(_, "Greensill Capital") => Future.successful(List(
      Person("Ronald McDonald"),
      Person("Donald Duck"),
      Person("Colonel Sanders")
    ))
    case Company(_, "McDonalds") => Future.successful(List(
      Person("Ronald McDonald"),
      Person("Rhona McDonald")
    ))
  }
}

case class Person(name: String)
case class Company(id: Int, name: String)

// TASK
def getCronies(kingpin: Person): Future[List[Person]] = ???

// Ammonite doesn't like calling Await.result at the top level.
// So to test run:
//
// amm -p cony-connect.sc
//
// In REPL:
// Await.result(getCronies(Person("Ronald McDonald")), 10.seconds)
