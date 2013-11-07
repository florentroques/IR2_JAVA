package fr.upem.java_avance.td2.hash;

import java.util.Arrays;
import java.util.Objects;

public class Hash2 {
	private String[] hashTable;
	private int currentSize;

	public Hash2(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity must be positive");
		}

		if (isPowerOfTwo(capacity)) {
			hashTable = new String[capacity];
		} else {
			/**
			 * on récupère le bit le plus à gauche et on décale de 1 vers la
			 * gauche pour récupérer la puissance de 2 supérieure la plus proche
			 * de capacity
			 */
			hashTable = new String[Integer.highestOneBit(capacity) << 1];
		}
	}

	private boolean isPowerOfTwo(int number) {
		return ((number & (number - 1)) == 0);
	}

	private int getIndex(String s) {
		/**
		 * hashCode renvoie un NullPointerException pas besoin d'utiliser
		 * Objects.requireNonNull();
		 * 
		 * & length -1 car on est dans le cas particulier des puissances de 2
		 * ex:
		 * 16 : 10000
		 * 15 : 1111 -> dans tableau taille 16 index de 0 à 15
		 */
		int index = s.hashCode() & 0x7FFFFFFF & (hashTable.length - 1);

		for (;;) {
			String element = hashTable[index];

			if (element == null) {
				return index;
			}

			if (element.equals(s)) {
				return -1;
			}

			index = (index + 1) & (hashTable.length -1 );
		}
	}

	public void add(String s) {
		if (currentSize > hashTable.length / 2) {
			resizeHashTable();
		}

		int index = getIndex(s);
		if (index == -1) {
			return;
		}

		hashTable[index] = s;
		currentSize++;
	}

	private void addHashTable(String[] stringArray) {
		for (String s : stringArray) {
			if (s == null) {
				continue;
			}

			add(s);
		}
	}

	public void addAll(Hash2 hash2) {
		addHashTable(hash2.hashTable);
	}

	private void resizeHashTable() {
		String[] tmp = Arrays.copyOf(hashTable, hashTable.length);
		hashTable = new String[hashTable.length << 1];
		currentSize = 0;

		addHashTable(tmp);
	}

	private boolean intersectInternal(Hash2 hash2, String[] hashTable) {
		for (String s : hashTable) {
			if (s == null) {
				continue;
			}

			if (hash2.contains(s)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean intersect(Hash2 hash2) {
		Objects.requireNonNull(hash2);
		
		if (hash2.hashTable.length == 0 || hashTable.length == 0) {
			return false;
		}

		if (currentSize > hash2.currentSize) {
			return intersectInternal(this, hash2.hashTable);
		} else {
			return intersectInternal(hash2, hashTable);
		}		
	}

	protected String dump() {
		return Arrays.toString(hashTable);
	}

	public boolean contains(String s) {
		return getIndex(s) == -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		String separator = "";

		for (String s : hashTable) {
			if (s == null) {
				continue;
			}

			sb.append(separator).append(s);
			separator = ", ";
		}

		return sb.append(']').toString();

		// Version abrégée avec 1.8
		// return Arrays.stream(array)
		// .filter(e->e!=null)
		// .collect(Collectors.joining(", ","[","]"));
	}

	public static void main(String[] args) {
		Hash2 h2 = new Hash2(1);
		h2.add("toto");
		h2.add("tito");

		Hash2 h1 = new Hash2(1);
		h1.add("tota");
		h1.add("titi");
//		System.out.println(h2.hashTable.length);
//		h2.addAll(h1);
//		System.out.println(h2.hashTable.length);

		// h.add("tote");
		System.out.println(h1);
		System.out.println(h2);
//		System.out.println(h2.intersect(h1));
//		System.out.println(h2);
		
	}

}