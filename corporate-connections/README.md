### Corporate Connections

Build an API for looking up corporate connections using the Companies House api.

#### Companies House API
E.g. for officerId `aEdQfmEjiBuB7tLwOP_Wfg-JA-8`, this curl request returns their companies:

`curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/officers/aEdQfmEjiBuB7tLwOP_Wfg-JA-8/appointments`

And for companyId `03114488`, this curl request returns its officers:

`curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/company/03114488/officers`

[API Docs](https://developer-specs.company-information.service.gov.uk/companies-house-public-data-api/reference).

#### Running the webserver
Use the API_KEY environment variable to set the Companies House api key:

```
webserver-project$ API_KEY=my-api-key sbt

...

[webserver-project] $ run

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Enter to stop and go back to the console...)
```

### Task 1: proxy appointments data for an officer
Implement the `/officer/:id/appointmentsProxy` endpoint.

Pass through the unaltered Companies House API appointments response for an officerId.

E.g. for officerId `aEdQfmEjiBuB7tLwOP_Wfg-JA-8`: `https://api.company-information.service.gov.uk/officers/aEdQfmEjiBuB7tLwOP_Wfg-JA-8/appointments`

Build on the `CompaniesHouseService` class in the `services` package for talking to the Companies House API.

### Task 2: return company names for an officer
Implement the `/officer/:officerId/appointments` endpoint.

Use Circe to decode the Companies House response into Scala case classes. Use the `OfficerAppointmentResponse` model in `models/CompaniesHouseModels.scala`.

Then return just the list of companies as a JSON array.

### Task 3: return officer names for a given companyId
Implement the `/company/:companyId/officers` endpoint.

E.g. for companyId 03114488: `https://api.company-information.service.gov.uk/company/03114488/officers`

Use Circe to decode the Companies House response into Scala case classes. Use the `CompanyOfficersResponse` model in `models/CompaniesHouseModels.scala`.

### Task 4: 
Implement the `/officer/:officerId/connections` endpoint.

It should take an officerId and return a JSON array of connected officer names.

To do this, the backend must find their companies, then find all other officers of those companies.
