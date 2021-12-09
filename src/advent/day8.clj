(ns advent.day8)

(require '[advent.util :as util])
(require '[clojure.set :as sets])
(require '[clojure.string :as str])

(defn parse-entry [l] (let [[sig out] (str/split l #" \| ")] [(map sort (util/words sig)) (map sort (util/words out))]))

(defn is-unique [c] (case (count c)
                      2 1
                      4 4
                      3 7
                      7 8
                      nil))

(defn is-unique-map [e] (->> e
                             (map #(vector (is-unique %1) (set %1)))
                             (group-by (comp nil? first))))

(defn solve-first-part [s] (->> s
                                str/split-lines
                                (map (comp second parse-entry))
                                (map (comp is-unique-map))
                                (map (comp count #(get %1 false)))
                                util/sum))

(defn derive-nine [xs ys] [[9 (util/find-first #(sets/subset? (get xs 4) %1) ys)]])

(defn derive-zero-and-three [xs ys]
  (let [zero-or-three (filter #(sets/subset? (get xs 7) %1) ys)]
    (map #(if (= (count %) 6) [0 %] [3 %]) zero-or-three)))

(defn derive-six [_ ys]
  [[6 (util/find-first #(= (count %) 6) ys)]])

(defn derive-two-and-five [xs ys]
  (let [[a b] (seq ys)]
    (if (sets/subset? a (get xs 9))
      [[5 a] [2 b]]
      [[5 b] [2 a]])))

(defn apply-derivation [f [xs ys]]
  (let [x (f xs ys)]
    [(into xs x) (sets/difference (set ys) (map second x))]))

(defn calculate-mapping [sig]
  (let [{xs true ys false} (is-unique-map sig)]
    (first (->> [(into {} ys) (set (map second xs))]
                (apply-derivation derive-nine)
                (apply-derivation derive-zero-and-three)
                (apply-derivation derive-six)
                (apply-derivation derive-two-and-five)))))

(defn to-decimal [[a b c d]] (+ (* 1000 a) (* 100 b) (* 10 c) d))

(defn calculate-output-values [[sig out]]
  (let [mapping (into {} (map (fn [[k v]] [(apply str v) k]) (calculate-mapping sig)))]
    (to-decimal (map #(get mapping %) (map #(apply str %) out)))))

(defn solve-second-part [s] (->> s
                                 str/split-lines
                                 (map parse-entry)
                                 (map calculate-output-values)
                                 util/sum))
