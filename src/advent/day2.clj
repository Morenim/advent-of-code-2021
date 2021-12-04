(ns advent.day2)

(require '[clojure.string :as str])

(defn parse-line
  [s]
  (let [[x y] (str/split s #" ")]
    [x (Integer/parseInt y)]))

(defn parse-input [s] (map parse-line (str/split-lines s)))

(defn process-instructions
  [[[i step] & xs :as ls] [x y]]
  (if (empty? ls)
    [x y]
    (recur xs (case i
                "forward" [(+ x step) y]
                "up" [x (- y step)]
                "down" [x (+ y step)]))))

(defn solve-first-part
  [x]
  (apply * (process-instructions (parse-input x) [0 0])))

(defn process-advanced-instructions
  [[[instr step] & xs :as ls] [hori depth aim]]
  (if (empty? ls)
    [hori depth]
    (recur xs (case instr
                "forward" [(+ hori step) (+ depth (* aim step)) aim]
                "up" [hori depth (- aim step)]
                "down" [hori depth (+ aim step)]))))

(defn solve-second-part [x] (apply * (process-advanced-instructions (parse-input x) [0 0 0])))
