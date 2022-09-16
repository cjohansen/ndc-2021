(ns ui.list-icon-cards
  (:require [ui.list-icon :refer [list-icon]]
            [dumdom.devcards :refer-macros [defcard]]))

(def palettes
  [{}
   {:background {:x2 100
                 :stops [{:color "var(--amaranth)"}
                         {:color "var(--imperial)" :offset 50}]}
    :pen "var(--amaranth)"
    :pen-tip "var(--flint)"
    :pen-top "var(--west-side)"}
   {:background "#40b7ad"
    :pen "#f0bf5f"
    :pen-top "#f05e4f"}
   {:background "var(--amaranth)"
    :pen "var(--mexican-rojo)"
    :pen-top "var(--deep-lemon)"}
   {:background {:x1 100
                 :stops [{:color "#e831bf"}
                         {:color "var(--amaranth)" :offset 80}]}
    :pen "var(--mexican-rojo)"
    :pen-top "var(--deep-lemon)"}
   {:background {:x1 100
                 :stops [{:color "#a433e8"}
                         {:color "var(--amaranth)" :offset 80}]}
    :pen "var(--amaranth)"
    :pen-top "var(--deep-lemon)"}
   {:background {:x1 100
                 :stops [{:color "var(--amaranth"}
                         {:color "var(--imperial)"}]}
    :pen "var(--amaranth)"
    :pen-top "var(--deep-lemon)"}

   {:background "var(--brick-rojo)"
    :pen "var(--deep-lemon)"
    :pen-top "var(--imperial)"}

   {:background {:x1 100
                 :stops [{:color "var(--amaranth"}
                         {:color "var(--imperial)"}]}
    :pen "var(--deep-lemon)"
    :pen-top "var(--imperial)"}

   {:background "#3a99d9"
    :pen "#e54d42"
    :pen-top "#1369a3"}

   {:background {:x1 100
                 :stops [{:color "var(--blizzard)"}
                         {:color "var(--curious-azul)" :offset 80}]}
    :pen "var(--cinnabar)"
    :pen-top "var(--fun-azul)"}])

(defcard various-palettes
  [:div {:style {:display "flex"
                 :flex-wrap "wrap"}}
   (for [palette palettes]
     [:div {:style {:max-width "300px"
                    :flex-basis "20vw"
                    :padding "0 2vw 2vw 0"}}
      (list-icon {:colors palette})])])
