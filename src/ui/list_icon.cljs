(ns ui.list-icon)

(defn color [k colors]
  (if (map? (k colors))
    (str "url(#" (:id (k colors)) ")")
    (k colors)))

(defn pct [n]
  (if (number? n)
    (str n "%")
    n))

(def themes
  {:pink {:background {:x1 100
                       :stops [{:color "#a433e8"}
                               {:color "var(--amaranth)" :offset 80}]}
          :pen "var(--amaranth)"
          :pen-top "var(--deep-lemon)"}
   :purple {:background "var(--imperial)"
            :pen "var(--amaranth)"
            :pen-tip "var(--flint)"
            :pen-top "var(--west-side)"}})

(defn list-icon [& [{:keys [colors theme]}]]
  (let [colors (->> (merge {:background "var(--imperial)"
                            :list-background "#fff"
                            :pen "var(--amaranth)"
                            :pen-wood "var(--wafer)"
                            :pen-tip "var(--flint)"
                            :pen-top "var(--west-side)"}
                           (themes theme)
                           colors)
                    (map (fn [[k v]]
                           (if (map? v)
                             [k (assoc v :id (str "gradient-" (hash v)))]
                             [k v])))
                    (into {}))]
    [:svg {:xmlns "http://www.w3.org/2000/svg"
           :viewBox "50 50 570 570"}
     [:defs
      (for [gradient (->> (vals colors)
                          (filter map?))]
        [:linearGradient {:id (:id gradient)
                          :x1 (pct (:x1 gradient "0"))
                          :y1 (pct (:y1 gradient "0"))
                          :x2 (pct (:x2 gradient "0"))
                          :y2 (pct (:y2 gradient "100%"))}
         (let [stops (dec (count (:stops gradient)))
               step-size (/ 100 (max 1 stops))
               stops (conj (->> step-size
                                (repeat stops)
                                (map-indexed (fn [i s] (* (inc i) s)))) 0)]
           (map-indexed (fn [i color]
                          [:stop {:offset (pct (:offset color (nth stops i)))
                                  :stop-color (:color color)}])
                        (:stops gradient)))])]
     [:g {:transform "matrix(1.33333 0 0 -1.33333 0 666.667)"}
      [:path {:d "M463 249c0-117.084-94.916-212-212-212S39 131.916 39 249s94.916 212 212 212 212-94.916 212-212"
              :fill (color :background colors)}]
      [:g {:transform "translate(10, 0)"}
       [:path {:d "M316.366 133.831H160.013c-9.472 0-17.222 7.75-17.222 17.222v194.511c0 9.473 7.75 17.223 17.222 17.223h156.353c9.472 0 17.222-7.75 17.222-17.223v-194.51c0-9.473-7.75-17.223-17.222-17.223"
               :fill (color :list-background colors)}]
       [:path {:d "m279.628 121.304-16.908 16.908a2.41 2.41 0 0 0 0 3.398l58.943 58.943 20.306-20.306-58.944-58.943a2.408 2.408 0 0 0-3.397 0"
               :fill (color :pen colors)}]
       [:path {:d "m254.923 113.507 27.738 7.433-20.305 20.304Z"
               :fill (color :pen-wood colors)}]
       [:path {:d "m341.969 180.247-20.306 20.306 16.77 16.77a5.013 5.013 0 0 0 7.07 0l13.237-13.235c1.944-1.945 1.944-5.127 0-7.07z"
               :fill (color :pen-top colors)}]
       [:path {:d "m252.596 110.386-.793.794a.94.94 0 0 0 0 1.325l5.409 5.409a.939.939 0 0 0 1.325 0l.793-.794a.939.939 0 0 0 0-1.325l-5.41-5.41a.94.94 0 0 0-1.324 0"
               :fill (color :pen-tip colors)}]
       [:g {:clip-path "url(#b)" :opacity ".2"}
        [:path {:d "m358.43 204.395-6.308 6.31-99.922-99.923.338-.338a1.022 1.022 0 0 1 1.44 0l3.84 3.838 24.843 6.658-.082.08c.212.117.415.253.595.432l58.795 58.796 16.462 16.46c2.114 2.115 2.114 5.575 0 7.688"
                :fill (color :pen-tip colors)}]]
       [:path {:d "M311.837 318.14h-91.582a2.29 2.29 0 0 0 0 4.58h91.582a2.29 2.29 0 1 0 0-4.58m-100.513 23.314a2.29 2.29 0 0 1-3.238 0l-31.905-31.905-15.096 15.096a2.29 2.29 0 0 1-3.238-3.238l16.187-16.187c.109-.224.247-.437.433-.623a2.283 2.283 0 0 1 1.619-.671c.034 0 .068.008.102.01.03-.001.058-.008.087-.008.586 0 1.172.223 1.619.67.184.185.32.396.43.618l33 33a2.29 2.29 0 0 1 0 3.238m0-49.989a2.29 2.29 0 0 1-3.238 0L176.18 259.56l-15.096 15.096a2.29 2.29 0 0 1-3.238-3.238l16.187-16.187c.109-.224.247-.437.433-.623a2.283 2.283 0 0 1 1.619-.671c.034 0 .068.008.102.01.03-.001.058-.008.087-.008.586 0 1.172.223 1.619.67.184.185.32.396.43.618l33 33a2.29 2.29 0 0 1 0 3.238m.001-49.608a2.288 2.288 0 0 1-3.238 0l-31.905-31.905-15.096 15.096a2.29 2.29 0 0 1-3.238-3.238l16.187-16.186a2.26 2.26 0 0 1 .433-.623 2.279 2.279 0 0 1 1.619-.67c.034 0 .068.007.102.008.03 0 .058-.007.087-.007.586 0 1.172.222 1.619.67.184.184.32.395.43.617l33 33.001a2.289 2.289 0 0 1 0 3.237m100.513 25.705h-91.582a2.29 2.29 0 0 0 0 4.579h91.582a2.29 2.29 0 1 0 0-4.58m0-50.578h-91.582a2.29 2.29 0 1 0 0 4.58h91.582a2.29 2.29 0 1 0 0-4.58"
               :fill (color :background colors)}]]]]))
