(ns advent.day7)

(require '[clojure.string :as str])

(require '[advent.util :as util])

(defn parse-input [s] (map util/parse-int (str/split s #",")))

(defn simplify [p] [(apply min p) (apply max p) (frequencies p)])

(defn calculate-fuel-cost [n] (identity n))

(defn calculate-real-fuel-cost [n] (/ (* n (inc n)) 2))

(defn diff [a b] (Math/abs ^long (- a b)))

(defn take-smallest [p] (reduce (fn [x y] (if (< x y) (reduced x) y)) p))

(defn calculate-cost-position [fuel-fn pos freqs]
  (util/sum (map (fn [[slot freq]] (* freq (fuel-fn (diff pos slot)))) freqs)))

(defn calculate-fuel-costs [fuel-fn [mi ma freqs]]
  (map #(calculate-cost-position fuel-fn % freqs) (range mi (inc ma))))

(defn solve [fuel-fn s] (-> s
                            parse-input
                            simplify
                            ((partial calculate-fuel-costs fuel-fn))
                            take-smallest))