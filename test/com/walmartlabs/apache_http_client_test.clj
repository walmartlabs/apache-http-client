(ns com.walmartlabs.apache-http-client-test
  (:require [clojure.test :refer [is deftest run-tests]]
            clj-http.client
            [com.walmartlabs.apache-http-client :as apache-client]))

(deftest t-google
  (let [request-map {:request-method :get
                     :scheme :https
                     :uri "/"
                     :server-name "www.google.com"
                     :server-port 443}
        clj-http-response (clj-http.client/request (assoc request-map
                                                     :as :stream))
        apache-response (apache-client/request request-map)]
    (is (= (select-keys clj-http-response
                        [:status :headers :body])
           apache-response))))
