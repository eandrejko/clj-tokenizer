(ns clj-tokenizer.test.core
  (:use [clj-tokenizer.core] :reload)
  (:use [clojure.test]))

(deftest token-seq-without-stopword-removal
  (let [ts (token-stream "this is a string")]
    (is (= '("this" "is" "a" "string") (token-seq ts)))))

(deftest token-seq-with-stopword-removal
  (let [ts (token-stream-without-stopwords "this is a string without the stopwords")]
    (is (= '("string" "without" "stopwords") (token-seq ts)))))
