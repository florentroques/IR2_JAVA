package fr.upem.concurrence.td3;

import java.util.ArrayDeque;

public class SynchronizedBuffer<E> {
	private final ArrayDeque<E> deque;
	private final int dequeMaxSize;

	public SynchronizedBuffer(int maxSize) {
		this.dequeMaxSize = maxSize;
		this.deque = new ArrayDeque<>();
	}

	public void put(E e) throws InterruptedException {
		synchronized (deque) {
			while (isFull()) {
				// Pour faire wait, il faut détenir le verrou
				// car on le lache ensuite
				//
				deque.wait();
			}

			deque.add(e); // ou deque.offer(e);
			// deque.notify();
			deque.notifyAll();
		}
	}

	public E take() throws InterruptedException {
		synchronized (deque) {
			while (isEmpty()) {
				// Pour faire wait, il faut détenir le verrou
				// car on le lache ensuite
				//
				deque.wait();
			}

			E e = deque.remove();
//			E e = deque.poll();
			deque.add(e);
			// deque.notify();
			deque.notifyAll();
			return e;
		}
	}

	// A appeler que si je détiens le lock sur deque
	private boolean isFull() {
		return deque.size() >= dequeMaxSize;
	}

	private boolean isEmpty() {
		return deque.isEmpty();
	}
}
