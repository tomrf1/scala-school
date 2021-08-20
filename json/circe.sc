// Adapted from https://circe.github.io/circe/
//import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import $ivy.`io.circe::circe-core:0.14.1`
import io.circe._
import $ivy.`io.circe::circe-generic:0.14.1`
import io.circe.generic.auto._
import $ivy.`io.circe::circe-parser:0.14.1`
import io.circe.parser._
import io.circe.syntax._

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo

val foo: Foo = Qux(13, Some(14.0))

// We 'encode' a scala object as json:
val fooJson: String = foo.asJson.noSpaces
println(fooJson)

// We 'decode' json to a scala object (returns an Either because it may fail):
val decodedFoo: Either[Error, Foo] = decode[Foo](fooJson)
println(decodedFoo)

/**
 * The `asJson` function requires an implicit `Encoder[Foo]`.
 * The `decode` function requires an implicit `Decoder[Foo]`.
 * These each provide a function defining how to encode/decode for an object of type `Foo`.
 *
 * The `io.circe.generic.auto._` import implicitly provides them for us, using 'automatic derivation' at compile-time.
 *
 * But sometimes we might want to implement our own Encoders and Decoders.
 * E.g. custom Encoder/Decoder for the Qux type:
 */

val quxEncoder: Encoder[Qux] = (qux: Qux) => Json.fromFields(Seq(
  Some("i" -> Json.fromInt(qux.i)),
  qux.d.flatMap(Json.fromDouble).map(json => "d" -> json)
).flatten)

val quxDecoder: Decoder[Qux] = (cursor: io.circe.HCursor) =>
  for {
    i <- cursor.downField("i").as[Int]
    d <- cursor.downField("d").as[Option[Double]]
  } yield Qux(i, d)

val qux = Qux(1, Some(2.0))
val quxJon = qux.asJson(quxEncoder).noSpaces
println(quxJon)

println(decode[Qux](quxJon)(quxDecoder))
