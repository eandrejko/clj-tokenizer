(ns clj-tokenizer.core
  (:require [clojure.contrib.duck-streams :as ds])
  (:require [clojure.contrib.str-utils :as su])
  (:import [org.apache.lucene.analysis.standard StandardTokenizer StandardAnalyzer])
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

;; usage
(comment
  (token-seq (token-stream "this is a string"))
  (token-seq (token-stream-without-stopwords "this is a string without the stopwords"))
  )

(defn -main
  [ & args]
  (doall (map
          (fn [line] (println (su/str-join " " ((comp token-seq token-stream-without-stopwords)line))))
          (line-seq (java.io.BufferedReader. *in*)))))