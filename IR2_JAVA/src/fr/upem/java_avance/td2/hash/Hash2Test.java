package fr.upem.java_avance.td2.hash;

import org.junit.Test;

import static org.junit.Assert.*;
 
public class Hash2Test {
  @Test(expected=IllegalArgumentException.class)
  public void testHashCapacityNegative() {
    new Hash2(-2);
  }
  
  @Test
  public void testHashNotACapacityPowerOfTwo1() {
    new Hash2(7);  // ok
    new Hash2(13); // ok
  }
  
  @Test
  public void testAddAndDump1() {
    Hash2 hash = new Hash2(4);
    hash.add("foo");
    assertTrue(hash.dump().contains("foo"));
  }
  
  @Test
  public void testAddAndDump2() {
    Hash2 hash = new Hash2(8);
    hash.add("hello");
    hash.add("world");
    String dump = hash.dump();
    assertTrue(dump.contains("hello"));
    assertTrue(dump.contains("world"));
  }
  
  @Test
  public void testEmptyDump() {
    new Hash2(2).dump();
  }
  
  @Test(expected=NullPointerException.class)
  public void testAddNull() {
    Hash2 hash = new Hash2(4);
    hash.add(null);
  }
  
  @Test
  public void testAddAndContains() {
    Hash2 hash = new Hash2(4);
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
    Hash2 hash = new Hash2(4);
    hash.contains(null);
  }
  
  @Test
  public void testResize() {
    Hash2 hash = new Hash2(16);
    for(int i=0; i<1024; i++) {
      hash.add(Integer.toString(i));
    }
  }
  
  @Test
  public void testAddAll() {
    Hash2 hash = new Hash2(4);
    hash.add("ba");
    hash.add("zoo");
    Hash2 hash2 = new Hash2(4);
    hash2.add("ka");
    hash2.add("ramba");
    hash.addAll(hash2);
    assertTrue(hash.contains("ba"));
    assertTrue(hash.contains("zoo"));
    assertTrue(hash.contains("ka"));
    assertTrue(hash.contains("ramba"));
  }
  
  @Test
  public void testIntersect1() {
    Hash2 hash = new Hash2(4);
    hash.add("boo");
    hash.add("bar");
    Hash2 hash2 = new Hash2(4);
    hash2.add("oob");
    hash2.add("rab");
    assertFalse(hash.intersect(hash2));
  }
  
  @Test
  public void testIntersect2() {
    Hash2 hash = new Hash2(1);
    Hash2 hash2 = new Hash2(1);
    for(int i=0; i<1024; i+=2) {
      hash.add(Integer.toString(i));
      hash2.add(Integer.toString(i + 1));
    }
    hash.add("hell");
    hash2.add("hell");
    assertTrue(hash.intersect(hash2));
  }
}