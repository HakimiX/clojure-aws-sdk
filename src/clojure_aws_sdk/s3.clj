(ns clojure-aws-sdk.s3
  (:require [clojure.java.io :as io])
  (:import (com.amazonaws.services.s3 AmazonS3Client AmazonS3ClientBuilder)
           (com.amazonaws.services.s3.model PutObjectRequest ObjectMetadata Bucket Owner)
           (com.amazonaws.auth BasicSessionCredentials BasicAWSCredentials DefaultAWSCredentialsProviderChain)
           (java.io ByteArrayInputStream)
           (com.amazonaws ClientConfiguration)
           (com.amazonaws.client.builder AwsClientBuilder$EndpointConfiguration)))

;; TODO use standrd AmazonS3ClientBuilder with credentials-provider
;; TODO: implement S3 operations
;; Upload an Object
;; List Objects
;; Download an Object
;; Copy, Move, or Rename Objects
;; Delete an Object
;; Delete Multiple Objects at Once

;;final AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(s3Endpoint, REGION);
;;final AmazonS3 client = AmazonS3ClientBuilder.standard()
;;.withEndpointConfiguration(endpoint)
;;.build();

(defn- s3-client*
  "Create an AmazonS3Client instance from a map of credentials and client configuration
  parameters such as "
  [{:keys [access-key secret-key endpoint] :as creds}]
  (let [client-config (ClientConfiguration.)]
    (when-let [conn-timout (:conn-timeout creds)]
      (.setConnectionTimeout client-config conn-timout))
    (when-let [max-conns (:max-conns creds)]
      (.setMaxConnections client-config max-conns))
    ;; Add more client config parameters here if needed (i.e. proxy-host, proxy-port, etc.)
    (let [creds (if (and access-key secret-key)
                  (BasicAWSCredentials. access-key secret-key)
                  (DefaultAWSCredentialsProviderChain.))
          client (AmazonS3Client. creds client-config)]
      (if endpoint (.setEndpoint client endpoint))
      client)))

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
  (to-map (.craeteBucket (s3-client* cred) name)))

(defn delete-bucket
  [cred ^String name]
  (.deleteBucket (s3-client* cred) name))

(defn delete-object
  [cred ^String bucket ^String key]
  (.deleteObject (s3-client* cred) bucket key))

(defn list-buckets
  "List all the S3 buckets for the supplied credentials. The buckets will be
  returned as a seq of maps with the following keys:
    :name          - the bucket name
    :creation-date - the date when the bucket was created
    :owner         - the owner of the bucket"
  [creds]
  (map to-map (.listBuckets (s3-client* creds))))

(defn payload->input-stream
  "Convert payload to inputstream."
  [payload]
  (io/input-stream
    (ByteArrayInputStream. (.getBytes payload))))

(defn put-object
  [^AmazonS3Client client ^String bucket ^String key ^String payload]
  (->> (PutObjectRequest. bucket key (payload->input-stream payload) (ObjectMetadata.))
       (.putObject client)))
