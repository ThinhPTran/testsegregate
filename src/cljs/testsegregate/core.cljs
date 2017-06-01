(ns testsegregate.core
  (:require [reagent.core :as reagent]
            [goog.dom :as gdom]
            [testsegregate.db :as mydb]
            [testsegregate.events :as events]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn page []
  (let [appstatedata (:data @mydb/app-state)
        input-text (:input-text @mydb/app-state)
        numrow (js/parseInt (:input-text @mydb/app-state))
        method1inittime (get-in @mydb/app-state [:method1 :init-time])
        method2inittime (get-in @mydb/app-state [:method2 :init-time])
        method3inittime (get-in @mydb/app-state [:method3 :init-time])
        method4inittime (get-in @mydb/app-state [:method4 :init-time])]
    [:div
     [:div (str "num of row: " numrow)]
     [:div (str "data: " appstatedata)]
     [:div "Method of segregate"]
     [:div.row
      [:input
       {:id "my-row-number"
        :type "text"
        :value input-text
        :onChange (fn [_]
                    (let [v (.-value (gdom/getElement "my-row-number"))]
                      (swap! mydb/app-state assoc :input-text v)))}]
      [:button
       {:type "button"
        :onClick (fn [_]
                   (.log js/console "Init data")
                   (events/initdata))}
       "Init data"]]
     [:div.row
      [:button
       {:type "button"
        :onClick (fn [_]
                   (.log js/console "Change a row"))}
       "Change a row"]]
     [:div.row
      [:button
       {:type "button"
        :onClick (fn [_]
                   (.log js/console "Add a row"))}
       "Add a row"]]
     [:div.row
      [:button
       {:type "button"
        :onClick (fn [_]
                   (.log js/console "Delete a row"))}
       "Delete a row"]]
     [:div.row
      [:button
       {:type "button"
        :onClick (fn [_]
                   (.log js/console "Insert a row"))}
       "Insert a row"]]
     [:div.row
      [:div
       [:div "Method 1: Every cell is a datom"]
       [:div (str "init time: " method1inittime)]]
      [:div
       [:div "Method 2: Every column is a datom"]
       [:div (str "init time: " method2inittime)]]
      [:div
       [:div "Method 3: Every row is a datom"]
       [:div (str "init time: " method3inittime)]]
      [:div
       [:div "Method 4: The whole survey is a datom"]
       [:div (str "init time: " method4inittime)]]]]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))


(defn reload []
  (reagent/render [page]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
