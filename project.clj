(defproject clojure-zmq "2.0.7-SNAPSHOT"
  :description "A Clojure binding/wrapper for ZeroMQ"
  :dependencies [[org.clojure/clojure "1.2.0-RC1"]
                 [org.clojure/clojure-contrib "1.2.0-RC1"]]
  ; jzmq-native-deps only has OS X x86_64 support at the moment
  :native-dependencies [[org.clojars.mikejs/jzmq-native-deps
                         "2.0.7-SNAPSHOT"]]
  :dev-dependencies [[native-deps "1.0.1"]])