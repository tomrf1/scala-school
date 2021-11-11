### Corporate Connections

Build an API for looking up corporate connections using the Companies House api.

The GET endpoint should take an officerId and return a JSON array of connected officerIds.
To do this, the backend must find their companies, then find all other officers of those companies.

#### Companies House API
E.g. for officerId `aEdQfmEjiBuB7tLwOP_Wfg-JA-8`, this curl request returns their companies:

`curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/officers/aEdQfmEjiBuB7tLwOP_Wfg-JA-8/appointments`

And for companyId `03114488`, this curl request returns its officers:

`curl -XGET -u <api-key>: https://api.company-information.service.gov.uk/company/03114488/officers`

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
