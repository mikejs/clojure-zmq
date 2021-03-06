(ns 
  org.zeromq.clojure.examples.rpc
   (:use [org.zeromq.clojure :as zmq]))
   
(defn- string-to-bytes [s] (.getBytes s))
(defn- bytes-to-string [b] (String. b))

(defn handle [socket query] 
  (let [query_ (bytes-to-string query)
        resultset (str "Received query: " query_)]
    (zmq/send- socket (string-to-bytes resultset))))

(defn- on-thread [f]
  (doto (Thread. #^Runnable f) 
    (.start)))

(defn start-server []
  (let [ctx (zmq/make-context 1 1)
        socket (zmq/make-socket ctx zmq/+rep+)]
    
    ; Create separate 'dispatcher' thread
    (on-thread 
       #(do 
           (zmq/bind socket "tcp://lo:5555")
           (while true
             (let [query (zmq/recv socket)]
               (handle socket query)))))))

(defn send-to-server [query]
  (let [ctx (zmq/make-context 1)
        socket (zmq/make-socket ctx zmq/+req+)]
    (zmq/connect socket "tcp://localhost:5555")
    (zmq/send- socket (string-to-bytes query))
    (println (str "Received response: " (bytes-to-string (zmq/recv socket))))))


