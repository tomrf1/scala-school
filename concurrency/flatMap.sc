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

def sleepyAdder(a: Int, b: Int): Future[Int] =
  Future {
    println(s"${a}+${b} sleeping...")
    Thread.sleep(2000)
    println(s"${a}+${b} awoke")
    a + b
  }

def sequentialAdder(): Unit = {
  // Create the Futures in sequence
  val result: Future[Int] = for {
    sum <- sleepyAdder(1, 2)
    biggerSum <- sleepyAdder(sum, 4)
  } yield biggerSum

  val finalSum = Await.result(result, 10.seconds)
  println(s"Final sum: $finalSum")
}

def parallel(): Unit = {
  // Kick off both Futures now...
  val futureA = sleepyPrint("a")
  // cannot depend on result of futureA here
  val futureB = sleepyPrint("b")

  // ...then flatMap them
  val result: Future[Unit] = for {
    _ <- futureA
    _ <- futureB
  } yield ()

  Await.result(result, 10.seconds)
}
