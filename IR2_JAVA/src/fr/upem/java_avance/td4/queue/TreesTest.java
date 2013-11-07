package fr.upem.java_avance.td4.queue;

import static org.junit.Assert.assertEquals;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

import org.junit.Test;

public class TreesTest {
	private static List<Integer> map(final List<Tree> list) {
		class MappedList extends AbstractList<Integer> implements RandomAccess {
			@Override
			public int size() {
				return list.size();
			}

			@Override
			public Integer get(int index) {
				return list.get(index).getId();
			}
		}
		return new MappedList();
	}

	@Test
	public void testBreadthFirst() {
		Tree tree = new Tree(0, new Tree(1, new Tree(3), new Tree(4)),
				new Tree(2));
		assertEquals(map(Trees.breadthFirstSearch(tree)),
				Arrays.asList(0, 1, 2, 3, 4));
	}

	@Test
	public void testBreadthFirst2() {
		Tree tree = new Tree(0, new Tree(1, new Tree(3), new Tree(4)),
				new Tree(2, new Tree(5), new Tree(6)));
		assertEquals(map(Trees.breadthFirstSearch(tree)),
				Arrays.asList(0, 1, 2, 3, 4, 5, 6));
	}
}