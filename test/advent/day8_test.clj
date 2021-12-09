(ns advent.day8-test
  (:require [clojure.test :refer :all]
            [advent.day8 :refer :all]))

(def example (slurp "src/advent/day8/example.txt"))

(def input (slurp "src/advent/day8/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 26))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 288))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 61229))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 940724))))