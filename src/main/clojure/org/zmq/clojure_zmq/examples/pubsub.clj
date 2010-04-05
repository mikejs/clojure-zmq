(ns 
  org.zmq.clojure-zmq.examples.pubsub
   (:use [org.zmq.clojure-zmq :as zmq]))
   
(defn- string-to-bytes [s] (.getBytes s))
(defn- bytes-to-string [b] (String. b))

(defn handle [socket id msg] 
  (println (str "Subscriber " id "received message: " (bytes-to-string msg))))

(defn- on-thread [f]
  (doto (Thread. #^Runnable f) 
    (.start)))

(defn start-subscriber [id]
  (let [ctx (zmq/make-context 1 1)
        socket (zmq/make-socket ctx zmq/+sub+)]
    
    ; Create on separate thread so we can interact with REPL
    (on-thread 
       #(do 
           (zmq/set-socket-option socket zmq/+subscribe+ "") 
           (zmq/connect socket "tcp://localhost:5555")
           (while true
             (let [msg (zmq/recv socket)]
               (handle socket id msg)))))))

(def publish-ctx (zmq/make-context 1 1))
(def publish-socket (zmq/make-socket publish-ctx zmq/+pub+))

(do (zmq/bind publish-socket "tcp://lo:5555"))

(defn publish [msg] 
  (zmq/send- publish-socket (string-to-bytes msg)))


