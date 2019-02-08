package com.rest.webservice.trie.restfulwebservicetrie.word;

public class Word {
   
   private String word;
   
   protected Word() {
      
   }

   public Word(String word) {
      super();
      this.word = word;
   }

   public String getWord() {
      return word;
   }

   public void setWord(String word) {
      this.word = word;
   }

   @Override
   public String toString() {
      return "Word [word=" + word + "]";
   }
}
