(ns clojure-aws-sdk.aws.s3
  (:import (com.amazonaws.services.s3 AmazonS3ClientBuilder AmazonS3Client)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider

(def client (AmazonS3ClientBuilder/defaultClient))

(defn object-exists?
  [^AmazonS3Client client bucket key]
  (.doesObjectExist client bucket key))
