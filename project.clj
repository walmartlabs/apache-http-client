(defproject com.walmartlabs/apache-http-client "0.1.0-SNAPSHOT"
  :description "Provides a ring compatible http interface that uses
Apache HttpClient as implementation"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.httpcomponents/httpclient "4.3.5"]]
  :profiles {:dev {:dependencies [[clj-http "1.0.0"]]}})
