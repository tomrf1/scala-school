import scala.util.{Failure, Success, Try}

try {
  1
} catch { case err: Throwable =>
  println("error!")
}

val try1: Try[Int] = Success(1)
val try2: Try[Int] = Failure(new Exception("error!"))
val try3: Try[Int] = Try(throw new Exception("error!"))

// map
val doubled: Try[Int] = try1.map(n => n*2)

// escape the try
val result: Int = try1 match {
  case Success(n) => n
  case Failure(err) =>
    println(err.getMessage)
    0
}
