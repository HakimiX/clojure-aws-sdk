(ns clojure-aws-sdk.s3
  (:import (com.amazonaws.services.s3 AmazonS3ClientBuilder AmazonS3Client)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider

(def client (AmazonS3ClientBuilder/defaultClient))

(defn object-exist?
  "Takes the name of the bucket containing the object and the name
  of the object that has to be checked."
  [^AmazonS3Client client bucket-name object-name]
  (.doesObjectExist client bucket-name object-name))

(defn bucket-exist?
  "Takes the name of the bucket to check"
  [^AmazonS3Client client bucket-name]
  (.doesBucketExistV2 client bucket-name))

