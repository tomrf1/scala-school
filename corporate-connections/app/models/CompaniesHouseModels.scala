package models

case class AppointedTo(company_name: String, company_number: String, company_status: String)
case class Appointment(officer_role: String, appointed_to: AppointedTo)
case class OfficerAppointmentResponse(items: List[Appointment])

object OfficerAppointmentResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/semiauto-derivation.html
}


case class Officer(name: String, officer_role: String)
case class CompanyOfficersResponse(items: List[Officer])

object CompanyOfficersResponse {
  // JSON decoding? See https://circe.github.io/circe/codecs/semiauto-derivation.html
}
