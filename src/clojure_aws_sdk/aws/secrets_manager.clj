(ns clojure-aws-sdk.aws.secrets-manager
  (:import (com.amazonaws.services.secretsmanager AWSSecretsManagerClientBuilder AWSSecretsManagerClient)
           (com.amazonaws.services.secretsmanager.model GetSecretValueRequest)))

;; TODO: implement support for "standard" client
(def client (AWSSecretsManagerClientBuilder/defaultClient))

;; TODO: implement the following Amazon Secrets Manager functionality
;; create-secret
;; delete-secret
;; describe-secret
;; get-secret-value
;; list-secrets
;; update-secret

(defn get-secret-value
  "Takes the ARN or name of the secret to retrieve.
  Returns the content of the secret"
  [^AWSSecretsManagerClient client ^String secret-id]
  (let [request (doto (GetSecretValueRequest.)
                  .withSecretId secret-id)]
    (.getSecretString (.getSecretValue client request))))
