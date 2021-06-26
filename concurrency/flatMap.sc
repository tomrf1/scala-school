import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

def sleepyPrint(msg: String): Future[Unit] =
  Future {
    println(s"$msg sleeping...")
    Thread.sleep(2000)
    println(s"$msg awoke")
  }

def sequential(): Unit = {
  // Create the Futures in sequence
  val result: Future[Unit] = for {
    _ <- sleepyPrint("a")
    _ <- sleepyPrint("b")
  } yield ()

  Await.result(result, 10.seconds)
}

def parallel(): Unit = {
  // Kick off both Futures now...
  val futureA = sleepyPrint("a")
  val futureB = sleepyPrint("b")

  // ...then flatMap them
  val result: Future[Unit] = for {
    _ <- futureA
    _ <- futureB
  } yield ()

  Await.result(result, 10.seconds)
}
