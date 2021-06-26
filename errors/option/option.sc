val option1: Option[Int] = Some(1)
val option2: Option[Int] = None

// map
val doubled: Option[Int] = option1.map(n => n*2)

// flatmap
val add: Option[Int] = option1.flatMap(n1 => option2.map(n2 => n1 + n2))

val addForYield: Option[Int] = for {
  n1 <- option1
  n2 <- option2
} yield n1 + n2

// orElse
val maybe: Option[Int] = option1.orElse(option2)

// escaping the option
val result1: Int = option1 match {
  case Some(n) => n
  case None => 0
}

val result2: Int = option1.getOrElse(0)

val result3: Int = option1.fold(0)(n => n)
