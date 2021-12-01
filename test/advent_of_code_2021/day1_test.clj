(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]))

(def example (slurp "src/advent_of_code_2021/day1/example.txt"))

(def input (slurp "src/advent_of_code_2021/day1/first-input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= 7 (first-part example)))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= 1195 (first-part input)))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= 5 (second-part example)))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= 1235 (second-part input)))))