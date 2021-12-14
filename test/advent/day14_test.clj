(ns advent.day14-test
  (:require [clojure.test :refer :all]
            [advent.day14 :refer :all]))

(def example (slurp "src/advent/day14/example.txt"))

(def input (slurp "src/advent/day14/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve 10 example) 1588))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve 10 input) 2321))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve 40 example) 2188189693529))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve 40 input) 2399822193707))))