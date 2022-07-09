(ns clojure-aws-sdk.aws.lambda
  (:import (com.amazonaws.services.lambda AWSLambdaClient)
           (com.amazonaws ClientConfiguration)
           (com.amazonaws.auth BasicAWSCredentials DefaultAWSCredentialsProviderChain)))

(defn lambda-client*
  "Creates an AWSLambdaClient instance from a map of credentials and client configuration
  parameters such as connection timeout and proxy settings."
  [{:keys [access-key secret-key endpoint]}]
  (let [creds (if (and access-key secret-key)
                (BasicAWSCredentials. access-key secret-key)
                (DefaultAWSCredentialsProviderChain.))
        client (AWSLambdaClient. creds)]
    (if endpoint (.setEndpoint client endpoint))
    client))

(defn list-functions
  "Returns a list of lambda functions, with the version-specific configuration
  for each function."
  [^AWSLambdaClient client]
  (->> (.listFunctions client)
       #_(.getFunctions)
       #_(map #(.getFunctionName %))))
