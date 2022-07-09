(defproject bamboozle-sample-lambda "0.1.0-SNAPSHOT"
  :description "Sample AWS Lambda Function"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.logging "1.1.0"]
                 [uswitch/lambada "0.1.2"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
