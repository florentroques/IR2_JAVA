package fr.upem.tpnote2013;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.function.Consumer;

import org.junit.Test;

@SuppressWarnings("static-method")
public class ListTrieTest {
  @Test
  public void testAdd() {
    Trie trie = new ListTrie();
    trie.add("foo");
    assertTrue(trie.contains("foo"));
  }
  
  @Test
  public void testAdd2() {
    Trie trie = new ListTrie();
    trie.add("foo");
    assertFalse(trie.contains("bar"));
  }
  
  @Test
  public void testAddEmpty() {
    Trie trie = new ListTrie();
    trie.add("");
    assertTrue(trie.contains(""));
  }
  
  @Test
  public void testContains() {
    Trie trie = new ListTrie();
    trie.add("hello");
    trie.add("hellboy");
    assertTrue(trie.contains("hello"));
    assertTrue(trie.contains("hellboy"));
    assertFalse(trie.contains("hell"));
  }
  
  @Test
  public void testContainsEmpty() {
    Trie trie = new ListTrie();
    assertFalse(trie.contains(""));
  }
  
  @Test(expected=NullPointerException.class)
  public void testNull() {
    Trie trie = new ListTrie();
    trie.add(null);
  }
  
  @Test(expected=NullPointerException.class)
  public void testNull2() {
    Trie trie = new ListTrie();
    trie.contains(null);
  }
  
  @Test
  public void testAddALot() {
    Trie trie = new ListTrie();
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
    Trie trie = new ListTrie();
    trie.forAll(new Consumer<String>() {
      @Override
      public void accept(String s) {
        fail();
      }
    });
  }
  
  @Test
  public void testForAll() {
    Trie trie = new ListTrie();
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
    Trie trie = new ListTrie();
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
    Trie trie = new ListTrie();
    Trie prefix = trie.prefix("ouch");
    prefix.add("y");
    assertTrue(prefix.contains("y"));
  }
  
  @Test
  public void testPrefixContains2() {
    Trie trie = new ListTrie();
    trie.add("hello");
    Trie prefix = trie.prefix("hell");
    assertTrue(prefix.contains("o"));
    assertFalse(prefix.contains("hello"));
  }
  
  @Test
  public void testPrefixContains3() {
    Trie trie = new ListTrie();
    trie.prefix("zorg");
    assertFalse(trie.contains("zorg"));
  }
  
  @Test
  public void testPrefixAdd() {
    Trie trie = new ListTrie();
    trie.add("hello");
    Trie prefix = trie.prefix("hell");
    prefix.add("boy");
    assertTrue(prefix.contains("boy"));
    assertTrue(trie.contains("hellboy"));
  }
  
  @Test
  public void testPrefixAdd2() {
    Trie trie = new ListTrie();
    Trie prefix2 = trie.prefix("foo").prefix("bar");
    prefix2.add("");
    assertTrue(trie.contains("foobar"));
  }
}