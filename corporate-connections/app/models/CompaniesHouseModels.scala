package models
import io.circe._
import io.circe.generic.auto._

case class CompanyLink(company: String)
case class Appointment(officer_role: String, links: CompanyLink)
case class OfficerAppointmentResponse(items: List[Appointment])

object OfficerAppointmentResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/auto-derivation.html
  implicit val companiesEncoder = Encoder[List[CompanyLink]]
  implicit val appointmentResponseDecoder = Decoder[OfficerAppointmentResponse]
}


case class Officer(name: String, officer_role: String)
case class CompanyOfficersResponse(items: List[Officer])

object CompanyOfficersResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/auto-derivation.html
  implicit val officersResponseDecoder = Decoder[CompanyOfficersResponse]
  implicit val officersEncoder = Encoder[Set[Officer]]
}
