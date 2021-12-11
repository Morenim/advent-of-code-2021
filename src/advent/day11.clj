(ns advent.day11)

(require '[clojure.string :as str])
(require '[advent.util :as util])

(defn create-grid [lines]
  (mapv #(mapv (comp util/parse-int str) %) lines))

(defn grid-value
  ([grid [x y]] (grid-value grid x y))
  ([grid x y] (nth (nth grid y nil) x nil)))

(defn map-grid [f grid]
  (mapv #(mapv f %) grid))

(defn filter-grid [f grid]
  (filter #(f %) (apply concat grid)))

(defn map-grid-indexed [f grid]
  (mapv
    (fn [y row] (mapv #(f [%1 y] %2) (range 0 (count row)) row))
    (range 0 (count grid)) grid))

(defn flashed? [v] (> v 10))

(defn flashes? [v] (= v 10))

(defn some-grid [f grid]
  (some f (apply concat grid)))

(defn reset-grid [grid]
  [(map-grid #(if (flashed? %) 0 %) grid)
   (count (filter-grid flashed? grid))])

(def relative-points [[-1 -1] [0 -1] [1 -1]
                      [-1 0] [1 0]
                      [-1 1] [0 1] [1 1]])

(defn neighbours [grid xy]
  (filter some? (map #(grid-value grid %1) (mapv #(mapv + % xy) relative-points))))

(defn flash-point [grid xy v]
  (cond (flashes? v) 11
        (flashed? v) v
        :else (let [nr-flashed (count (filter flashes? (neighbours grid xy)))]
                (if (= 0 nr-flashed)
                  v
                  (min 10 (+ v nr-flashed))))))

(defn iterate-flash [grid]
  (map-grid-indexed #(flash-point grid %1 %2) grid))

(defn has-not-settled [grid]
  (some-grid #(= % 10) grid))

(defn flash-grid [grid]
  (first (drop-while (comp has-not-settled) (iterate iterate-flash grid))))

(defn iteration [[grid flashes-acc i]]
  (let [inc-grid (map-grid inc grid)
        flashed-grid (flash-grid inc-grid)
        [next-grid flashes] (reset-grid flashed-grid)]
    [next-grid (+ flashes-acc flashes) (inc i)]))

(defn simulate [grid]
  (iterate iteration [grid 0 0]))

(defn solve-first-part [s]
  (->> s
       str/split-lines
       create-grid
       simulate
       (drop 100)
       first
       second))

(defn not-has-synchronised? [[grid _ _]]
  (some-grid #(> % 0) grid))

(defn flip [f]
  (fn [& xs]
    (apply f (reverse xs))))

(defn solve-second-part [s]
  (->> s
       str/split-lines
       create-grid
       simulate
       (drop-while not-has-synchronised?)
       first
       ((flip nth) 2)))