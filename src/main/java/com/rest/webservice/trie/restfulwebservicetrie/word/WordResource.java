package com.rest.webservice.trie.restfulwebservicetrie.word;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordResource {

   @Autowired
   private WordDaoService service;
   
   @GetMapping("/start-trie")
   public String startTrie() {
      return service.startTrie();
   }
   
   /**
    * Load all words and return count
    * Get /words/loadWords
    */
   @GetMapping("/words-load")
   public int loadWords() {
      return service.loadWordsTrie();
   }
   
   /**
    * Get /words
    * Retrieve all words from trie
    * @return
    */
   @GetMapping("/words")
   public List<String> retrieveAllWords() {
      return service.printAllTrieWords();
   }
   
   /**
    * Get /words/find-word/{word}
    * Return true/false if word is in trie
    */
   @GetMapping("/words/find-word/{word}")
   public boolean findTrieWord(@PathVariable String word) {
      return service.findTrieWord(word);
   }
   
   /**
    * Get /words/suggest/{wordprefix}
    * Returns list of suggested words with prefix
    */
   @GetMapping("/words/suggest/{wordprefix}")
   public List<String> suggestedWords(@PathVariable String wordprefix) {
      return service.suggestWords(wordprefix);
   }
   
   /**
    * Input - word
    * Output - CREATED & Return Created URI
    */
   @PostMapping("/words")
   public void insertWord(@RequestBody Word word) {
      if(word == null)
         System.out.println("null");
      
      System.out.println("Word is in word: " + word);
      Word savedWord = service.insertTrieWord(word);
   } 
}
