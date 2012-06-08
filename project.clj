(defproject clj-tokenizer "0.2.0"
  :description "Simple text tokenizing filter using the Lucene tokenizer"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.apache.lucene/lucene-core "3.1.0"]
                 [org.apache.lucene/lucene-analyzers "3.1.0"]]
  :main clj-tokenizer.core)
