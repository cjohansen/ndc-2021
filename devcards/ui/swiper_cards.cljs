(ns ui.swiper-cards
  (:require [ui.swiper :as swiper :refer [SwipeReveal SwipeControl]]
            [ui.checkmark-icon :refer [CheckmarkIcon]]
            [ui.trash-can-icon :refer [TrashCanIcon]]
            [dumdom.core :refer [defcomponent]]
            [dumdom.devcards :refer-macros [defcard]]))

(def styles
  {:display "flex"
   :align-items "center"
   :padding "10px 20px"
   :height "50px"})

(defcard swipe-left
  (SwipeReveal
   {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
             "Drag me right"]
    :left [:div {:style (assoc styles :background "var(--lima)")} "OK!"]}))

(defcard swipe-right
  (SwipeReveal
   {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
             "Drag me left"]
    :right [:div {:style (assoc styles :background "var(--mexican-rojo)")} "NO!"]}))

(defcard swipe-both-ways
  (SwipeReveal
   {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
             "Drag me both ways"]
    :left [:div {:style (assoc styles :background "var(--lima)")} "OK!"]
    :right [:div {:style (assoc styles :background "var(--mexican-rojo)")} "NO!"]}))

(defcard swipe-with-left-threshold-transition
  (fn [store]
    (SwipeReveal
     {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
               "Dragging right yields a transition"]
      :left [:div {:style (assoc styles
                                 :transition "background 0.5s"
                                 :background (if (:ok? @store)
                                               "var(--lima)"
                                               "var(--mine-shaft)"))} "OK!"]
      :on-above-left-threshold (fn []
                                 (swap! store assoc :ok? true))
      :on-below-left-threshold (fn []
                                 (swap! store assoc :ok? false))})))

(defcard swipe-with-right-threshold-transition
  (fn [store]
    (SwipeReveal
     {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
               "Dragging left yields a transition"]
      :right [:div {:style (assoc styles
                                  :transition "background 0.5s"
                                  :background (if (:ok? @store)
                                                "var(--mexican-rojo)"
                                                "var(--mine-shaft)"))} "NO!"]
      :on-above-right-threshold (fn []
                                  (swap! store assoc :ok? true))
      :on-below-right-threshold (fn []
                                  (swap! store assoc :ok? false))})))

