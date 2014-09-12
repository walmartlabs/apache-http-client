(ns com.walmartlabs.apache-http-client
  "Provides a ring compatible http interface that uses Apache
  HttpClient as implementation"
  (:import (java.io InputStream)
           (org.apache.http HttpHost HttpResponse Header HttpRequest)
           (org.apache.http.entity InputStreamEntity)
           (org.apache.http.impl.client HttpClients)
           (org.apache.http.message BasicHttpEntityEnclosingRequest
                                    BasicHttpRequest)))

(defn- add-request-headers
  [^BasicHttpRequest request ring-request]
  (doseq [[k v] (:headers ring-request)]
    (.addHeader request k v))
  request)

(defn- ^HttpHost ring-request->http-host
  [ring-request]
  (let [{:keys [scheme server-name server-port]} ring-request]
    (assert (string? server-name))
    (assert (or (nil? scheme) (keyword? scheme)))
    (assert (or (nil? server-port) (number? server-port)))
    (cond (and server-port scheme) (HttpHost. ^String server-name
                                              ^int server-port
                                              (name scheme))
          server-port (HttpHost. ^String server-name
                                 ^int server-port)
          :else (HttpHost. ^String server-name))))

(defn- ^HttpRequest ring-request->request
  [ring-request]
  (let [{:keys [request-method uri query-string body]} ring-request]
    (assert (keyword? request-method))
    (assert (string? uri))
    (assert (or (nil? body) (instance? InputStream body)))
    (let [method-string (clojure.string/upper-case (name request-method))
          uri-string (str uri "?" query-string)
          request (BasicHttpEntityEnclosingRequest. method-string uri-string)]
      (add-request-headers request ring-request)
      (when body
        (.setEntity request (InputStreamEntity. body)))
      request)))

(defn- assoc-header
  [header-map ^Header header]
  (let [name (.getName header)
        value (.getValue header)]
    (assoc header-map name value)))

(defn- response->headers
  [^HttpResponse response]
  (let [headers-array (.getAllHeaders response)]
    (reduce assoc-header {} headers-array)))

(defn- response->ring-response
  [^HttpResponse response]
  {:status (.getStatusCode (.getStatusLine response))
   :headers (response->headers response)
   :body (.getContent (.getEntity response))})

(defn request
  [ring-request]
  (let [http-client (HttpClients/createDefault)
        http-host (ring-request->http-host ring-request)
        http-request (ring-request->request ring-request)]
    (-> (.execute http-client http-host http-request)
        response->ring-response)))

(def unexceptional-status?
  #{200 201 202 203 204 205 206 207 300 301 302 303 307})

(defn wrap-exceptions
  [ring-response]
  (let [status (:status ring-response)]
    (if (unexceptional-status? status)
      ring-response
      (throw (ex-info (str "Http Response status [" status "]")
                      {:ring-response ring-response})))))
