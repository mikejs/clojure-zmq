; License
;

(ns 
  org.zmq.clojure-zmq
  (:import [org.zmq Context Socket Poller]))

; Constants
(def *noblock* 1)

(def P2P 0)
(def PUB 1)
(def SUB 2)
(def REQ 3)
(def REP 4)
(def XREQ 5)
(def XREP 6)
(def UPSTREAM 7)
(def DOWNSTREAM 8)

(def HWM 1)
(def LWM 2)
(def SWAP 3)
(def AFFINITY 4)
(def IDENTITY 5)
(def SUBSCRIBE 6)
(def UNSUBSCRIBE 7)
(def RATE 8)
(def RECOVERY_IVL 9)
(def MCAST_LOOP 10)
(def SNDBUF 11)
(def RCVBUF 12)

; Context
(defn make-context 
  ([app-threads io-threads flags] 
    (Context. app-threads io-threads flags))
  ([app-threads io-threads] 
    (make-context app-threads io-threads 0)))

(defn destroy-context [context] 
  (.destroy context))

; Socket
(defn make-socket [context socket-type]
  (Socket. context socket-type))

(defn set-socket-option [socket option value] 
  (.setsockopt socket option value))

(defn bind [socket address]
  (.bind socket address))

(defn connect [socket address] 
  (.connect socket address))

(defn send-
  ([socket message flags]
   (.send socket message flags))
  ([socket message]
   (send- socket message 0)))

(defn recv
  ([socket flags]
    (.recv socket flags))
  ([socket]
    (recv socket 0)))

(defn destroy-socket [socket]
  (.destroy socket))

; Poller
(defn make-poller [context size]
  (Poller. context size))

(defn register [poller socket] ())

(defn poll [poller] ())

(defn poll-in [poller index] ())
(defn poll-out [poller index] ())
(defn poll-error [poller index] ())

(defn destroy-poller [poller] ())
                                        


