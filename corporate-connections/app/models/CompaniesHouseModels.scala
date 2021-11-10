package models

case class CompanyLink(company: String)
case class Appointment(officer_role: String, links: CompanyLink)
case class OfficerAppointmentResponse(items: List[Appointment])


case class Officer(name: String, officer_role: String)
case class CompanyOfficersResponse(items: List[Officer])

// JSON decoding? See https://circe.github.io/circe/codecs/semiauto-derivation.html
