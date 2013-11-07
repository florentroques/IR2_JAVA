package fr.upem.java_avance.td2.hash;

import java.util.Arrays;

public class Hash {
	private final String[] array;
	private int currentSize;

	public Hash(int capacity) {
		if (!isPowerOfTwo(capacity)) {
			throw new IllegalArgumentException("Capacity must be a power of 2");
		}

		array = new String[capacity];
	}
	
	private boolean isPowerOfTwo(int number) {
		return ((number & (number -1)) == 0);
	}

	private int getIndex(String s) {
		/**
		 * hashCode renvoie un NullPointerException pas besoin d'utiliser
		 * Objects.requireNonNull();
		 */
		int index = s.hashCode() & 0x7FFFFFFF % array.length;

		for (;;) {
			String element = array[index];

			if (element == null) {
				return index;
			}

			if (element.equals(s)) {
				return -1;
			}

			index = (index + 1) % array.length;
		}
	}

	public void add(String s) {
		if (currentSize == array.length) {
			throw new IllegalStateException("Hash Table is already full");
		}
		
		int index = getIndex(s);
		if (index == -1) {
			return;
		}		
		
		array[index] = s;
		currentSize++;
	}

	protected String dump() {
		return Arrays.toString(array);
	}

	public boolean contains(String s) {
		return getIndex(s) == -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		String separator = "";
		
		for (String s : array) {
			if (s == null){
				continue;
			}
			
			sb.append(separator).append(s);
			separator = ", ";			
		}
		
		return sb.append(']').toString();
		
//		return Arrays.stream(array)
//					 .filter(e->e!=null)
//					 .collect(Collectors.joining(", ","[","]"));
	}

	public static void main(String[] args) {
		Hash h = new Hash(2);
		h.add("toto");
		h.add("tito");
//		h.add("tote");
		System.out.println(h);
	}

}
