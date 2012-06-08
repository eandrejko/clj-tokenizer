(ns clj-tokenizer.core
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:import [org.apache.lucene.analysis.standard StandardTokenizer StandardAnalyzer])
  (:import [org.apache.lucene.analysis.snowball SnowballFilter])
  (:import [org.tartarus.snowball.ext EnglishStemmer])
  (:import [java.io StringReader])
  (:import [org.apache.lucene.util Version])
  (:import [org.apache.lucene.analysis Token])
  (:gen-class))

(defn token-stream
  "builds a TokenStream from provided string str"
  [str]
  (StandardTokenizer. (Version/LUCENE_30) (StringReader. str)))

(defn token-stream-without-stopwords
  "builds a TokenStream from provided string str with stopwords removed"
  [str]
  (let [sr (StringReader. str)
        sa (StandardAnalyzer. (Version/LUCENE_30))]
    (.tokenStream sa "text" sr)))

(defn stemmed
  "builds a TokenStream from provided TokenStream with words stemmed"
  [tk]
  (SnowballFilter. tk (EnglishStemmer.)))

(defn next-token
  "reads the next token as a string from the TokenStream tk"
  [tk]
  (if (.incrementToken tk)
    (let [at (.getAttributeClassesIterator tk)]
      (.term (.getAttribute tk (.next at)) ))))

(defn token-seq
  "returns a lazy sequence of tokens from the TokenStream tk"
  [tk]
  (lazy-seq
   (if-let [ntok (next-token tk)]
     (cons ntok (token-seq tk)))))

(defn -main
  [& args]
  (doseq [line (line-seq (io/reader *in*))]
    (println (string/join " " (token-seq
                               (token-stream-without-stopwords line))))))