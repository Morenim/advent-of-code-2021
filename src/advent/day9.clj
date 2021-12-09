(ns advent.day9)

(require '[clojure.string :as str])
(require '[advent.util :as util])

;; Parsing

(defn to-grid [ls]
  (->> ls
       (map-indexed (fn [y ln] (map-indexed (fn [x s] [[x y] (Integer/parseInt (str s))]) ln)))
       (apply concat)
       (into {})))

;; First problem

(defn neighbours [grid [x y]]
  (filter some? [(get grid [(dec x) y])
                 (get grid [(inc x) y])
                 (get grid [x (dec y)])
                 (get grid [x (inc y)])]))

(defn is-lowest-level [v nbrs]
  (< v (apply min nbrs)))

(defn lowest-points [grid]
  (filter (fn [[k v]] (is-lowest-level v (neighbours grid k))) grid))

(defn risk-levels [grid]
  (->> grid
       lowest-points
       (map second)
       (map inc)))

(defn solve-first-part [s]
  (->> s
       str/split-lines
       to-grid
       risk-levels
       util/sum))

;; Second problem

(defn point-to-basin [grid basin [x y :as xy]]
  (let [v (get grid xy)]
    (if (or (nil? v) (= v 9) (contains? basin xy))
      basin
      (reduce #(point-to-basin grid %1 %2) (conj basin xy) [[(dec x) y] [x (dec y)] [(inc x) y] [x (inc y)]]))))

(defn to-basins [grid]
  (let [lowest (lowest-points grid)]
    (map #(point-to-basin grid #{} (first %)) lowest)))

(defn score-basins [basins]
  (->> basins
       (map count)
       (sort >)
       (take 3)
       (apply *)))

(defn solve-second-part [s]
  (->> s
       str/split-lines
       to-grid
       to-basins
       score-basins))