(ns advent.day10)

(require '[clojure.string :as str])
(require '[advent.util :as util])

(defn score-illegal-character [c]
  (case c
    \) 3
    \] 57
    \} 1197
    \> 25137
    0))

(defn is-closing-character [c] (some #{c} '(\) \] \} \>)))

(defn matches-starting-character [starting ending]
  (case starting
    \( (= ending \))
    \[ (= ending \])
    \{ (= ending \})
    \< (= ending \>)))

(defn matches [p v] (if (p v) v nil))

(defn find-illegal-character [s]
  (matches
    char?
    (reduce
      (fn [stack c]
        (if (is-closing-character c)
          (if (matches-starting-character (peek stack) c)
            (pop stack)
            (reduced c))
          (conj stack c)))
      '() s)))

(defn solve-first-part [s]
  (->> s
       str/split-lines
       (map (comp score-illegal-character find-illegal-character))
       util/sum))

(defn to-ending-character [c]
  (case c
    \( \)
    \[ \]
    \{ \}
    \< \>))

(defn autocomplete [s]
  (map to-ending-character
       (reduce
         (fn [stack c]
           (if (is-closing-character c)
             (pop stack)
             (conj stack c)))
         '() s)))

(defn score-autocomplete-character [c]
  (case c
    \) 1
    \] 2
    \} 3
    \> 4))

(defn score-autocomplete [s]
  (reduce (fn [score c] (+ (score-autocomplete-character c) (* score 5))) 0 s))

(defn middle-value [coll]
  (nth (sort coll) (/ (count coll) 2)))

(defn solve-second-part [s]
  (->> s
       str/split-lines
       (remove find-illegal-character)
       (map (comp score-autocomplete autocomplete))
       middle-value))