(defproject clojure-aws-sdk "0.1.0"
  :description "Clojure AWS SDK for Java"
  :url "https://github.com/HakimiX/clojure-aws-sdk"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cheshire "5.11.0"]
                 [com.amazonaws/aws-java-sdk-s3 "1.12.239"]
                 [com.amazonaws/aws-java-sdk-lambda "1.12.239"]
                 [com.amazonaws/aws-java-sdk-secretsmanager "1.12.239"]
                 [com.amazonaws/aws-java-sdk-ssm "1.12.239"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
