package fr.upem.concurrence.td4.atomicite;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.ThreadLocalRandom;

public class LockFreeSet {
	private final AtomicReferenceArray<Object[]> array;

	 private static final Object[] EMPTY_ARRAY = new Object[0];

	public LockFreeSet(int concurrencyLevel) {
		array = new AtomicReferenceArray<>(concurrencyLevel);
		// Arrays.fill(array, EMPTY_ARRAY);
		for (int i = 0; i < concurrencyLevel; i++) {
			array.set(i, EMPTY_ARRAY);
		}
	}

	public boolean add(Object o) {
		int index = o.hashCode() & 0x7FFFFFFF & (array.length() - 1);

		for (;;) {
			Object[] collisionArray = array.get(index);
			int length = collisionArray.length;

			for (int i = 0; i < length; i++) {
				if (collisionArray[i].equals(o)) {
					return false;
				}
			}
			
			Object[] newArray = Arrays.copyOf(collisionArray, length + 1);
			newArray[length] = o;

			if (array.compareAndSet(index, collisionArray, newArray)) {
				return true;
			}
		}
	}

	public boolean contains(Object o) {
		int index = o.hashCode() & (array.length() - 1);
		Object[] collisionArray = array.get(index);
		int length = collisionArray.length;
		for (int i = 0; i < length; i++) {
			if (collisionArray[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		final LockFreeSet set = new LockFreeSet(16);
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				ThreadLocalRandom random = ThreadLocalRandom.current();
				for (;;) {
					Integer value = random.nextInt(10000);
					set.add(value);
					if (!set.contains(value)) {
						System.out.println("error !");
					}
				}
			}).start();
		}
	}
}
