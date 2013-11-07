package fr.upem.tpnote2013;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.function.Consumer;

import org.junit.Test;

@SuppressWarnings("static-method")
public class HashTrieTest {
  @Test
  public void testAdd() {
    HashTrie trie = new HashTrie();
    trie.add("foo");
    assertTrue(trie.contains("foo"));
  }
  
  @Test
  public void testAdd2() {
    HashTrie trie = new HashTrie();
    trie.add("foo");
    assertFalse(trie.contains("bar"));
  }
  
  @Test
  public void testAddEmpty() {
    HashTrie trie = new HashTrie();
    trie.add("");
    assertTrue(trie.contains(""));
  }
  
  @Test
  public void testContains() {
    HashTrie trie = new HashTrie();
    trie.add("hello");
    trie.add("hellboy");
    assertTrue(trie.contains("hello"));
    assertTrue(trie.contains("hellboy"));
    assertFalse(trie.contains("hell"));
  }
  
  @Test
  public void testContainsEmpty() {
    HashTrie trie = new HashTrie();
    assertFalse(trie.contains(""));
  }
  
  @Test(expected=NullPointerException.class)
  public void testNull() {
    HashTrie trie = new HashTrie();
    trie.add(null);
  }
  
  @Test(expected=NullPointerException.class)
  public void testNull2() {
    HashTrie trie = new HashTrie();
    trie.contains(null);
  }
  
  @Test
  public void testAddALot() {
    HashTrie trie = new HashTrie();
    for(int i = 'a'; i <= 'z'; i++) {
      for(int j = 'a'; j <= 'z'; j++) {
        trie.add("" + i + j);
      }
    }
    
    for(int i = 'a'; i <= 'z'; i++) {
      for(int j = 'a'; j <= 'z'; j++) {
        assertTrue(trie.contains("" + i + j));
      }
    }
  }
  
  @Test
  public void testForAllEmpty() {
    HashTrie trie = new HashTrie();
    trie.forAll(new Consumer<String>() {
      @Override
      public void accept(String s) {
        fail();
      }
    });
  }
  
  @Test
  public void testForAll() {
    HashTrie trie = new HashTrie();
    trie.add("hello");
    trie.add("hellboy");
    HashSet<String> set = new HashSet<>();
    trie.forAll(new Consumer<String>() {
      @Override
      public void accept(String s) {
        set.add(s);
      }
    });
    assertTrue(set.contains("hello"));
    assertTrue(set.contains("hellboy"));
    assertFalse(set.contains("hell"));
  }
  
  @Test
  public void testForAllEmptyString() {
    HashTrie trie = new HashTrie();
    trie.add("");
    trie.forAll(new Consumer<String>() {
      @Override
      public void accept(String s) {
        assertEquals("", s);
      }
    });
  }
  
  @Test
  public void testPrefixContains1() {
    HashTrie trie = new HashTrie();
    HashTrie prefix = trie.prefix("ouch");
    prefix.add("y");
    assertTrue(prefix.contains("y"));
  }
  
  @Test
  public void testPrefixContains2() {
    HashTrie trie = new HashTrie();
    trie.add("hello");
    HashTrie prefix = trie.prefix("hell");
    assertTrue(prefix.contains("o"));
    assertFalse(prefix.contains("hello"));
  }
  
  @Test
  public void testPrefixContains3() {
    HashTrie trie = new HashTrie();
    trie.prefix("zorg");
    assertFalse(trie.contains("zorg"));
  }
  
  @Test
  public void testPrefixAdd() {
    HashTrie trie = new HashTrie();
    trie.add("hello");
    HashTrie prefix = trie.prefix("hell");
    prefix.add("boy");
    assertTrue(prefix.contains("boy"));
    assertTrue(trie.contains("hellboy"));
  }
  
  @Test
  public void testPrefixAdd2() {
    HashTrie trie = new HashTrie();
    HashTrie prefix2 = trie.prefix("foo").prefix("bar");
    prefix2.add("");
    assertTrue(trie.contains("foobar"));
  }
}