(ns advent.day6-test
  (:require [clojure.test :refer :all]
            [advent.day6 :refer :all]))

(def example (slurp "src/advent/day6/example.txt"))

(def input (slurp "src/advent/day6/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve 80 example) 5934))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve 80 input) 352195))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve 256 example) 26984457539))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve 256 input) 1600306001288))))