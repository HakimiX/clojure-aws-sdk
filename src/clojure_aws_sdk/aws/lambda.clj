(ns clojure-aws-sdk.aws.lambda
  (:import (com.amazonaws.services.lambda AWSLambdaClient)))

(defn list-functions
  "Returns a list of lambda functions, with the version-specific configuration
  for each function."
  [^AWSLambdaClient client]
  (->> (.listFunctions client)
       (.getFunctions)
       (map #(.getFunctionName %))))
