# --------------------------
# AWS S3
# --------------------------

resource "aws_s3_bucket" "sample_bucket" {
  bucket = "bamboozle-sample-bucket"
  tags = {
    Name        = "Sample AWS S3 bucket for testing AWS JAVA SDK"
    Environment = "dev"
  }
}

// Uploading a file to a bucket
resource "aws_s3_bucket_object" "sample_bucket_object" {
  bucket     = aws_s3_bucket.sample_bucket.id
  key        = "sample-object.json"
  source     = "../resources/sample-object.json"
  depends_on = [aws_s3_bucket.sample_bucket]
}

# --------------------------
# AWS Lambda
# --------------------------

resource "aws_lambda_function" "sample_lambda_function" {
  function_name = "bamboozle-sample-lambda"
  role          = aws_iam_role.sample_lambda_role.arn
  runtime       = "java8"
  memory_size   = "1536"

  // Code
  handler       = "bamboozle-sample-lambda.lambda.handler"
  filename      = "bamboozle-sample-lambda.jar"
  source_code_hash = filebase64sha256("bamboozle-sample-lambda.jar")

  environment {
    variables = {
      API_ENDPOINT = "https://jsonplaceholder.typicode.com/"
    }
  }
}

// Lambda role
resource "aws_iam_role" "sample_lambda_role" {
  name               = "bamboozle-sample-lambda-role"
  assume_role_policy = data.aws_iam_policy_document.AWSLambdaTrustPolicy.json
}

// Lambda policy
data "aws_iam_policy_document" "AWSLambdaTrustPolicy" {
  statement {
    actions = ["sts:AssumeRole"]
    effect  = "Allow"
    principals {
      identifiers = ["lambda.amazonaws.com"]
      type        = "Service"
    }
  }
}
