(ns advent.day12-test
  (:require [clojure.test :refer :all]
            [advent.day12 :refer :all]))

(def small (slurp "src/advent/day12/small.txt"))
(def medium (slurp "src/advent/day12/medium.txt"))
(def large (slurp "src/advent/day12/large.txt"))
(def input (slurp "src/advent/day12/input.txt"))

(deftest first-part-small-test
  (testing "Test the small for the first part."
    (is (= (solve-first-part small) 10))))

(deftest first-part-medium-test
  (testing "Test the medium for the first part."
    (is (= (solve-first-part medium) 19))))

(deftest first-part-large-test
  (testing "Test the large for the first part."
    (is (= (solve-first-part large) 226))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 5178))))


(deftest second-part-small-test
  (testing "Test the small for the second part."
    (is (= (solve-second-part small) 36))))

(deftest second-part-medium-test
  (testing "Test the medium for the second part."
    (is (= (solve-second-part medium) 103))))

(deftest second-part-large-test
  (testing "Test the large for the second part."
    (is (= (solve-second-part large) 3509))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input) 130094))))