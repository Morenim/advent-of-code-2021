(ns advent-of-code-2021.day3)

;; Common functions
(defn parse-input [s] (vec (map vec (map seq (clojure.string/split-lines s)))))

(defn binary-to-decimal [s] (Integer/parseInt (apply str s) 2))

;; First part

(defn invert [s] (map #(if (= \1 %1) \0 \1) s))

(defn count-ones
  ([s] (count-ones s (repeat (count (first s)) 0)))
  ([[x & xs :as ls] counts]
   (if (empty? ls)
     counts
     (recur xs (map #(+ %2 (if (= %1 \1) 1 0)) x counts)))))

(defn gamma
  [s c]
  (let [h (/ (count s) 2)]
    (map #(if (> %1 h) \1 \0) c)))

(defn solve-first-part [s]
  (let [p (parse-input s)
        x (gamma p (count-ones p))]
    (* (binary-to-decimal x) (binary-to-decimal (invert x)))))

;; Second part

(defn split-by-common-bit
  [useMost p i]
  (let [{ones true zeroes false} (group-by #(= \1 (nth %1 i)) p)
        one-count (count ones)
        zero-count (count zeroes)]
    (cond
      (= one-count zero-count) (if useMost ones zeroes)
      (> one-count zero-count) (if useMost ones zeroes)
      :else (if useMost zeroes ones))))

(defn extract-rating
  [useMost [x & xs :as p] i]
  (if (empty? xs)
    x
    (recur useMost (split-by-common-bit useMost p i) (inc i))))

(defn extract-ratings
  [p]
  [(extract-rating true p 0) (extract-rating false p 0)])

(defn solve-second-part
  [s]
  (apply * (map binary-to-decimal (extract-ratings (parse-input s)))))