import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

implicit def ec = ExecutionContext.fromExecutor(
  new java.util.concurrent.ForkJoinPool(10)
)

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
    sum <- sleepyAdder(1, 1)
    biggerSum <- sleepyAdder(sum, 1)
  } yield biggerSum

  val finalSum = Await.result(result, 10.seconds)
  println(s"Final sum: $finalSum")
}

def parallelAdder(): Unit = {
  // Kick off both Futures now...
  val futureA = sleepyAdder(1, 1)
  // cannot depend on result of futureA here
  val futureB = sleepyAdder(1, 1)

  // ...then flatMap them
  val result: Future[Int] = for {
    _ <- futureA
    anotherSum <- futureB
  } yield anotherSum

  val finalSum = Await.result(result, 10.seconds)
  println(s"Final sum: $finalSum")
}

sequentialAdder()

println
println

parallelAdder()
