(ns clojure.examples.hello
    (:gen-class))

(defn my-map
    ([f coll1]
        (let [first (first coll1)
              rest (rest coll1)]
          (if (seq rest)
            (cons (f first) (my-map f rest))
            (list (f first)))))
    ([f coll1 & cols2]
        (let [cols (cons coll1 cols2)
               firsts (my-map first cols)
              rests (my-map rest cols)]
          (if (some #(not (seq %)) rests)
              (list (apply f firsts))
              (cons (apply f firsts) (apply my-map f rests))))))
