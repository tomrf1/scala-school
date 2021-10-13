import scala.util.{Failure, Success, Try}

/**
 * Creating a Try
 */
try {
  1
} catch { case err: Throwable =>
  println("error!")
}

val try1: Try[Int] = Success(1)
val try2: Try[Int] = Failure(new Exception("error!"))
val try3: Try[Int] = Try(throw new Exception("error!"))


/**
 * Transforming the Try
 */
// map
val doubled: Try[Int] = try1.map(n => n*2)

//flatmap
val add: Try[Int] = try1.flatMap(n1 => try2.map(n2 => n1+n2))

val addForYield: Try[Int] = for {
  n1 <- try1 // flatmap
  n2 <- try2 // map
} yield n1 + n2


/**
 * Escaping the Try
 */
val result: Int = try1 match {
  case Success(n) => n
  case Failure(err) =>
    println(err.getMessage)
    0
}
