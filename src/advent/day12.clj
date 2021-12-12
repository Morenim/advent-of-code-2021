(ns advent.day12)

(require '[clojure.string :as str])

(defn parse-line [s]
  (let [[a b :as ab] (vec (str/split s #"-"))]
    (cond
      (= b "end") [ab]
      (= b "start") [(reverse ab)]
      (= a "start") [ab]
      (= a "end") [(reverse ab)]
      :else [ab (reverse ab)])))

(defn parse-graph [s]
  (->> s
       str/split-lines
       (mapcat parse-line)
       (group-by first)
       (map (fn [[k ls]] [k (map second ls)]))
       (into {})))

(defn is-big-cave? [s]
  (and (not (= s "end")) (Character/isUpperCase ^char (first s))))

(defn check-cave? [node current-path allow-double]
  (cond (is-big-cave? node) [allow-double node]
        (not-any? #{node} current-path) [allow-double node]
        allow-double [false node]))

(defn find-paths
  ([graph allow-double] (find-paths graph allow-double "start" []))
  ([graph allow-double node current-path]
   (if (= node "end")
     [(conj current-path node)]
     (let [viable-neighbours (filter some? (map #(check-cave? % current-path allow-double) (get graph node)))]
       (reduce
         (fn [paths [new-allow-double x]] (concat paths (find-paths graph new-allow-double x (conj current-path node))))
         []
         viable-neighbours)))))

(defn count-paths [allow-double graph]
  (count (find-paths graph allow-double)))

(defn solve-first-part [s]
  (->> s
       parse-graph
       (count-paths false)))

; Hella slow.
(defn solve-second-part [s]
  (->> s
       parse-graph
       (count-paths true)))