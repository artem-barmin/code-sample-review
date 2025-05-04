(ns clojure.examples.runner
  (:require [clojure.test :as t]
            clojure.examples.hello-test))

;; Capture the original report function
(def original-report t/report)

(defn colored-report [m]
  (case (:type m)
    :pass (do (print "\033[0;32m") (original-report m) (print "\033[0m")) ; Green for pass
    :fail (do (print "\033[0;31m") (original-report m) (print "\033[0m")) ; Red for fail
    :error (do (print "\033[0;31m") (original-report m) (print "\033[0m")) ; Red for error
    :summary (do (print "\033[0;34m") (original-report m) (print "\033[0m")) ; Blue for summary
    (original-report m))) ; Default for other event types

(defn run-tests!
  "Runs specific test namespaces with colored output."
  [_] ; Accept a dummy argument for -X compatibility
  (binding [t/report colored-report]
    (t/run-tests 'clojure.examples.hello-test)))