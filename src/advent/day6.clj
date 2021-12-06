(ns advent.day6)

(require '[clojure.string :as str])

(defn parse-int [s] (Integer/parseInt s))

(defn parse-input [s] (map parse-int (str/split s #",")))

(defn count-fish [state]
  (reduce-kv (fn [acc _ nr] (+ acc nr)) 0 state))

(defn init-state [xs]
  (reduce #(merge-with (fn [r _] (inc r)) %1 {[false %2] 1}) {} xs))

(defn simulate [state]
   (reduce-kv (fn [acc [isNew time] nr]
                (merge-with + acc
                            (if (= time 0)
                              {[false 6] nr [true 8] nr}
                              {[isNew (dec time)] nr}))
                ) {} state))

(defn solve [n s]
  (count-fish (nth (iterate simulate (init-state (parse-input s))) n)))
