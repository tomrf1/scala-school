/**
 * Creating an option
 */
val option1: Option[Int] = Some(1)
val option2: Option[Int] = None


/**
 * Transforming the option
 */
// map - transform the value inside the option, if it exists
val doubled: Option[Int] = option1.map(n => n*2)

// flatmap - transform the value inside, with a function that returns an option
val add: Option[Int] = option1.flatMap(n1 => option2.map(n2 => n1 + n2))

val addForYield: Option[Int] = for {
  n1 <- option1 // flatmap
  n2 <- option2 // map
} yield n1 + n2 // inside the map

// orElse
val maybe: Option[Int] = option1.orElse(option2)


/**
 * Escaping the option
 */
val result1: Int = option1 match {
  case Some(n) => n
  case None => 0
}

val result2: Int = option1.getOrElse(0)

val result3: Int = option1.fold(0)(n => n)
