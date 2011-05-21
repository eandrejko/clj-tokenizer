(defproject clj-tokenizer "1.0.0-SNAPSHOT"
  :description "Simple text tokenizing filter using Lucene tokenizer"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.apache.lucene/lucene-core "3.0.3"]]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  :main clj-tokenizer.core
  )
