(ns ui.checkmark-icon
  (:require [dumdom.core :refer [defcomponent]]))

(defcomponent CheckmarkIcon [props]
  [:svg {:viewBox "0 0 159.33357 151.82442"
         :xmlns "http://www.w3.org/2000/svg"
         :style (merge (when-let [s (:size props)]
                         {:width s
                          :height s})
                       (:style props))}
   [:g {:transform "translate(-103.19 -530.74)"}
    [:path {:d "m256.76 532.51c-30.93 26.355-62.795 54.246-79.007 92.449-2.9462 6.3978-5.3925 13.442-7.6179 19.926-14.722-19.227-39.038-26.766-60.531-35.719-10.082-4.2593-6.535 6.5717-1.1701 9.4792 26.865 13.356 51.675 39.121 67.343 63.911 11.642-55.622 49.596-101.6 85.233-144.45 3.0115-3.5544 1.509-10.693-4.2496-5.593z"
            :fill (:color props "var(--fg)")
            :fill-rule"evenodd"}]]])
