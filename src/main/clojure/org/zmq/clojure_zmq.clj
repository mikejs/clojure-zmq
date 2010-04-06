;   Copyright (c) Joe Holloway. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns 
  org.zmq.clojure-zmq
  (:import [org.zmq Context Socket Poller]))

; Constants
(def +noblock+ 1)

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

(def +pollin+ 1)
(def +pollout+ 2)
(def +pollerr+ 4)

;
; Context
;
(defn make-context [app-threads io-threads & [flags]] 
    (Context. app-threads io-threads (if (nil? flags) 0 flags)))

(defmacro with-context 
  "Constructs a 0MQ context with the given `name` and evaluates the exprs with
  that context in the lexical scope.  The exprs are contained in an implicit
  `do`.  The context is finally destroyed.
  
  (with-context [ctx 1 1 0] ... )
  "
  [[name- app-threads io-threads & flags] & body]
  `(let [~name- (make-context ~app-threads ~io-threads ~@flags)]
     (try
       ~@body
       (finally (.destroy ~name-)))))

;
; Socket
;
(defn make-socket [context socket-type]
  (Socket. context socket-type))

(defmacro with-socket
  "Constructs a 0MQ socket with the given `name` and `context`.  The exprs are
  evaluated with that socket in the lexical scope.  The exprs are contained in
  an implicit `do`.  The socket is finally destroyed.

  (with-socket [sock ctx +req+] ... )
  (with-socket [sock ctx +pub+] ... )
  "
  [[name- context socket-type] & body]
  `(let [~name- (make-socket ~context ~socket-type)]
     (try
       ~@body
       (finally (.destroy ~name-)))))

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

;
; Poller
;
(defn make-poller [context size]
  (Poller. context size))

(defn register [poller socket] 
  (.register poller socket))

(defn poll [poller]
  (.poll poller))

(defn pollin [poller idx]
  (.pollin poller idx))

(defn pollout [poller idx]
  (.pollout poller idx))

(defn pollerr [poller idx]
  (.pollerr poller idx))

(defn destroy-poller [poller]
  (.destroy poller))
                                        


