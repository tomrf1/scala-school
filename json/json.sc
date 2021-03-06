// A json model in scala
sealed trait Json
case class JsonBool(b: Boolean) extends Json
case class JsonNumber(d: Double) extends Json
case class JsonString(s: String) extends Json
case class JsonArray(arr: List[Json]) extends Json
case class JsonObject(map: Map[String,Json]) extends Json
case class JsonNull() extends Json

object Json {
  // We'd need to parse stringified json into this Json model. E.g.
  def parseJson(json: String): Either[String, Json] = ???
  // Circe uses jawn (https://github.com/typelevel/jawn) for parsing
}

/**
 * For encoding and decoding we use "type classes".
 * A type class is a way of defining an interface for various types, without having to use inheritance.
 */

// the type class for encoding types to json
trait Encoder[A] {
  def apply(a: A): Json
}

// the type class for decoding types from json
trait Decoder[A] {
  def apply(json: Json): Either[String,A]
}

/**
 * We implement type class 'instances' and leverage scala's implicit keyword to make use of them.
 * E.g. we can define instances of the Encoder type class for String, Boolean, etc.
 */

object Encoder {
  // instances of the Encoder type class:
  implicit val stringEncoder: Encoder[String] = (s: String) => JsonString(s)
  implicit val booleanEncoder: Encoder[Boolean] = (b: Boolean) => JsonBool(b)
  // we can even define instances for nested types, e.g. for an Option[A], where A must also have an Encoder
  // (this is called 'recursive type class inference')
  implicit def optionEncoder[A](implicit enc: Encoder[A]): Encoder[Option[A]] = {
    case Some(a) => enc(a)
    case None => JsonNull()
  }

  // a "type class interface" for Encoder:
  def encode[A](a: A)(implicit encoder: Encoder[A]): Json = encoder(a)
}

// When we call `encode`, scala finds the right implicit Encoder instance
val jsonString: Json = Encoder.encode("a string")
val jsonBool: Json = Encoder.encode(true)

val maybeBool: Option[Boolean] = Some(true)
val jsonMaybeBool: Json = Encoder.encode(maybeBool) // uses optionEncoder and booleanEncoder


object Decoder {
  // instances of the Decoder type class:
  implicit val stringDecoder: Decoder[String] = {
    case JsonString(s) => Right(s)
    case other => Left(s"Not a string: $other")
  }
  implicit val booleanDecoder: Decoder[Boolean] = {
    case JsonBool(b) => Right(b)
    case other => Left(s"Not a boolean: $other")
  }
  implicit def optionDecoder[A](implicit dec: Decoder[A]): Decoder[Option[A]] = {
    case JsonNull() => Right(None)
    case other => dec(other).map(Some(_))
  }

  // a "type class interface" for Decoder:
  def decode[A](json: Json)(implicit decoder: Decoder[A]): Either[String, A] = decoder(json)
}

// When we call `decode`, scala finds the right implicit Decoder instance
val string: Either[String, String] = Decoder.decode[String](jsonString)
val bool: Either[String, Boolean] = Decoder.decode[Boolean](jsonBool)
val maybeString: Either[String, Option[String]] = Decoder.decode[Option[String]](JsonString("a string")) // uses optionDecoder and stringDecoder
