(ns clojure-aws-sdk.secrets-manager
  (:import (com.amazonaws.services.secretsmanager AWSSecretsManagerClientBuilder)))

;; TODO: implement support for "standard" client
(def client (AWSSecretsManagerClientBuilder/defaultClient))

;; TODO: implement the following Amazon Secrets Manager functionality
;; create-secret
;; delete-secret
;; describe-secret
;; get-secret-value
;; list-secrets
;; update-secret

