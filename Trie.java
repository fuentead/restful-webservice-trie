package com.rest.webservice.trie.restfulwebservicetrie.word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
   
   public Node head;
   private int wordCount;

   class Node {
      char letter;
      Map<Character, Node> children;
      boolean isWord;
      public Node(char letter) {
         this.letter = letter;
         this.children = new HashMap<Character, Node>();
         this.isWord = false;
      }
   }
   
   public Trie() {
      this.head = new Node(' ');
      this.wordCount = 0;
   }
   
   /**
    * Insert word in trie.
    * @param word to be inserted
    */
   public void insert(String word) {
      if(word == null)
         return;
      
      Node ptr = head;
      int i = 0;
      while(i < word.length() && ptr.children.containsKey(word.charAt(i))) {
         ptr = ptr.children.get(word.charAt(i));
         i++;
      }
      
      while(i < word.length()) {        
         ptr.children.put(word.charAt(i), new Node(word.charAt(i)));
         ptr = ptr.children.get(word.charAt(i));
         i++;
      }
      wordCount++;
      ptr.isWord = true;
   }
   
   /**
    * Find word in trie.
    * @param word to find
    * @return True if word found. False if word not found.
    */
   public boolean find(String word) {
      return false;
   }
   
   /**
    * Suggest list of words in trie by providing a starting word or letter
    * @param word to start suggestion
    */
   public List<String> suggest(String word) {
      if(word == null)
         return null;
      
      int i=0;
      Node ptr = head;
      while(i < word.length() && ptr.children.containsKey(word.charAt(i))) {
         ptr = ptr.children.get(word.charAt(i));
         i++;
      }
      if(ptr.isWord)
         System.out.println(word);
      word = word.substring(0, word.length() - 1);
      return dfsPrint(word, ptr);       
   }
   
   /**
    * Print Trie with depth first search
    * @param word - suggested word to start printing from
    * @param nodePtr - suggested node to start printing from
    */
   private List<String> dfsPrint(String prefix, Node nodePtr) {
      if(nodePtr == null)
         return null;
      
      StringBuffer wordBuffer = new StringBuffer();
      List<String> wordsList = new ArrayList<String>();
      
      for(Node keyNode : nodePtr.children.values()) {
         wordBuffer.append(nodePtr.letter);
         dfsPrintHelper(prefix, keyNode, wordBuffer, wordsList);
         wordBuffer.deleteCharAt(wordBuffer.length()-1);
      }
      return wordsList;
   }

   /**
    * Helper for printing trie with depth first search
    * @param word
    * @param nodePtr
    * @param wordBuffer
    */
   private void dfsPrintHelper(String word, Node nodePtr, StringBuffer wordBuffer, List<String> wordsList) {
      if(nodePtr == null)
         return;
      if(nodePtr.isWord) {
         System.out.println(word + wordBuffer.toString() + nodePtr.letter);
         wordsList.add(word + wordBuffer.toString() + nodePtr.letter);
      }

      for(Node keyNode : nodePtr.children.values()) {
         wordBuffer.append(nodePtr.letter);
         dfsPrintHelper(word, keyNode, wordBuffer, wordsList);
         wordBuffer.deleteCharAt(wordBuffer.length()-1);
      } 
   }
   
   /**
    * Print all words in current trie
    * @return
    */
   public List<String> seeTrie() {
      if(head == null)
         return null;
      
      return dfsPrint(" ", head);
   }
   
   /**
    * Load Trie from dictionary words in linux
    * @return
    */
   public int loadTrie() {
      File file = new File("/usr/share/dict/words");
      
      BufferedReader br = null;
      try {
         br = new BufferedReader(new FileReader(file));
         
         String word;
         while ((word = br.readLine()) != null) {
            System.out.println(word);
            insert(word);
         } 
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      finally {
         if(br != null) 
            try {
               br.close();
            } catch (IOException e) {
               e.printStackTrace();
            }         
      } 
      return wordCount;
   }
   
   public int getWordCount() {
      return wordCount;
   }

   public void setWordCount(int wordCount) {
      this.wordCount = wordCount;
   }
   
   public static void main(String[] args) {
      Trie trie = new Trie();
      trie.loadTrie();
      
      boolean wordFound = trie.find("whippy");
      System.out.println("is Whippy in Trie: " + wordFound + "\n");
      
      /*
      trie.insert("carro");
      trie.insert("ca");
      trie.insert("marmol");
      trie.insert("caracol");
      trie.insert("mora");

      boolean wordFound = trie.find("carro");
      System.out.println("is Carro in Trie: " + wordFound + "\n");
      
      boolean wordFound2 = trie.find("caracol");
      System.out.println("is caracol in trie: " + wordFound2 + "\n");
      
      System.out.println("\nSuggest ca");
      trie.suggest("ca");
      
      System.out.println("\nSuggest car");
      trie.suggest("car"); 
      
      System.out.println("\nSee Trie");
      trie.seeTrie();*/
   }
}
