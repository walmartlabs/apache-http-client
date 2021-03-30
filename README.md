***
# NOTICE:

## This repository has been archived and is not supported.

[![No Maintenance Intended](http://unmaintained.tech/badge.svg)](http://unmaintained.tech/)
***
NOTICE: SUPPORT FOR THIS PROJECT HAS ENDED 

This projected was owned and maintained by Walmart. This project has reached its end of life and Walmart no longer supports this project.

We will no longer be monitoring the issues for this project or reviewing pull requests. You are free to continue using this project under the license terms or forks of this project at your own risk. This project is no longer subject to Walmart's bug bounty program or other security monitoring.


## Actions you can take

We recommend you take the following action:

  * Review any configuration files used for build automation and make appropriate updates to remove or replace this project
  * Notify other members of your team and/or organization of this change
  * Notify your security team to help you evaluate alternative options

## Forking and transition of ownership

For [security reasons](https://www.theregister.co.uk/2018/11/26/npm_repo_bitcoin_stealer/), Walmart does not transfer the ownership of our primary repos on Github or other platforms to other individuals/organizations. Further, we do not transfer ownership of packages for public package management systems.

If you would like to fork this package and continue development, you should choose a new name for the project and create your own packages, build automation, etc.

Please review the licensing terms of this project, which continue to be in effect even after decommission.

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
