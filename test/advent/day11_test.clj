(ns advent.day11-test
  (:require [clojure.test :refer :all]
            [advent.day11 :refer :all]))

(def example (slurp "src/advent/day11/example.txt"))

(def input (slurp "src/advent/day11/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 1656))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 1691))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 195))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 3042730309))))