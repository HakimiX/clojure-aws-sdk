(ns clojure-aws-sdk.s3
  (:import (com.amazonaws.services.s3 AmazonS3ClientBuilder AmazonS3Client)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider
;; TODO: implement S3 operations
;; Upload an Object
;; List Objects
;; Download an Object
;; Copy, Move, or Rename Objects
;; Delete an Object
;; Delete Multiple Objects at Once

(def client (AmazonS3ClientBuilder/defaultClient))

(defn object-exist?
  [^AmazonS3Client client ^String bucket-name ^String object-name]
  (.doesObjectExist client bucket-name object-name))

(defn bucket-exist?
  [^AmazonS3Client client ^String bucket-name]
  (.doesBucketExistV2 client bucket-name))
