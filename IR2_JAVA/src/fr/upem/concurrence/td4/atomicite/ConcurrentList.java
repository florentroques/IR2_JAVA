package fr.upem.concurrence.td4.atomicite;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ConcurrentList<E> {
	private final Link<E> head;
	private volatile Link<E> tail;
	@SuppressWarnings("rawtypes")
	private final static AtomicReferenceFieldUpdater arfuTailList = AtomicReferenceFieldUpdater
			.newUpdater(ConcurrentList.class, ConcurrentList.class, "next");

	private static class Link<E> {
		private final E element;
		private volatile Link<E> next;
		@SuppressWarnings("rawtypes")
		private final static AtomicReferenceFieldUpdater arfuNextLink = AtomicReferenceFieldUpdater
				.newUpdater(Link.class, Link.class, "next");

		public Link(E e) {
			this.element = e;
		}
	}

	public ConcurrentList(E e) {
		this.head = this.tail = new Link<>(e);
	}

	@SuppressWarnings("unchecked")
	private void addLast(E e) {
		Link<E> newTail = new Link<>(e);
		Link<E> oldTail = this.tail;

		for (;;) {
			if (Link.arfuNextLink.compareAndSet(tail, null, newTail)) {
				break;
			}
			
			oldTail = oldTail.next;
		}
		
		arfuTailList.compareAndSet(tail, oldTail, newTail);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		ConcurrentList<E> tail = this;

		while (tail.next != null) {
			builder.append(tail.element).append(',');
			tail = tail.next;
		}

		builder.append(tail.element);

		return builder.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
