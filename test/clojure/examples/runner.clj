(ns clojure.examples.runner
  (:require [clojure.test :as t]
            clojure.examples.hello-test)) ; Explicitly require the test namespace

(defn run-tests!
  "Runs specific test namespaces."
  [_] ; Accept a dummy argument for -X compatibility
  (t/run-tests 'clojure.examples.hello-test)) ; Explicitly run the test namespace