(defproject clj-tokenizer "0.1.0"
  :description "Simple text tokenizing filter using Lucene tokenizer"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.apache.lucene/lucene-core "3.1.0"]
                 [org.apache.lucene/lucene-analyzers "3.1.0"]]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  :main clj-tokenizer.core
  )
