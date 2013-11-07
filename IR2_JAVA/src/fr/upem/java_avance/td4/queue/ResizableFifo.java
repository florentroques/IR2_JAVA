package fr.upem.java_avance.td4.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Objects;

public class ResizableFifo<E> extends AbstractQueue<E> {
	// !implements Queue<E>
	// car sinon il faut redéfinir trop de méthodes
	private Object[] array;
	private int head;
	private int tail;

	public ResizableFifo(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity must be > 0");
		}

		if (!isPowerOfTwo(capacity)) {
			capacity = Integer.highestOneBit(capacity) << 1;
		}

		this.array = new Object[capacity];
	}

	private boolean isPowerOfTwo(int number) {
		return ((number & (number - 1)) == 0);
	}

	@Override
	public boolean offer(E element) {
		Objects.requireNonNull(element);

		array[tail] = element;
		tail = (tail + 1) & (array.length - 1);
		if (head == tail) {
			resize();
		}

		return true;
	}

	private void resize() {
		Object[] copy = new Object[array.length << 1];
		System.arraycopy(array, head, copy, 0, array.length - head);
		
		if (tail != 0) {
			System.arraycopy(array, 0, copy, array.length - head, tail);
		}
		
		head = 0;
		tail = array.length;
		array = null;
		array = copy;
	}

	@Override
	public E poll() {
//		if (head == tail) {
		if (isEmpty()) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E element = (E) array[head];
		array[head] = null;
		head = (head + 1) & (array.length - 1);		
		return element;
	}

	@Override
	public boolean isEmpty() {
		return array[head] == null && head == tail;
	}

	@Override
	public String toString() {
		Object first = array[head];
		if (head == tail && first == null) {
			return "[]";
		}

		StringBuilder builder = new StringBuilder();
		builder.append('[').append(first);

		int i = (head + 1) & (array.length - 1);
		while (i != tail) {
			builder.append(", ").append(array[i]);
			i = (i + 1) & (array.length - 1);
		}

		return builder.append(']').toString();
	}

	public int size() {
		if (head == tail && array[head] != null) {
			return array.length;
		}

		return (tail - head + array.length) & (array.length - 1);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int currentIndex = head;
			
			@Override
			public boolean hasNext() {
				return currentIndex != tail;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				E element = (E) array[currentIndex];
				currentIndex = currentIndex + 1 & (array.length - 1);				
				return element;
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return (E) array[head];
	}

}
