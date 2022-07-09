(ns clojure-aws-sdk.localstack
  "Localstack standard format endpoints.")

;; When using localstack, don't use the localhost endpoint (i.e. http://localhost:4566),
;; instead use the standard format endpoint (i.e. http://s3.localhost.localstack.cloud:4566),
;; because this will actually reachout to DNS and resovle into localhost IP internally.
;; Source: https://stackoverflow.com/questions/68034637/unknown-host-when-using-localstack-with-spring-cloud-aws-2-3


(def locastack-endpoints
  {:s3-endpoint  "http://s3.localhost.localstack.cloud:4566"
   :ssm-endpoint "http://ssm.localhost.localstack.cloud:4566"})
