; License
;

(ns org.zmq.test-clojure-zmq
  (:use
     clojure.test
     [org.zmq.clojure-zmq :as zmq])
  (:import [org.zmq Socket]))

(def ctx (zmq/make-context 1 1 0))

; Context
(deftest test-make-context [] 
    (is (not (nil? ctx))))

(deftest test-constant []
  (is (= Socket/PUB zmq/+pub+)))




