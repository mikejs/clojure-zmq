# clojure-zmq #

## Introduction ##

clojure-zmq provdes a thin Clojure-esque API on top of
[0MQ](http://www.zeromq.org/).

## Getting Started ##

clojure-zmq has the following core dependencies:

 * [zmq](http://github.com/sustrik/zeromq2)
 * [jzmq](http://github.com/sustrik/jzmq) 
 * clojure
 * clojure-contrib
 
The native libraries for `zmq` and `jzmq` must be compiled and installed accordingly.  

The `Zmq.jar` produced from building `jzmq` must be installed to your local
Maven repository as follows:

> mvn install:install-file -Dfile=src/Zmq.jar -DgroupId=org.zmq \
>         -DartifactId=jzmq -Dversion=2.0.6-SNAPSHOT -Dpackaging=jar

A Maven POM is provided allowing you to build clojure-zmq and install it into
your local Maven repository.

