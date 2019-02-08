package com.rest.webservice.trie.restfulwebservicetrie.word;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordDaoService {
   
   @Autowired
   private Trie trie;
      
   public String startTrie() {
      trie = new Trie();
      return "Trie Started";
   }
   
   public int loadWordsTrie() {
      return trie.loadTrie();
      //return 1;
   }
   
   public List<String> printAllTrieWords() {
      return trie.seeTrie();
   }
   
   public boolean findTrieWord(String word) {
      return trie.find(word);
   }
   
   public List<String> suggestWords(String wordprefix) {
      return trie.suggest(wordprefix);
   }
   
   public Word insertTrieWord(Word word) {
      if(word == null)
         System.out.println("Word is null");
      
      System.out.println("Word is: " + word);
      trie.insert(word.getWord());
      return word;
   } 
}
