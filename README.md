# Clojure AWS SDK for Java

* [AWS Services](#aws-services)
* [Deploy Infrastructure](#deploy-infrastructure)
  * [Terraform](#terraform)
* [REPL Examples](#repl-examples)
* [Local Development using LocalStack](#local-development-using-localstack)
* [Sources](#sources)

## AWS Services
todo...

## Deploy Infrastructure
### Terraform 
Terraform is an infrastructure as code (IaC) tool that is used to
create and manage resources on cloud platforms and other services through 
their APIs. In the example, the AWS provider is used to interact with AWS 
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

## REPL Examples 
### S3
```clojure
;; Require the S3 namespace
(require '[clojure-aws-sdk.s3 :as bob])

;; Define bucket name for later use
(def bucket-name "bamboozle-sample-bucket")

;; Check if a bucket exists (returns true/false)
(s3/bucket-exist? s3/client "bamboozle-sample-bucket")
=> true

(s3/bucket-exist? s3/client "does-not-exist")
=> false

;; Check if an object exists in a bucket (returns true/false)
(s3/object-exist? s3/client bucket-name "sample-object.json")
=> true

(s3/object-exist? s3/client bucket-name "does-not-exist.json")
=> false
```

## Sources
* [LocalStack](https://github.com/localstack/localstack)
