(ns advent.day13)

(require '[clojure.string :as str])
(require '[advent.util :as util])

(defn parse-point [s]
  (mapv util/parse-int (str/split s #",")))

(defn parse-fold [s]
  (let [[[_ direction line]] (re-seq #"fold along (x|y)=(\d+)" s)]
    [(first direction) (util/parse-int line)]))

(defn parse-input [s]
  (let [[point-lines fold-lines] (map str/split-lines (str/split s #"\r\n\r\n"))
        points (map parse-point point-lines)
        folds (map parse-fold fold-lines)]
    [(set points) folds]))

(defn fold-by-line [direction line [x y]]
  [(if (= direction \x) (if (< x line) x (- line (- x line))) x)
   (if (= direction \y) (if (< y line) y (- line (- y line))) y)])

(defn dimensions [page]
  [(inc (apply max (map first page)))
   (inc (apply max (map second page)))])

(defn fold [[points folds]]
  (reductions
    (fn [[page [width height]] [direction line]]
      (let [points (map #(fold-by-line direction line %) page)
            new-dimensions (if (= direction \x) [line height] [width line])]
        [(set points) new-dimensions]))
    [points (dimensions points)]
    folds))

(defn render [[page [width height]]]
  (map
    (fn [y] (apply str (map #(if (contains? page [% y]) \# \.) (range 0 width))))
    (range 0 height)))

(defn solve-first-part [s]
  (->> s
       parse-input
       fold
       second
       first
       count))

(defn solve-second-part [s]
  (->> s
       parse-input
       fold
       last
       render))