(defcard swipe-both-ways-with-transitions
  (fn [store]
    (SwipeReveal
     {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
               "Drag me both ways"]
      :left [:div {:style (assoc styles
                                 :background "var(--lima)"
                                 :padding-right 100
                                 :transition "opacity 0.5s"
                                 :opacity (if (:left? @store)
                                            1
                                            0.1))} "OK!"]
      :right [:div {:style (assoc styles
                                  :background "var(--mexican-rojo)"
                                  :padding-left 100
                                  :transition "opacity 0.5s"
                                  :opacity (if (:right? @store)
                                             1
                                             0.1))} "NO!"]
      :right-threshold 0.25
      :left-threshold 0.25
      :on-above-right-threshold #(swap! store assoc :right? true)
      :on-below-right-threshold #(swap! store assoc :right? false)
      :on-above-left-threshold #(swap! store assoc :left? true)
      :on-below-left-threshold #(swap! store assoc :left? false)})))

(defcard snap-left-disappear
  (fn [store]
    [:div
     (when (:retry? @store)
       [:a {:onClick (fn [e]
                       (.preventDefault e)
                       (swap! store dissoc :gone? :retry?))}
        "Try again"])
     (when-not (:gone? @store)
       [:div {:style {:height 50
                      :transition "height 0.5s"
                      :overflow "hidden"}
              :leaving-style {:height 0}}
        (SwipeReveal
         {:swipee [:div {:style (assoc styles :background "var(--woodsmoke)")}
                   "Drag me left"]
          :right [:div {:style (assoc styles
                                      :background "var(--mexican-rojo)"
                                      :opacity (if (:active? @store) 1 0.1)
                                      :padding-left 100)} "NO!"]
          :right-threshold 0.25
          :snap-right 0.4
          :on-above-right-threshold #(swap! store assoc :active? true)
          :on-below-right-threshold #(swap! store assoc :active? false)
          :on-snap-right (fn []
                           (swap! store assoc :gone? true)
                           (js/setTimeout #(swap! store assoc :retry? true :active? false) 1000))
          })])]))

(defcard swip-control-right
  (fn [store]
    [:div {}
     [:p {:style {:margin-bottom 10}} "Click it to toggle state"]
     [:div {:style {:height 50
                    :position "relative"}
            :onClick (fn [e] (swap! store update :clicked? not))}
      (SwipeControl
       {:content (TrashCanIcon {:color "#ffffff"
                                :size 24})
        :color "var(--mexican-rojo)"
        :align :right
        :class-name (when (:clicked? @store)
                      "swiper-above-threshold")})]]))

(defcard swip-control-left
  (fn [store]
    [:div {}
     [:p {:style {:margin-bottom 10}} "Click it to toggle state"]
     [:div {:style {:height 50
                    :position "relative"}
            :onClick (fn [e] (swap! store update :clicked? not))}
      (SwipeControl
       {:content (CheckmarkIcon {:color "#ffffff"
                                 :size 24})
        :color "var(--lima)"
        :class-name (when (:clicked? @store)
                      "swiper-above-threshold")})]]))

(defcomponent CustomSwipable
  :on-mount swiper/mount-swiper
  [data]
  (swiper/swipable-container
   (SwipeControl
    {:content (CheckmarkIcon {:color "#ffffff"
                              :size 24})
     :color "var(--lima)"})
   [:div.swipee
    [:div {:style (assoc styles :background "var(--woodsmoke)")}
     "Drag me right"]]))

(defcard custom-swipable
  (fn [store]
    [:div
     (when (:retry? @store)
       [:a {:onClick (fn [e]
                       (.preventDefault e)
                       (swap! store dissoc :gone? :retry?))}
        "Try again"])
     (when-not (:gone? @store)
       [:div {:style {:height 50
                      :transition "height 0.5s"
                      :overflow "hidden"}
              :leaving-style {:height 0}}
        (CustomSwipable
         (assoc @store
                :on-above-left-threshold #(swap! store assoc :active? true)
                :on-below-left-threshold #(swap! store assoc :active? false)
                :on-snap-left (fn []
                                (swap! store assoc :gone? true)
                                (js/setTimeout #(swap! store assoc :retry? true :active? false) 1000))
                ))])])
  {:left-threshold [0.1 0.2]
   :snap-left 0.3})

(defcomponent CustomSwipeRight
  :on-mount swiper/mount-swiper
  [data]
  (swiper/swipable-container
   (SwipeControl
    {:content (TrashCanIcon {:color "#ffffff"
                             :size 24})
     :color "var(--mexican-rojo)"
     :align :right})
   [:div.swipee
    [:div {:style (assoc styles :background "var(--woodsmoke)")}
     "Drag me left"]]))

(defcard custom-swipable-other-side
  (fn [store]
    [:div
     (when (:retry? @store)
       [:a {:onClick (fn [e]
                       (.preventDefault e)
                       (swap! store dissoc :gone? :retry?))}
        "Try again"])
     (when-not (:gone? @store)
       [:div {:style {:height 50
                      :transition "height 0.5s"
                      :overflow "hidden"}
              :leaving-style {:height 0}}
        (CustomSwipeRight
         (assoc @store
                :on-above-right-threshold #(swap! store assoc :active? true)
                :on-below-right-threshold #(swap! store assoc :active? false)
                :on-snap-right (fn []
                                (swap! store assoc :gone? true)
                                (js/setTimeout #(swap! store assoc :retry? true :active? false) 1000))
                ))])])
  {:right-threshold [0.1 0.5]
   :snap-right 0.3})

(defcomponent CustomSwipeBoth
  :on-mount swiper/mount-swiper
  [data]
  (swiper/swipable-container
   (SwipeControl
    {:content (TrashCanIcon {:color "#ffffff"
                             :size 24})
     :color "var(--mexican-rojo)"
     :align :right})
   (SwipeControl
    {:content (CheckmarkIcon {:color "#ffffff"
                              :size 24})
     :color "var(--lima)"})
   [:div.swipee
    [:div {:style (assoc styles :background "var(--woodsmoke)")}
     "Drag either way"]]))

(defcard custom-swipable-both-sides
  (fn [store]
    (let [on-snap (fn []
                    (swap! store assoc :gone? true)
                    (js/setTimeout #(swap! store assoc :retry? true) 1000))]
      [:div
       (when (:retry? @store)
         [:a {:onClick (fn [e]
                         (.preventDefault e)
                         (swap! store dissoc :gone? :retry?))}
          "Try again"])
       (when-not (:gone? @store)
         [:div {:style {:height 50
                        :transition "height 0.5s"
                        :overflow "hidden"}
                :leaving-style {:height 0}}
          (CustomSwipeBoth
           (assoc @store
                  :on-snap-right on-snap))])]))
  {:right-threshold [0.1 0.2]
   :snap-right 0.3
   :left-threshold [0.1 0.2]
   :snap-left 0.3})

