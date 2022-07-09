(ns bamboozle-sample-lambda.lambda
  "Sample AWS Lambda function"
  (:require [uswitch.lambada.core :refer [deflambdafn]]
            [clojure.tools.logging :as log]))

(deflambdafn bamboozle_sample_lambda.lambda.handler
             [in out ctx]
             (log/info :message "Incoming event" :event in))

