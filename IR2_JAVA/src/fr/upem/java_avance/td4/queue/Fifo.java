package fr.upem.java_avance.td4.queue;

import java.util.Objects;

public class Fifo<E> {
	private final Object[] array;
	private int head;
	private int tail;

	public Fifo(int capacity) {
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

	public void offer(E e) {
		Objects.requireNonNull(e);

		if (head == tail && array[head] != null) {
			throw new IllegalStateException();
		}

		array[tail] = e;
		tail = (tail + 1) & (array.length - 1);
		// tail = (tail + 1) % array.length;
	}

	public E poll() {
		if (head == tail && array[head] == null) {
			throw new IllegalStateException();
		}

		@SuppressWarnings("unchecked")
		E e = (E) array[head];
		array[head] = null;
		head = (head + 1) & (array.length - 1);
		// head = (head + 1) % array.length;

		return e;

		// try {
		// return array[head];
		// } finally {
		// array[head] = null;
		// head = (head + 1) & (array.length - 1);
		// }
	}

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

		// StringBuilder builder = new StringBuilder();
		// String separator = "";
		// builder.append('[');
		// for (int tmp = head; array[head] != null && tmp != tail; tmp = (tmp +
		// 1)
		// & (array.length - 1)) {
		// builder.append(separator).append(array[tmp]);
		// separator = ", ";
		// }

		return builder.append(']').toString();
	}

	public int size() {
		if (head == tail && array[head] != null) {
			return array.length;
		}

		// if (tail > head) {
		// return tail - head;
		// } else if (head > tail) {
		// return tail + array.length - head;
		// }

		return (tail - head + array.length) & (array.length - 1);
	}

}
