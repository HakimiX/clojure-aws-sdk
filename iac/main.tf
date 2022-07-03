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

resource "aws_s3_bucket_object" "sample_bucket_object" {
  bucket     = aws_s3_bucket.sample_bucket.id
  key        = "sample-object.json"
  source     = "../resources/sample-object.json"
  depends_on = [aws_s3_bucket.sample_bucket]
}

