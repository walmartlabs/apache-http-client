# apache-http-client-clj

Provides a ring compatible http interface that uses Apache HttpClient
as implementation

## Usage

```clojure
(def request-map
  {:request-method :get
   :scheme :https
   :uri "/"
   :server-name "www.google.com"
   :server-port 443})

(apache-client/request request-map)
```

## License

Copyright © 2014 WalmartLabs

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
