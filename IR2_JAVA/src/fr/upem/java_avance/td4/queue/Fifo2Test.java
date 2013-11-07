package fr.upem.java_avance.td4.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
 
public class Fifo2Test { 
  @Test
  public void testOfferPollOneElement() {
    Fifo<String> fifo = new Fifo<>(3);
    fifo.offer("hello");
    String s = fifo.poll();
    assertEquals("hello", s);
  }
  
  @Test
  public void testOfferPoll() {
    Fifo<Integer> fifo = new Fifo<>(2);
    fifo.offer(9);
    assertEquals(9, (int)fifo.poll());
    fifo.offer(2);
    fifo.offer(37);
    assertEquals(2, (int)fifo.poll());
    fifo.offer(12);
    assertEquals(37, (int)fifo.poll());
    assertEquals(12, (int)fifo.poll());
  }
  
  @Test
  public void testFullToEmpty() {
    Fifo<Object> fifo = new Fifo<>(20);
    for(int i = 0; i < 20; i++) {
      fifo.offer(i);
    }
    assertEquals(0, (int)fifo.poll());
    fifo.offer("foo");
    for(int i = 1; i < 20; i++) {
      assertEquals(i, (int)fifo.poll());
    }
    assertEquals("foo", fifo.poll());
  }
}