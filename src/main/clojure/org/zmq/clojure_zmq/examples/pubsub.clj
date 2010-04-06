(ns 
  org.zmq.clojure-zmq.examples.pubsub
   (:use [org.zmq.clojure-zmq :as zmq]))
   
(defn- string-to-bytes [s] (.getBytes s))
(defn- bytes-to-string [b] (String. b))

(defn handle [socket id msg] 
  (println (str "Subscriber " id ", received message: " (bytes-to-string msg))))

(defn- on-thread [f]
  (doto (Thread. #^Runnable f) 
    (.start)))

(defn make-publisher []
  (let [ctx (zmq/make-context 1 1)
        socket (zmq/make-socket ctx zmq/+pub+)]
    (zmq/bind socket "tcp://lo:5555")
    socket))

(defn publish [publisher msg]
  (zmq/send- publisher (string-to-bytes msg)))

(defn start-subscriber [id]
  ; Create subscriber on separate thread so we can interact with REPL when it
  ; blocks.
  (on-thread 
     #(with-context [ctx 1 1]
        (with-socket [socket ctx zmq/+sub+]
          (zmq/set-socket-option socket zmq/+subscribe+ "") 
          (zmq/connect socket "tcp://localhost:5555")
          (while true
            (let [msg (zmq/recv socket)]
              (handle socket id msg)))))))

