(ns ndc.ws-client
  (:require [chord.client :as chord]
            [cljs.core.async :as a]))

(defn ws-url [path]
  (str (if (= "http:" js/window.location.protocol)
         "ws://"
         "wss://")
       js/window.location.host
       path))

(defn start [url callback]
  (let [running? (atom true)]
    (a/go
      (loop [ws-chan nil]
        (when @running?
          (if (nil? ws-chan)
            (let [{:keys [ws-channel error]} (a/<! (chord/ws-ch (ws-url url)))]
              (if error
                (js/console.log "Failed to connect websocket" (pr-str error))
                (do
                  (callback (:message (a/<! ws-channel)))
                  (recur ws-channel))))
            (let [timeout-ch (a/timeout 7000) ;; heartbeat every 3s from server
                  [value port] (a/alts! [ws-chan timeout-ch])]
              (condp = port
                timeout-ch
                (do (a/close! ws-chan)
                    (js/console.log "No web socket heartbeat, reconnecting")
                    (recur nil))

                ws-chan
                (if-let [messages (:message value)]
                  (do (callback messages)
                      (recur ws-chan))
                  (do (a/close! ws-chan)
                      (if-let [error (:error value)]
                        (js/console.log "websocket error" (pr-str error))
                        (do
                          (js/console.log "Lost websocket connection, trying again in 3 seconds")
                          (a/<! (a/timeout 3000))
                          (recur nil)))))))))))
    (fn []
      (reset! running? false))))
