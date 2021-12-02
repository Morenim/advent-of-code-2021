(ns advent-of-code-2021.day2-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day2 :refer :all]))

(def example (slurp "src/advent_of_code_2021/day2/example.txt"))

(def input (slurp "src/advent_of_code_2021/day2/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 150))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 1855814))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 900))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 1845455714))))