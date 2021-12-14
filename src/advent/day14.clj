(ns advent.day14)

(require '[clojure.string :as str])

(defn parse-rule [s]
  (let [[left right] (str/split s #" -> ")]
    [left (first right)]))

(defn parse-input [s]
  (let [[start _ & raw-rules] (str/split-lines s)]
    [[(frequencies (map #(apply str %) (partition 2 1 start))) (frequencies start)]
     (into {} (map parse-rule raw-rules))]))

(defn expand [[start rules]]
  (iterate
    (fn [[adjacent start-counts]]
      (reduce
        (fn [[acc counts :as iter] [[x y :as xy] result]]
          (let [n (get adjacent xy)]
            (if (some? n)
              [(merge-with + acc {
                                  (apply str [x result]) n,
                                  (apply str [result y]) n
                                  })
               (merge-with + counts {result n})]
              iter)))
        [{} start-counts]
        rules))
    start))

(defn score [s]
  (let [freqs (map second (second s))
        min (apply min freqs)
        max (apply max freqs)]
    (- max min)))

(defn solve [n s]
  (->> s
       parse-input
       expand
       (drop n)
       first
       score))
