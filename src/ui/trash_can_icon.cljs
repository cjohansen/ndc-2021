(ns ui.trash-can-icon
  (:require [dumdom.core :refer [defcomponent]]))

(defcomponent TrashCanIcon [data]
  [:svg {:xmlns "http://www.w3.org/2000/svg"
         :viewBox "0 0 816 1056"
         :style (merge (when-let [s (:size data)]
                         {:width s
                          :height s})
                       (:style data))}
   [:defs
    [:clipPath {:id "thrashcan"
                :clipPathUnits "userSpaceOnUse"}
     [:path {:id "path16"
             :d "M 0,792 H 612 V 0 H 0 Z"}]]]
   [:g {:transform "matrix(1.3333333,0,0,-1.3333333,0,1056)"}
    [:g {:clip-path "url(#thrashcan)"}
     [:g {:transform "translate(383.7168,177.208)"}
      [:path {:fill (:color data "#000000")
              :style "fill-opacity: 1; fill-rule: nonzero; stroke: none"
              :d "m 0,0 c 0.053,-0.001 0.105,-0.002 0.158,-0.004 0,-1.94 -0.177,-3.899 0.033,-5.817 0.637,-5.815 2.874,-10.979 6.838,-15.317 8.458,-9.258 21.542,-9.383 30.18,-0.304 4.061,4.267 6.311,9.422 7.086,15.207 0.424,3.159 0.61,6.351 0.879,9.531 0.665,7.879 1.306,15.76 1.976,23.639 0.728,8.569 1.483,17.135 2.212,25.705 0.659,7.741 1.284,15.484 1.95,23.223 0.974,11.333 1.991,22.662 2.953,33.996 0.963,11.334 1.858,22.674 2.825,34.008 0.966,11.334 2.009,22.66 2.973,33.994 0.975,11.473 1.87,22.951 2.847,34.424 0.965,11.334 2.002,22.661 2.982,33.993 1.087,12.576 2.16,25.154 3.203,37.734 0.16,1.931 0.286,3.89 0.157,5.818 -0.491,7.33 -3.068,13.78 -8.537,18.853 -7.887,7.316 -19.152,7.416 -27.21,0.3 -2.963,-2.617 -5.111,-5.815 -6.657,-9.444 -1.378,-3.235 -1.98,-6.646 -2.278,-10.107 -0.963,-11.195 -1.86,-22.395 -2.792,-33.593 -0.622,-7.464 -1.254,-14.929 -1.889,-22.393 -0.753,-8.845 -1.519,-17.69 -2.271,-26.536 -0.636,-7.464 -1.254,-14.929 -1.889,-22.392 -0.754,-8.846 -1.521,-17.691 -2.277,-26.537 C 12.803,150.38 12.168,142.776 11.515,135.175 10.803,126.883 10.066,118.593 9.363,110.3 8.695,102.421 8.067,94.538 7.4,86.659 6.37,74.496 5.328,62.334 4.279,50.172 3.588,42.156 2.863,34.144 2.18,26.127 1.437,17.419 0.726,8.709 0,0 m -156.392,1.732 c -0.203,2.452 -0.566,6.875 -0.936,11.299 -0.648,7.741 -1.294,15.483 -1.953,23.224 -0.729,8.568 -1.478,17.137 -2.207,25.706 -0.658,7.74 -1.292,15.482 -1.951,23.223 -0.729,8.569 -1.48,17.136 -2.209,25.706 -0.658,7.74 -1.291,15.483 -1.949,23.223 -0.739,8.708 -1.5,17.414 -2.24,26.121 -0.647,7.603 -1.265,15.207 -1.921,22.809 -0.977,11.333 -1.996,22.661 -2.959,33.995 -0.962,11.335 -1.864,22.674 -2.816,34.009 -1.056,12.578 -2.128,25.156 -3.224,37.732 -0.168,1.931 -0.365,3.881 -0.79,5.767 -1.484,6.587 -4.655,12.146 -10.311,16.064 -7.017,4.861 -15.587,4.849 -22.628,0.036 -3.403,-2.326 -5.948,-5.39 -7.821,-9.028 -2.746,-5.333 -3.666,-11.007 -3.097,-16.951 0.238,-2.485 0.453,-4.973 0.664,-7.461 0.632,-7.464 1.251,-14.929 1.886,-22.393 0.753,-8.846 1.523,-17.69 2.276,-26.536 0.648,-7.602 1.277,-15.206 1.927,-22.807 0.721,-8.431 1.466,-16.86 2.184,-25.291 0.67,-7.878 1.309,-15.76 1.978,-23.639 0.728,-8.569 1.48,-17.137 2.209,-25.706 0.658,-7.74 1.292,-15.482 1.951,-23.223 0.729,-8.569 1.482,-17.136 2.211,-25.706 0.658,-7.74 1.282,-15.483 1.948,-23.223 0.975,-11.333 1.983,-22.662 2.96,-33.995 0.869,-10.089 1.61,-20.191 2.609,-30.267 0.697,-7.027 3.627,-13.075 9.1,-17.682 4.959,-4.175 10.683,-5.764 17.09,-4.436 4.806,0.997 8.691,3.584 11.796,7.319 4.849,5.834 6.762,12.624 6.223,22.111 m 55.817,141.309 V 80.162 c 0,-28.315 -0.015,-56.632 0.03,-84.948 0.003,-2.34 0.173,-4.735 0.692,-7.008 2.305,-10.107 11.436,-17.159 21.851,-17.053 10.596,0.11 19.662,7.523 21.606,17.864 0.484,2.567 0.584,5.238 0.585,7.861 0.028,97.441 0.024,194.883 0.024,292.325 0,0.832 0.05,1.669 -0.014,2.497 -0.127,1.656 -0.234,3.321 -0.493,4.959 -1.508,9.578 -10.289,18.004 -21.289,18.266 -12.234,0.291 -20.784,-8.74 -22.359,-17.453 -0.416,-2.303 -0.605,-4.678 -0.607,-7.019 -0.034,-49.137 -0.026,-98.274 -0.026,-147.412 m -176.553,197.308 c -1.869,0.027 -3.514,0.04 -5.159,0.077 -5.98,0.135 -9.938,3.765 -10.443,9.668 -0.142,1.655 -0.069,3.329 -0.056,4.994 0.007,0.818 0.101,1.636 0.172,2.695 0.881,0.173 1.644,0.406 2.418,0.453 1.245,0.076 2.498,0.031 3.748,0.031 139.129,0 278.258,10e-4 417.387,-0.009 1.926,0 3.874,0.203 5.729,-0.25 0.95,-3.244 0.979,-8.553 -0.071,-11.111 -1.624,-3.949 -4.601,-6.176 -8.899,-6.448 -1.923,-0.122 -3.856,-0.054 -5.848,-0.074 -0.254,-1.028 -0.553,-1.813 -0.63,-2.619 -0.632,-6.63 -1.217,-13.264 -1.827,-19.896 -1.031,-11.191 -2.078,-22.38 -3.103,-33.571 -0.672,-7.323 -1.307,-14.649 -1.981,-21.971 -0.98,-10.638 -1.985,-21.274 -2.97,-31.911 -0.666,-7.184 -1.31,-14.37 -1.974,-21.554 -1.022,-11.052 -2.058,-22.103 -3.078,-33.156 -0.649,-7.046 -1.271,-14.094 -1.921,-21.14 -1.019,-11.053 -2.06,-22.103 -3.077,-33.156 -0.673,-7.323 -1.303,-14.649 -1.978,-21.972 C 98.315,88.653 97.29,77.88 96.289,67.104 95.647,60.197 95.032,53.287 94.396,46.379 93.363,35.188 92.321,23.999 91.289,12.809 90.652,5.9 90.032,-1.009 89.396,-7.917 c -1.032,-11.19 -2.07,-22.381 -3.107,-33.57 -0.294,-3.178 -0.583,-6.356 -0.893,-9.532 -1.081,-11.039 -6.1,-20.094 -14.251,-27.401 -9.367,-8.396 -20.421,-12.61 -33.052,-12.609 -69.426,0.007 -138.852,0.003 -208.278,0.003 -8.054,0 -16.11,-0.121 -24.161,0.027 -15.339,0.283 -27.946,6.416 -37.644,18.286 -5.025,6.151 -8.066,13.241 -8.846,21.207 -0.784,8.009 -1.53,16.022 -2.28,24.033 -1.008,10.775 -2.01,21.55 -3.004,32.326 -0.675,7.323 -1.323,14.648 -1.998,21.97 -0.994,10.776 -2.007,21.55 -3.002,32.326 -0.675,7.323 -1.324,14.648 -2,21.97 -0.993,10.777 -2.005,21.551 -2.999,32.326 -0.675,7.323 -1.325,14.648 -2.001,21.97 -0.994,10.776 -2.006,21.551 -3,32.326 -0.675,7.322 -1.323,14.648 -1.999,21.971 -0.994,10.775 -2.007,21.55 -3.001,32.326 -0.675,7.322 -1.322,14.648 -1.998,21.97 -0.993,10.776 -2.004,21.55 -3.002,32.326 -0.652,7.046 -1.292,14.093 -1.942,21.139 -1.035,11.191 -2.077,22.38 -3.106,33.57 -0.153,1.659 -0.224,3.325 -0.389,4.981 -0.136,1.363 -0.356,2.718 -0.571,4.325"}]]
     [:g {:transform "translate(93.6006,610.186)"}
      [:path {:fill (:color data "#000000")
              :style "fill-opacity:1;fill-rule:nonzero;stroke:none"
              :d "m 0,0 c -0.661,3.079 -0.549,5.706 -0.298,8.307 1.735,18.002 14.29,32.577 31.818,36.91 2.281,0.564 4.63,0.915 6.969,1.174 16.273,1.8 32.553,3.524 48.826,5.323 13.789,1.524 27.571,3.129 41.359,4.671 6.067,0.678 12.142,1.276 18.211,1.942 0.939,0.103 1.86,0.373 2.98,0.605 0.074,1.351 0.189,2.563 0.2,3.776 0.097,10.403 7.393,21.985 20.61,25.12 2.149,0.509 4.38,0.707 6.584,0.951 8.687,0.961 17.382,1.866 26.07,2.825 8.688,0.959 17.371,1.956 26.057,2.937 3.033,0.343 6.061,0.739 9.099,1.021 13.181,1.22 25.166,-7.198 28.498,-19.972 0.308,-1.18 0.656,-2.35 1.033,-3.698 2.514,0.216 4.846,0.365 7.166,0.625 11.168,1.25 22.33,2.553 33.499,3.793 11.17,1.239 22.347,2.41 33.517,3.641 8.689,0.956 17.37,1.967 26.057,2.936 5.102,0.569 10.198,1.256 15.316,1.607 6.032,0.413 11.921,-0.571 17.506,-2.913 13.796,-5.787 22.303,-16.053 25.616,-30.618 0.237,-1.039 0.195,-2.141 0.304,-3.47 -1.119,-0.223 -2.034,-0.478 -2.966,-0.581 C 282.839,31.284 141.646,15.667 0,0"}]]]]])
