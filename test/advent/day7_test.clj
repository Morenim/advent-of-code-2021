(ns advent.day7-test
  (:require [clojure.test :refer :all]
            [advent.day7 :refer :all]))

(def example (slurp "src/advent/day7/example.txt"))

(def input (slurp "src/advent/day7/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve calculate-fuel-cost example) 37))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve calculate-fuel-cost input) 336040))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve calculate-real-fuel-cost example) 168))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve calculate-real-fuel-cost input) 94813675))))