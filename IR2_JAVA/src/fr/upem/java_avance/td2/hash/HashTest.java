package fr.upem.java_avance.td2.hash;

import org.junit.Test;

import static org.junit.Assert.*;
 
public class HashTest {
  @Test(expected=IllegalArgumentException.class)
  public void testHashCapacityNegative() {
    new Hash(-2);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testHashCapacityPowerOfTwo1() {
    new Hash(7); // not a power of two
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testHashCapacityPowerOfTwo2() {
    new Hash(13); // not a power of two
  }
  
  @Test
  public void testAddAndDump1() {
    Hash hash = new Hash(4);
    hash.add("foo");
    assertTrue(hash.dump().contains("foo"));
  }
  
  @Test
  public void testAddAndDump2() {
    Hash hash = new Hash(8);
    hash.add("hello");
    hash.add("world");
    assertTrue(hash.dump().contains("hello"));
    assertTrue(hash.dump().contains("world"));
  }
  
  @Test
  public void testEmptyDump() {
    new Hash(2).dump();
  }
  
  @Test(expected=NullPointerException.class)
  public void testAddNull() {
    Hash hash = new Hash(4);
    hash.add(null);
  }
  
  @Test
  public void testAddAndContains() {
    Hash hash = new Hash(4);
    assertFalse(hash.contains("baz"));
    hash.add("foo");
    assertFalse(hash.contains("baz"));
    hash.add("bar");
    assertFalse(hash.contains("baz"));
    assertTrue(hash.contains("foo"));
    assertTrue(hash.contains("bar"));
  }
   
  @Test(expected=NullPointerException.class)
  public void testContainsNull() {
    Hash hash = new Hash(4);
    hash.contains(null);
  }
  
  @Test
  public void testToString() {
    Hash hash = new Hash(4);
    hash.add("hoo");
    hash.add("bo");
    String text = hash.toString();
    assertTrue(text.equals("[hoo, bo]") ||
               text.equals("[bo, hoo]"));
  }
  
  @Test
  public void testFull() {
    Hash hash = new Hash(1024);
    for(int i=0; i<1024; i++) {
      hash.add(Integer.toString(i));
    }
  }
  
  @Test(expected=IllegalStateException.class)
  public void testFullPlusOne() {
    Hash hash = new Hash(1024);
    for(int i=0; i<1024; i++) {
      hash.add(Integer.toString(i));
    }
    hash.add("bang");
  }
}