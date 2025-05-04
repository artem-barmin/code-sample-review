(ns clojure.examples.hello
    (:gen-class))

(defn my-map
    ([f coll]
        (if (empty? coll)
            '()
            (let [[x & xs] coll
                  fx        (f x)]
                (cons fx (my-map f xs)))))
    ([f coll & more-colls]
        (let [cols  (cons coll more-colls)
              heads (map first cols)
              tails (map rest cols)
              fh    (apply f heads)]
            (if (some empty? tails)
                (list fh)
                (cons fh (apply my-map f tails))))))
