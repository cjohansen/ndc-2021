(ns ^:figwheel-hooks ui.devcards
  (:require [devcards.core :as devcards]
            ui.list-icon-cards
            ui.completion-input
            ui.swiper-cards
            ))

(enable-console-print!)

(defn render []
  (devcards/start-devcard-ui!))

(defn render-on-relaod []
  (render))

(render)
