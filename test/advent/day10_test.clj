(ns advent.day10-test
  (:require [clojure.test :refer :all]
            [advent.day10 :refer :all]))

(def example (slurp "src/advent/day10/example.txt"))

(def input (slurp "src/advent/day10/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 26397))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 311949))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 288957))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 3042730309))))