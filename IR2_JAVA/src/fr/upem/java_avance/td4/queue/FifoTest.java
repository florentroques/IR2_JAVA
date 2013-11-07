package fr.upem.java_avance.td4.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
 
public class FifoTest {
  @Test(expected=IllegalArgumentException.class)
  public void testFifoCapacity() {
    new Fifo(-3);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testFifoCapacity2() {
    new Fifo(0);
  }

  @Test(expected=IllegalStateException.class)
  public void testEmpty() {
    Fifo fifo = new Fifo(1);
    fifo.poll();
  }
  
  @Test(expected=IllegalStateException.class)
  public void testFull() {
    Fifo fifo = new Fifo(1);
    fifo.offer(43);
    fifo.offer(7);
  }

  @Test(expected=NullPointerException.class)
  public void testOfferNull() {
    Fifo fifo = new Fifo(234);
    fifo.offer(null);
  }
  
  
  
  @Test
  public void testOfferPoll() {
    Fifo fifo = new Fifo(2);
    fifo.offer(9);
    assertEquals(9, fifo.poll());
    fifo.offer(2);
    fifo.offer(37);
    assertEquals(2, fifo.poll());
    fifo.offer(12);
    assertEquals(37, fifo.poll());
    assertEquals(12, fifo.poll());
  }
  
  @Test
  public void testFullToEmpty() {
    Fifo fifo = new Fifo(20);
    for(int i = 0; i < 20; i++) {
      fifo.offer(i);
    }
    assertEquals(0, fifo.poll());
    fifo.offer("foo");
    for(int i = 1; i < 20; i++) {
      assertEquals(i, fifo.poll());
    }
    assertEquals("foo", fifo.poll());
  }

  @Test
  public void testSize() {
    Fifo fifo = new Fifo(2);
    assertEquals(0, fifo.size());
    fifo.offer("foo");
    assertEquals(1, fifo.size());
    fifo.offer("bar");
    assertEquals(2, fifo.size());
    fifo.poll();
    assertEquals(1, fifo.size());
    fifo.poll();
    assertEquals(0, fifo.size());
  }
  
  @Test
  public void testSizeEmpty() {
    Fifo fifo = new Fifo(1);
    assertEquals(0, fifo.size());
  }
  
  @Test
  public void testSizeFull() {
    Fifo fifo = new Fifo(1);
    fifo.offer("dooh");
    assertEquals(1, fifo.size());
  }
  
  @Test
  public void testIsEmpty() {
    Fifo fifo = new Fifo(2);
    assertTrue(fifo.isEmpty());
    fifo.offer("oof");
    assertFalse(fifo.isEmpty());
    fifo.offer("rab");
    assertFalse(fifo.isEmpty());
    fifo.poll();
    fifo.poll();
    assertTrue(fifo.isEmpty());
  }
  
  @Test
  public void testEmptyToString() {
    Fifo fifo = new Fifo(23);
    assertEquals("[]", fifo.toString());
  }
  
  @Test
  public void testOneElementToString() {
    Fifo fifo = new Fifo(23);
    fifo.offer("joe");
    assertEquals("[joe]", fifo.toString());
  }
  
  @Test
  public void testTwoElementToString() {
    Fifo fifo = new Fifo(23);
    fifo.offer("jane");
    fifo.offer("doe");
    assertEquals("[jane, doe]", fifo.toString());
  }
  
  @Test
  public void testNonMutateToString() {
    Fifo fifo = new Fifo(200);
    ArrayList<Integer> list = new ArrayList<>();
    for(int i = 0; i < 100; i++) {
      fifo.offer(i);
      list.add(i);
    }
    assertEquals(list.toString(), fifo.toString());
    for(int i = 0; i < 100; i++) {
      assertEquals(i, fifo.poll());
    }
  }
  
  @Test
  public void testFullToString() {
    Fifo fifo = new Fifo(99);
    ArrayList<Integer> list = new ArrayList<>();
    for(int i = 0; i < 99; i++) {
      fifo.offer(i);
      list.add(i);
    }
    assertEquals(list.toString(), fifo.toString());
  }
}