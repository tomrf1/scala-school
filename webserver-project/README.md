### Example Play app

```
webserver-project$ sbt

...

[webserver-project] $ run

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Enter to stop and go back to the console...)
```

#### TODO
1. implement a CAPI service
2. use circe to handle CAPI's json response
3. use a twirl template to render content
4. error handling
5. custom ExecutionContext
6. AWS sdk - S3?
7. serve assets - css/js?
8. anything else?
