(ns advent.day5-test
  (:require [clojure.test :refer :all]
            [advent.day5 :refer :all]))

(def example (slurp "src/advent/day5/example.txt"))

(def input (slurp "src/advent/day5/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 5))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 6666))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 12))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 19081))))