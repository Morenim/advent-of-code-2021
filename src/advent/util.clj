(ns advent.util)

(defn parse-int [s] (Integer/parseInt s))

(require '[clojure.string :as str])

(defn words [s] (str/split s #" "))

(defn sum [x] (reduce + 0 x))

(defn find-first [f xs]
  (reduce #(when (f %2) (reduced %2)) nil xs))