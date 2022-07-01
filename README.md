# Clojure AWS SDK for Java

* [AWS Services](#aws-services)
* [Local Development using LocalStack](#local-development-using-localstack)
* [Sources](#sources)

## AWS Services
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
