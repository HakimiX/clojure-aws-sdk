# Clojure AWS SDK for Java

* [AWS Services](#aws-services)
* [Deploy Infrastructure](#deploy-infrastructure)
  * [Terraform](#terraform)
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


## Sources
* [LocalStack](https://github.com/localstack/localstack)
