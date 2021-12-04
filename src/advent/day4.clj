(ns advent.day4
  (:require [clojure.string :as str]))

(defn parse-int
  [s]
  (Integer/parseInt s))

(defn parse-numbers
  [s]
  (mapv parse-int (str/split s #",")))

(defn parse-board
  [s]
  (mapv #(mapv parse-int (filter not-empty (str/split %1 #" +"))) (str/split-lines s)))

(defn parse-input
  [s]
  (let [sanitised (str/replace s #"\r\n" "\n")
        [raw-numbers & raw-boards] (str/split sanitised #"\n\n")
        numbers (parse-numbers raw-numbers)
        boards (mapv parse-board raw-boards)]
    [numbers boards]))

(defn initialise-state
  [boards]
  (let [empty-row (vec (repeat 5 false))
        empty-state (vec (repeat 5 empty-row))]
    (map (fn [board] [board empty-state]) boards)))

(defn unmarked-value
  [board-nr is-drawn]
  (if is-drawn 0 board-nr))

(defn sum-unmarked
  [[board state]]
  (apply + (map (fn [brow srow] (apply + (map unmarked-value brow srow))) board state)))

(defn has-board-won?
  [state]
  (or
    (some #(every? true? %1) state)
    (some (fn [i] (every? #(nth %1 i) state)) [0 1 2 3 4])))

(defn update-value
  [nr board-nr is-drawn]
  (cond is-drawn true
        (= nr board-nr) true
        :else false))

(defn update-state
  [nr [board state]]
  [board (mapv (fn [brow srow] (mapv #(update-value nr %1 %2) brow srow)) board state)])

(defn separate-winners
  [nr state]
  (let [{winners true losers false} (group-by (fn [[_ s]] (boolean (has-board-won? s))) state)]
    [(map (fn [s] [nr s]) winners), losers]))

(defn play-rounds
  ([nrs state] (play-rounds nrs state []))
  ([[nr & nrs :as ls] state acc]
   (if (empty? ls)
     acc
     (let [new-state (map #(update-state nr %1) state)
           [winners losers] (separate-winners nr new-state)]
       (recur nrs losers (concat acc winners))))))

(defn solve
  [get-winner s]
  (let [[nrs boards] (parse-input s)
        [nr winner] (get-winner (play-rounds nrs (initialise-state boards)))]
    (* nr (sum-unmarked winner))))

(defn solve-first-part
  [s]
  (solve first s))

(defn solve-second-part
  [s]
  (solve last s))