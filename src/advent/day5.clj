(ns advent.day5)

(require '[clojure.string :as str])

(defn parse-int [s] (Integer/parseInt s))

(defn parse-point [s] (mapv parse-int (str/split s #",")))

(defn parse-line [s] (map parse-point (str/split s #" -> ")))

(defn parse-input [s] (map parse-line (str/split-lines s)))

(defn absolute-range [z1 z2]
  (range (min z1 z2) (inc (max z1 z2))))

(defn reverse-if-decreasing [z1 z2 ls]
  ((if (< z1 z2) identity reverse) ls))

(defn vertical-line-to-points [[[x1 y1] [_ y2]]]
  (map #(vector x1 %1) (absolute-range y1 y2)))

(defn horizontal-line-to-points [[[x1 y1] [x2 _]]]
  (map #(vector %1 y1) (absolute-range x1 x2)))

(defn grid-line-to-points [[[x1 y1] [x2 y2] :as line]]
  (cond
    (= x1 x2) (vertical-line-to-points line)
    (= y1 y2) (horizontal-line-to-points line)
    :else []))

(defn diagonal-line-to-points [[[x1 y1] [x2 y2]]]
  (map
    vector
    (reverse-if-decreasing x1 x2 (absolute-range x1 x2))
    (reverse-if-decreasing y1 y2 (absolute-range y1 y2))))

(defn all-line-to-points [[[x1 y1] [x2 y2] :as line]]
  (cond
    (= x1 x2) (vertical-line-to-points line)
    (= y1 y2) (horizontal-line-to-points line)
    :else (diagonal-line-to-points line)))

(defn count-overlap [lines]
  (count (filter #(>= (second %1) 2) (frequencies lines))))

(defn solve-first-part [s]
  (count-overlap (mapcat grid-line-to-points (parse-input s))))

(defn solve-second-part [s]
  (count-overlap (mapcat all-line-to-points (parse-input s))))