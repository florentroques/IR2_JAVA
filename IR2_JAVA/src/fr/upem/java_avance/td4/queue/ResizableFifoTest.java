package fr.upem.java_avance.td4.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ResizableFifoTest {
	@Test(expected = IllegalArgumentException.class)
	public void testFifoCapacity() {
		new ResizableFifo<String>(-3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFifoCapacity2() {
		new ResizableFifo<Integer>(0);
	}

	@Test
	public void testEmpty() {
		ResizableFifo<Double> fifo = new ResizableFifo<>(1);
		assertNull(fifo.poll());
	}

	@Test
	public void testFull() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 100000; i++) {
			assertTrue(fifo.offer(i));
		}
	}

	@Test
	public void testContract() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 10000; i++) {
			fifo.offer(i);
		}
		for (int i = 0; i < 10000; i++) {
			assertEquals(i, (int) fifo.poll());
		}
	}

	@Test
	public void testSize() {
		ResizableFifo<String> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 10000; i++) {
			assertEquals(i, fifo.size());
			fifo.offer("foo");
		}
	}

	@Test
	public void testIsEmpty() {
		ResizableFifo<String> fifo = new ResizableFifo<>(1);
		assertTrue(fifo.isEmpty());
		fifo.offer("oof");
		assertFalse(fifo.isEmpty());
		fifo.offer("rab");
		assertFalse(fifo.isEmpty());
		fifo.poll();
		fifo.poll();
		assertTrue(fifo.isEmpty());
	}

	public void testPeek() {
		ResizableFifo<String> fifo = new ResizableFifo<>(1);
		assertNull(fifo.peek());
	}

	@Test
	public void testPeek2() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		fifo.offer(117);
		assertEquals(117, (int) fifo.peek());
		fifo.poll();
		fifo.offer(145);
		fifo.offer(541);
		assertEquals(145, (int) fifo.peek());
		fifo.poll();
		assertEquals(541, (int) fifo.peek());
	}

	@Test(expected = NoSuchElementException.class)
	public void testElement() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		fifo.element();
	}

	@Test
	public void testIterator() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 10000; i++) {
			fifo.offer(i);
		}
		int i = 0;
		for (int value : fifo) {
			assertEquals(i++, value);
		}
		assertEquals(10000, fifo.size());
	}

	@Test
	public void testIterator2() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		fifo.offer(222);
		fifo.poll();

		for (int i = 0; i < 100; i++) {
			fifo.offer(i);
		}
		int i = 0;
		for (int value : fifo) {
			assertEquals(i++, value);
		}
		assertEquals(100, fifo.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorRemove() {
		ResizableFifo<String> fifo = new ResizableFifo<>(1);
		fifo.offer("foooo");
		fifo.iterator().remove();
	}

	@Test
	public void testToString() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 7537; i++) {
			fifo.offer(i);
		}

		StringBuilder builder = new StringBuilder().append('[');
		for (Iterator<Integer> it = fifo.iterator(); it.hasNext();) {
			builder.append(it.next());
			if (it.hasNext()) {
				builder.append(", ");
			}
		}
		String text = builder.append(']').toString();

		assertEquals(text, fifo.toString());
	}

	@Test
	public void testAddAll() {
		ResizableFifo<Integer> fifo = new ResizableFifo<>(1);
		for (int i = 0; i < 3; i++) {
			fifo.offer(i);
		}

		ResizableFifo<Object> fifo2 = new ResizableFifo<>(1);
		fifo2.addAll(fifo);

		assertEquals(fifo2.size(), fifo.size());

		Iterator<Object> it2 = fifo2.iterator();
		Iterator<Integer> it = fifo.iterator();
		while (it2.hasNext()) {
			assertTrue(it.hasNext());
			assertEquals(it2.next(), it.next());
		}
	}
}
