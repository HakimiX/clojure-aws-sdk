(ns clojure-aws-sdk.aws.ssm
  (:import (com.amazonaws.services.simplesystemsmanagement.model GetParameterRequest)
           (com.amazonaws.services.simplesystemsmanagement AWSSimpleSystemsManagement AWSSimpleSystemsManagementClientBuilder)
           (com.amazonaws.auth BasicSessionCredentials BasicAWSCredentials)))

(defn build-client
  [creds]
  (let [aws-creds
        (if (:token creds)
          (BasicSessionCredentials. (:access-key creds) (:secret-key creds) (:token creds))
          (BasicAWSCredentials. (:access-key creds) (:secret-key creds)))]
    (-> (AWSSimpleSystemsManagementClientBuilder/standard)
        (.withCredentials aws-creds)
        .build)))

(defn get-parameter
  [^AWSSimpleSystemsManagement client paramter]
  (let [ssm-request (-> (GetParameterRequest.)
                        (.withName paramter)
                        (.withWithDecryption true))]
    (-> (.getParameter client ssm-request)
        (.getParameter)
        (.getValue))))
