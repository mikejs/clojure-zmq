;   Copyright (c) Joe Holloway. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns
  org.zeromq.clojure
  (:import (org.zeromq ZMQ)))

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
(defn make-context [io-threads]
  (ZMQ/context io-threads))

;
; Socket
;
(defn make-socket [context socket-type]
  (.socket context socket-type))

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
  (.poller context size))

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



