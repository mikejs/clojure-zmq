(ns 
  org.zmq.examples.rpc
   (:use [org.zmq.clojure-zmq :as zmq]))
   
(defn- string-to-bytes [s] (.getBytes s))
(defn- bytes-to-string [b] (String. b))

(def request-handler (agent 0))

(defn handle [socket query] 
    (let [query_ (bytes-to-string query)
          resultset (str "Received query: " query_)]
      (zmq/send-message socket (string-to-bytes resultset))))

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
           (while 1
             (let [query (zmq/recv-message socket)]
               (handle socket query)))))))


(defn -main [] 
  (let [ctx (zmq/make-context 1 1)
        socket (zmq/make-socket ctx zmq/+req+)]
    (do
      (zmq/connect socket "tcp://localhost:5555")
      (zmq/send-message socket (string-to-bytes "Hello World!"))
      (println (str "Received response: " (bytes-to-string (recv socket)))))))


