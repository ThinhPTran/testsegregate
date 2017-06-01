(ns testsegregate.utils)

(defn gentabledata [len]
  (reduce #(conj %1 [%2 %2 0])
          []
          (map #(* % 5) (range 100 (+ 100 len)))))

