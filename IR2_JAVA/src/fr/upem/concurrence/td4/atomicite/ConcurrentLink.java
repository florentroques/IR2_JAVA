package fr.upem.concurrence.td4.atomicite;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ConcurrentLink<E> {
	private final E element; // publication de l'objet
	private volatile ConcurrentLink<E> next;
	@SuppressWarnings("rawtypes")
	private final static AtomicReferenceFieldUpdater arfuNextCL = AtomicReferenceFieldUpdater
			.newUpdater(ConcurrentLink.class, ConcurrentLink.class, "next");

	public ConcurrentLink(E firstElement) {
		this.element = firstElement;
	}

	@SuppressWarnings("unchecked")
	public void addLast(E element) {
		ConcurrentLink<E> tail = this;
		ConcurrentLink<E> newTail = new ConcurrentLink<>(element);

		for (;;) {
			while (tail.next != null) {
				tail = tail.next;
			}

			if (arfuNextCL.compareAndSet(tail, null, newTail)) {
				return;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		ConcurrentLink<E> tail = this;
		
		while (tail.next != null) {
			builder.append(tail.element).append(',');			
			tail = tail.next;
		}
		
		builder.append(tail.element);
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		ConcurrentLink<String> cls = new ConcurrentLink<String>("a");
		cls.addLast("b");
		cls.addLast("c");
		cls.addLast("d");
		
		System.out.println(cls);
	}

}
