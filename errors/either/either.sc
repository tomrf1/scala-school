val either1: Either[String,Int] = Right(1)
val either2: Either[String,Int] = Left("error!")

// map
val doubled: Either[String,Int] = either1.map(n => n*2)

// flatMap
val add: Either[String,Int] = either1.flatMap(n1 => either2.map(n2 => n1 + n2))

val addForYield: Either[String,Int] = for {
  n1 <- either1
  n2 <- either2
} yield n1 + n2

// escaping the either
val result1: Int = either1 match {
  case Right(n) => n
  case Left(err) =>
    println(err)
    0
}

val result2: Int = either1.getOrElse(0)

val result3: Int = either1.fold(
  err => {
    println(err)
    0
  },
  n => n
)
