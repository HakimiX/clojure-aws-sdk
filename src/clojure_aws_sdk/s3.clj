(ns clojure-aws-sdk.s3
  (:import (com.amazonaws.services.s3 AmazonS3ClientBuilder AmazonS3Client)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider

(def client (AmazonS3ClientBuilder/defaultClient))

(defn object-exist?
  "Takes the name of the bucket containing the object and the name
  of the object that has to be checked.
  Returns the value true if the specified object exists in the Amazon S3 bucket;
  the value false if the object doesn't exist in the specified Amazon S3 bucket"
  [^AmazonS3Client client ^String bucket-name ^String object-name]
  (.doesObjectExist client bucket-name object-name))

(defn bucket-exist?
  "Takes the name of the bucket to check.
  Returns the value true if the specified bucket exists in Amazon S3; the value false
  if there is no bucket in Amazon S3 with that name."
  [^AmazonS3Client client ^String bucket-name]
  (.doesBucketExistV2 client bucket-name))
