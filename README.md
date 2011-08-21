# clj-tokenizer

A simple Clojure wrapper around the Lucene text tokenizer.  A wrapper for the Lucene [StandardAnalyzer](http://lucene.apache.org/java/3_0_3/api/core/org/apache/lucene/analysis/standard/StandardAnalyzer.html) and Lucene [StandardTokenizer](http://lucene.apache.org/java/3_0_3/api/core/org/apache/lucene/analysis/standard/StandardTokenizer.html) are provided.

For a proper Clojure library for NLP see [clojure-nlp](https://github.com/dakrone/clojure-opennlp).

The project can run from the command line and will tokenize each line of stdin, remove stopwords and write to stdout.

## Usage

First clone the project.  Then set up your 

      lein deps
      lein compile; lein uberjar

For example, to use the tokenizer from the command line use `java -jar`

      curl http://www.gutenberg.org/cache/epub/2701/pg2701.txt | java -jar clj-tokenizer-0.1.0-SNAPSHOT-standalone.jar | head -100


will tokenize Herman Melville's Moby Dick.

To use the tokenizer within Clojure first add the dependency to project.clj

      [clj-tokenizer "0.1.0"]

To create a token stream:

      (token-seq (token-stream "This is a string."))
      ;; ("This" "is" "a" "string")

To convert to lowercase and remove stopwords:

      (token-seq (token-stream-without-stopwords "This is a string, without the stopwords."))
      ;; ("string" "without" "stopwords")

To stem the words using the Snowball stemmer:

      (token-seq (stemmed (token-stream-without-stopwords "Going to be Stemming some lemmings.")))
      ;; ("go" "stem" "some" "lem")


## License

Copyright (C) 2010 Erik Andrejko

Distributed under the Eclipse Public License, the same as Clojure.
