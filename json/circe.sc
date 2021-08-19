// Adapted from https://circe.github.io/circe/
//import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import $ivy.`io.circe::circe-core:0.14.1`, io.circe._
import $ivy.`io.circe::circe-generic:0.14.1`, io.circe.generic.auto._
import $ivy.`io.circe::circe-parser:0.14.1`, io.circe.parser._, io.circe.syntax._

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo

val foo: Foo = Qux(13, Some(14.0))

// We 'encode' a scala object as json:
val json: String = foo.asJson.noSpaces
println(json)

// We 'decode' json to a scala object
val decodedFoo: Either[Error, Foo] = decode[Foo](json)
println(decodedFoo)
