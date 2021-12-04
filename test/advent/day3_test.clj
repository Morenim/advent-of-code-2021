(ns advent.day3-test
  (:require [clojure.test :refer :all]
            [advent.day3 :refer :all]))

(def example (slurp "src/advent/day3/example.txt"))

(def input (slurp "src/advent/day3/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 198))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 1307354))))

(deftest second-part-ratings-test
  (testing "Extract the ratings for the example of the second part."
    (is (= (extract-ratings (parse-input example)) [[\1 \0 \1 \1 \1] [\0 \1 \0 \1 \0]]))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example) 230))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 482500))))