# apache-http-client-clj

Provides a ring compatible http interface that uses Apache HttpClient
as implementation

## Usage

```clojure
(def request-map
  {:request-method :get
   :scheme :http
   :uri "/user-agent"
   :server-name "httpbin.org"
   :server-port 80})

(apache-client/request request-map)
; ==>
{:headers
 {"Access-Control-Allow-Origin" "*",
  "Date" "Fri, 12 Sep 2014 04:20:15 GMT",
  "Access-Control-Allow-Credentials" "true",
  "Content-Length" "56",
  "Server" "gunicorn/18.0",
  "Content-Type" "application/json",
  "Connection" "keep-alive"},
 :status 200
 :body #<EofSensorInputStream org.apache.http.conn.EofSensorInputStream@3f14b553>}
```

## License

Copyright © 2014 WalmartLabs

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
