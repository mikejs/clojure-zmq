; License
;

(ns 
  org.zmq.clojure-zmq
  (:import [org.zmq Context Socket Poller]))

; Constants
(def *noblock* 1)

(def +p2p+ 0)
(def +pub+ 1)
(def +sub+ 2)
(def +req+ 3)
(def +rep+ 4)
(def +xreq+ 5)
(def +xrep+ 6)
(def +upstream+ 7)
(def +downstream+ 8)

(def +hwm+ 1)
(def +lwm+ 2)
(def +swap+ 3)
(def +affinity+ 4)
(def +identity+ 5)
(def +subscribe+ 6)
(def +unsubscribe+ 7)
(def +rate+ 8)
(def +recovery-ivl+ 9)
(def +mcast-loop+ 10)
(def +sndbuf+ 11)
(def +rcvbuf+ 12)

; Context
(defn make-context 
  ([app-threads io-threads flags] 
    (Context. app-threads io-threads flags))
  ([app-threads io-threads] 
    (make-context app-threads io-threads 0)))

(defn destroy-context [context] 
  (do (.destroy context)))

; Socket
(defn make-socket [context socket-type]
  (Socket. context socket-type))

(defn set-socket-option [socket option value] 
  (do (.setsockopt option value)))

(defn bind [socket address]
  (do (.bind socket address)))

(defn connect [socket address] 
  (do (.connect socket address)))

(defn send-message 
  ([socket message flags]
   (.send socket message flags))
  ([socket message]
   (send-message socket message 0)))

(defn recv-message
  ([socket flags]
    (.recv socket flags))
  ([socket]
    (receive socket 0)))

(defn destroy-socket [socket]
  (do (.destroy socket)))

; Poller
(defn make-poller [context size]
  (Poller. context size))

(defn register [poller socket] ())

(defn poll [poller] ())

(defn poll-in [poller index] ())
(defn poll-out [poller index] ())
(defn poll-error [poller index] ())

(defn destroy-poller [poller] ())
                                        


