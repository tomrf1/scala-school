import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}


// Construct Futures which complete immediately
val future1: Future[Int] = Future.successful(1)
val future2: Future[Int] = Future.failed(new Exception("error!"))

// Construct a Future which completes sometime in the future, as determined by the given implicit ExecutionContext
val future3: Future[Int] = Future.apply(1)

// map
val doubled: Future[Int] = future1.map(n => n*2)

// flatMap
val add: Future[Int] = future1.flatMap(n1 => future2.map(n2 => n1 + n2))

val addForYield: Future[Int] = for {
  n1 <- future1
  n2 <- future2
} yield n1 + n2

// escape the future? Only if you want to block a thread...
val result: Int = Await.result(future1, 10.seconds)

// ...or side effect
future1.onComplete {
  case Success(value) => println(value)
  case Failure(err) => println(err.getMessage)
}
