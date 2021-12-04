(ns advent.day1)

(defn clean-string [x] (map #(Integer/parseInt %1) (clojure.string/split-lines x)))

(defn compare-measurements
  ([x]
   (compare-measurements x 0))
  ([[x & xs] acc]
   (if (empty? xs)
     acc
     (recur xs (+ acc (if (> (first xs) x) 1 0))))))

(defn first-part [x] (compare-measurements (clean-string x)))

(defn three-measurement-window
  [[x y z :as xs]]
  (if (nil? z)
    []
    (cons (+ x y z) (three-measurement-window (rest xs)))))

(defn second-part [x] (compare-measurements (three-measurement-window (clean-string x))))
