(ns clojure-aws-sdk.ssm
  (:import (com.amazonaws.services.simplesystemsmanagement.model GetParameterRequest)
           (com.amazonaws.services.simplesystemsmanagement AWSSimpleSystemsManagement)))

(defn get-parameter
  [^AWSSimpleSystemsManagement client paramter]
  (let [ssm-request (-> (GetParameterRequest.)
                        (.withName paramter)
                        (.withWithDecryption true))]
    (-> (.getParameter client ssm-request)
        (.getParameter)
        (.getValue))))
