(ns testsegregate.events
  (:require [testsegregate.db :as mydb]
            [datascript.core :as d]
            [testsegregate.utils :as utils]))

(defn initDataMethod1 [DBconn]
  (let [data (:data @mydb/app-state)
        nrow (count data)
        ncol (count (first data))
        rtime (time
                (doseq [irow (range nrow)]
                  (doseq [icol (range ncol)]
                    (let [datom [{:db/id -1 :irow irow :icol icol :val (get-in data irow icol)}]]
                      (d/transact! DBconn datom)))))]
    (swap! mydb/app-state assoc-in [:method1 :init-time] rtime)))

(defn initDataMethod2 [DBconn]
  (let [data (:data @mydb/app-state)
        col1 (mapv #(first %) data)
        col2 (mapv #(second %) data)
        col3 (mapv #(nth % 2) data)
        rtime (time
                (do
                  (d/transact! DBconn [{:db/id -1 :icol 0 :val col1}])
                  (d/transact! DBconn [{:db/id -1 :icol 1 :val col2}])
                  (d/transact! DBconn [{:db/id -1 :icol 2 :val col3}])))]
    (swap! mydb/app-state assoc-in [:method2 :init-time] rtime)))

(defn initDataMethod3 [DBconn]
  (let [data (:data @mydb/app-state)
        nrow (count data)
        rtime (time
                (doseq [irow [range nrow]]))]))

(defn initDataMethod4 [DBconn])

(defn initdata []
  (let [numrow (js/parseInt (:input-text @mydb/app-state))
        data (utils/gentabledata numrow)
        DBconn1 (d/create-conn)
        DBconn2 (d/create-conn)
        DBconn3 (d/create-conn)
        DBconn4 (d/create-conn)]
    (swap! mydb/app-state assoc :data data)
    ;; Init data and test for method 1
    (initDataMethod1 DBconn1)

    ;; Init data and test for method 2
    (initDataMethod2 DBconn2)

    ;; Init data and test for method 3
    (initDataMethod3 DBconn3)

    ;; Init data and test for method 4
    (initDataMethod4 DBconn4)))

