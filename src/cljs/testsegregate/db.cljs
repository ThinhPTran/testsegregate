(ns testsegregate.db
  (:require [reagent.core :as reagent]
            [datascript.core :as d]))

;; This part is for Datascript
;; Create a Datascript "connection" (an atom with the current DB value)

(defonce app-state (reagent/atom {}))





