(ns clojure-aws-sdk.s3
  (:require [clojure.java.io :as io])
  (:import (com.amazonaws.services.s3 AmazonS3Client)
           (com.amazonaws.services.s3.model PutObjectRequest ObjectMetadata Bucket Owner)
           (com.amazonaws.auth BasicSessionCredentials BasicAWSCredentials)
           (java.io ByteArrayInputStream)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider
;; TODO: implement S3 operations
;; Upload an Object
;; List Objects
;; Download an Object
;; Copy, Move, or Rename Objects
;; Delete an Object
;; Delete Multiple Objects at Once

(defn- client
  [creds]
  (AmazonS3Client.
    (if (:token creds)
      (BasicSessionCredentials. (:access-key creds) (:secret-key creds) (:token creds))
      (BasicAWSCredentials. (:access-key creds) (:secret-key creds)))))

(defprotocol ^{:no-doc true} Mappable
  "Convert a value into a Clojure map."
  (^{:no-doc true} to-map [x] "Return a map of the value."))

(extend-protocol Mappable
  Bucket
  (to-map [bucket]
    {:name          (.getName bucket)
     :creation-date (.getCreationDate bucket)
     :owner         (to-map (.getOwner bucket))})
  Owner
  (to-map [owner]
    {:id           (.getId owner)
     :display-name (.getDisplayName owner)})
  nil
  (to-map [_] nil))

(defn object-exist?
  [^AmazonS3Client client ^String bucket ^String object]
  (.doesObjectExist client bucket object))

(defn bucket-exist?
  [^AmazonS3Client client ^String bucket]
  (.doesBucketExistV2 client bucket))

(defn create-bucket
  [cred ^String name]
  (to-map (.craeteBucket (client cred) name)))

(defn delete-bucket
  [cred ^String bucket]
  (.deleteBucket (client cred) bucket))

(defn list-buckets
  "List all the S3 buckets for the supplied credentials. The buckets will be
  returned as a seq of maps with the following keys:
    :name          - the bucket name
    :creation-date - the date when the bucket was created
    :owner         - the owner of the bucket"
  [creds]
  (map to-map (.listBuckets (client creds))))

(defn payload->input-stream
  "Convert payload to inputstream."
  [payload]
  (io/input-stream
    (ByteArrayInputStream. (.getBytes payload))))

(defn put-object
  [^AmazonS3Client client ^String bucket ^String key payload]
  (->> (PutObjectRequest. bucket key (payload->input-stream payload) (ObjectMetadata.))
       (.putObject client)))
