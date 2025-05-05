(ns clojure.examples.hello
  (:gen-class))

(defn my-map
  "Applies function f to the first item of each coll, then the second, etc.
  Returns a lazy sequence of the results. Stops when any coll is exhausted."
  ([f coll]
   (lazy-seq
    (when (seq coll)
      (cons (f (first coll)) (my-map f (rest coll))))))
  ([f coll & more-colls]
   (let [colls (cons coll more-colls)]
     (lazy-seq
      (when (every? seq colls)
        (cons (apply f (map first colls))
              (apply my-map f (map rest colls))))))))
