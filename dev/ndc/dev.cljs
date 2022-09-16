(ns ndc.dev
  (:require [clojure.string :as str]
            [clojure.walk :as walk]
            [dumdom.core :as dumdom :refer [defcomponent]]
            [gadget.inspector :as gadget]
            [ui.completion-input :refer [CompletionInput]]
            [ui.list-icon :refer [list-icon]]))

;; Data

(defonce store
  (atom
   {:shopping-list {:week 45
                    :groceries [{:text "Banana"}
                                {:text "Orange"}
                                {:text "Covid19 test"}]}}))

;; Actions

(defn handle-action [e data]
  (let [action (walk/postwalk (fn [x]
                                (if (= x :event/target-value)
                                  (.. e -target -value)
                                  x)) data)]
    (prn action)
    (case (first action)
      :save-in-store (apply swap! store assoc (rest action)))))

;; UI Components

(defcomponent ListPageComponent [ui-data]
  [:div
   [:div {:style {:display "flex"
                  :align-items "center"
                  :margin 20}}
    [:div {:style {:width 64}}
     (list-icon {:theme :pink})]
    [:h1.h1 {:style {:margin-left 20}}
     (:title ui-data)]]
   [:div {:style {:margin 20}}
    (CompletionInput (:input ui-data))]
   [:div {:style {:margin 20}}
    [:ul
     (for [{:keys [text]} (:items ui-data)]
       [:li text])]]])

;; Business logic

(defn find-completion [completions v]
  (when-not (empty? v)
    (->> completions
         (filter #(str/starts-with? % v))
         first)))

(defn prepare-ui-data [state]
  {:title (str "Week " (-> state :shopping-list :week))
   :items (-> state :shopping-list :groceries)
   :input {:placeholder "Ingredient"
           :value (:current state)
           :completion (find-completion
                        (:completions state)
                        (:current state))
           :onInput [:save-in-store :current :event/target-value]}})


;; Machinery

(defn render [data]
  (let [ui-data (prepare-ui-data data)]
    (gadget/inspect "UI Data" ui-data)
    (dumdom/render
     (ListPageComponent ui-data)
     (js/document.getElementById "app")
     {:handle-event (fn [e data]
                      (handle-action e data))})))

;; Bootup
(render @store)
(gadget/inspect "Store" store)
(add-watch store :render (fn [_ _ _ state]
                           (render state)))



;; ClojureScript
(comment
  @store
  (swap! store assoc :completions ["Banana"
                                   "Bandana"
                                   "Bazooka"
                                   "Bajunga"])


  2 3 4 5 6

  (if (< 0 1)
    true
    false)

  (inc 3)

  (defn magnify [n]
    (* 2 n))

  (magnify 2)
  (magnify -2)

  '("NDC" "NDC" "NDC")

  (def conference "NDC")
  (.toLowerCase conference)

  true

  (def numbers [1 2 3 4 5])
  (conj numbers 6)

  #{1 2 3 4 5}

  {:name "Christian"
   :conference "NDC"}
)
