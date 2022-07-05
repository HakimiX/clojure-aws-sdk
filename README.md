# Clojure AWS SDK for Java

* [AWS Services](#aws-services)
* [Deploy Infrastructure](#deploy-infrastructure)
  * [Terraform](#terraform)
* [REPL](#repl)
* [Local Development using LocalStack](#local-development-using-localstack)
* [Sources](#sources)

## AWS Services
todo...

## Deploy Infrastructure
### Terraform 
Terraform is an infrastructure as code (IaC) tool that is used to
create and manage resources on cloud platforms and other services through 
their APIs. In this example, the AWS provider is used to interact with AWS 
resources. 
> Terraform version v1.2.4 _(on darwin_arm64)_

```shell
# Initialize terraform backend
terraform init

# Validate iac
terraform validate

# Create execution plan
terraform plan

# Apply infrastructre
terraform apply

# Destroy infrastructure
terraform destroy
```

todo...

## Local Development using LocalStack
Start LocalStack inside a Docker container using LocalStack CLI.<br>
_Requirements: python3, pip, and Docker._
```shell
# Install 
pip install localstack 

# Start LocalStack
localstack start -d
> "LocalStack running on http://localhost:4566"

# See status of AWS services
localstack service status
```

## REPL 
### S3
```clojure
;; Require the S3 namespace
(require '[clojure-aws-sdk.s3 :as s3])

;; AWS Basic Auth Credentials
(def creds {:access-key "AWS_ACCESS_KEY_ID here..."
            :secret-key "AWS_SECRET_ACCESS_KEY here..."})

;; Create S3 Client based on Basic Auth Credentials
(def client (s3/client creds))

;; Sample bucket and object (created through IaC)
(def bucket "bamboozle-sample-bucket")
(def object "sample-object.json")

;; Check if a bucket exists (returns true/false)
(s3/bucket-exist? client bucket)
=> true

(s3/bucket-exist? client "does-not-exist")
=> false

;; Check if an object exists in a bucket (returns true/false)
(s3/object-exist? client bucket object)
=> true

(s3/object-exist? client bucket "does-not-exist.json")
=> false

;; Put Object
(put-s3-object client bucket "new-object.json" {:name "bob"})
=> #object[com.amazonaws.services.s3.model.PutObjectResult
           0x457ef00f
           "com.amazonaws.services.s3.model.PutObjectResult@457ef00f"]


;; List Buckets (takes creds instead of client)
(list-buckets creds)
=> ({:name "bamboozle-sample-bucket",
     :creation-date #inst"2022-07-03T13:18:13.000-00:00",
     :owner {:id "e91fdabf59f895c0134cf9c5d", :display-name "mustafa.hakimi"}})
```

## Sources
* [LocalStack](https://github.com/localstack/localstack)
