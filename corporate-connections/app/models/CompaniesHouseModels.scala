package models

import io.circe._
import io.circe.generic.auto._

case class CompanyLink(company: String)
case class Appointment(officer_role: String, links: CompanyLink)
case class OfficerAppointmentResponse(items: List[Appointment])

object OfficerAppointmentResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/auto-derivation.html
  implicit val appointmentResponseDecoder = Decoder[OfficerAppointmentResponse]
  implicit val companiesEncoder = Encoder[CompanyLink]
}


case class OfficerLinks(appointments: String)
case class Links(officer: OfficerLinks)
case class Officer(name: String, officer_role: String, links: Links)
case class CompanyOfficersResponse(items: List[Officer])

object CompanyOfficersResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/auto-derivation.html
  implicit val officersResponseDecoder = Decoder[CompanyOfficersResponse]
  implicit val officersEncoder = Encoder[Set[Officer]]
}
