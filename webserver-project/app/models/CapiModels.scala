package models

import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}

case class ContentFields(
  headline: Option[String],
  standfirst: Option[String],
  byline: Option[String]
)

case class Content(id: String, webTitle: String, fields: Option[ContentFields])
object Content {
  implicit val contentEncoder = Encoder[Content]
}

case class ContentResponse(status: String, content: Content)

case class CapiResponse(response: ContentResponse)
object CapiResponse {
  implicit val capiResponseDecoder = Decoder[CapiResponse]
}
