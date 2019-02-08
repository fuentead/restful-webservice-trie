# restful-webservice-trie

## Prefix Trie webservice. 

Trie implementation to facilitate faster search when providing suggestions of words when a prefix is given.

## Trie Implementation Methods - Java Implementation
insert - insert a word in the trie structure  
suggest - given a prefix, suggest the words that could match the prefix  
seeTrie - prints all words included in the trie  
loadTrie - loads trie with all dictionary words from the linux file: /usr/share/dict/words  
findWord - to be implemented

## REST Webservice Implementation for Trie
GET /words - displays all words from trie  
GET /words/suggest/{wordprefix} - returns list of suggested words with prefix  
GET /words/find-word/{word} - returns true or false if word is in the trie  
POST /words - inserts a word in the trie

## Start Webservice 
Build and Run RestfulWebServiceTrieApplication.java

## Java Files 
### RestfulWebServiceTrieApplication.java
Class To Start Webservice

### Trie.java
Class with trie implementation. Loads trie with linux dictionary words at startup calling the loadTrie() function

### Word.java
Class for word object

### WordDaoService.java
Class Component that implements all functions to call trie method class

### WordResouce.java
Class Controller implementing all the GET and SET functions to interface with the trie

