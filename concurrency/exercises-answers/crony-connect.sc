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
def getCronies(kingpin: Person): Future[Set[Person]] = {
  import CompaniesHouseService._
  import scala.concurrent.ExecutionContext.Implicits.global

  def getOwnersForCompanies(companies: List[Company]): Future[List[Person]] = {
    // Promise.all
    val peopleForEachCompany = Future.sequence(companies.map(getOwners))
    peopleForEachCompany.map(_.flatten)
  }

  for {
    companies <- getCompaniesOwned(kingpin)
    cronies <- getOwnersForCompanies(companies)
  } yield cronies.toSet.filterNot(_ == kingpin)
}

Await.result(getCronies(Person("Ronald McDonald")), 10.seconds)
