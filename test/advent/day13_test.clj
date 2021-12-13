(ns advent.day13-test
  (:require [clojure.test :refer :all]
            [advent.day13 :refer :all]))

(def example (slurp "src/advent/day13/example.txt"))

(def input (slurp "src/advent/day13/input.txt"))

(deftest first-part-example-test
  (testing "Test the example for the first part."
    (is (= (solve-first-part example) 17))))

(deftest first-part-input-test
  (testing "Test the input for the first part."
    (is (= (solve-first-part input) 802))))

(deftest second-part-example-test
  (testing "Test the example for the second part."
    (is (= (solve-second-part example)
           ["#####"
            "#...#"
            "#...#"
            "#...#"
            "#####"
            "....."
            "....."]))))

(deftest second-part-input-test
  (testing "Test the input for the second part."
    (is (= (solve-second-part input)
           ["###..#..#.#..#.####.####..##..#..#.###.."
            "#..#.#.#..#..#.#.......#.#..#.#..#.#..#."
            "#..#.##...####.###....#..#....#..#.###.."
            "###..#.#..#..#.#.....#...#.##.#..#.#..#."
            "#.#..#.#..#..#.#....#....#..#.#..#.#..#."
            "#..#.#..#.#..#.#....####..###..##..###.."]))))