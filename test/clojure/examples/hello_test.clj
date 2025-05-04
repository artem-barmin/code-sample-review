(ns clojure.examples.hello-test
  (:require [clojure.test :refer :all]
            [clojure.examples.hello :refer [my-map]]))

(deftest my-map-test
  (testing "Single collection mapping"
    (is (= (my-map inc [1 2 3]) '(2 3 4)))
    (is (= (my-map inc []) '()))
    (is (= (my-map #(* % %) [1 2 3 4]) '(1 4 9 16))))

  (testing "Multiple collection mapping"
    (is (= (my-map + [1 2 3] [10 20 30]) '(11 22 33)))
    (is (= (my-map + [1 2] [10 20 30]) '(11 22))) ; Stops at shortest
    (is (= (my-map + [1 2 3] [10 20]) '(11 22))) ; Stops at shortest
    (is (= (my-map + [] [10 20]) '()))
    (is (= (my-map + [1 2] []) '()))
    (is (= (my-map str ["a" "b"] ["x" "y" "z"]) '("ax" "by")))
    (is (= (my-map list [1 2] [:a :b] [100 200]) '((1 :a 100) (2 :b 200)))))

  (testing "Laziness"
    (let [inf-range (iterate inc 0)
          result (my-map inc inf-range)]
      (is (= (take 5 result) '(1 2 3 4 5))))
    (let [inf1 (iterate inc 1)
          inf2 (iterate inc 10)
          result (my-map + inf1 inf2)]
      (is (= (take 3 result) '(11 13 15))))))
