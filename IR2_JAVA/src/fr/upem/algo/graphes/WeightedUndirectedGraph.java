package fr.upem.algo.graphes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class WeightedUndirectedGraph implements Graph {
	private final LinkedList<Edge>[] listsArray;

	@SuppressWarnings("unchecked")
	public WeightedUndirectedGraph(int size) {
		this.listsArray = (LinkedList<Edge>[]) new LinkedList<?>[size];
	}

	@Override
	public int getVerticesNumber() {
		return listsArray.length;
	}

	@Override
	public int getEdgesNumber() {
		int edgesNumber = 0;

		for (int i = 0; i < listsArray.length; i++) {
			edgesNumber += listsArray[i] == null ? 0 : listsArray[i].size();

		}

		return edgesNumber;
	}

	@Override
	public boolean existEdge(Edge e) {
		LinkedList<Edge> ll = listsArray[e.getFrom()%listsArray.length];

		if (ll == null) {
			return false;
		}

		for (Edge we : ll) {
			if (e.getTo() == we.getTo()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void addEdge(Edge e) {
		if (existEdge(e)) {
			return;
		}

		if (listsArray[e.getFrom()%listsArray.length] == null) {
			listsArray[e.getFrom()%listsArray.length] = new LinkedList<>();
		}

		listsArray[e.getFrom()%listsArray.length].add(e);
	}

	@Override
	public void removeEdge(Edge e) {
		LinkedList<Edge> ll = listsArray[e.getFrom()%listsArray.length];
		ll.remove((Edge) e);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of vertices : ").append(getVerticesNumber())
				.append('\n').append("Number of edges : ")
				.append(getEdgesNumber()).append('\n');
		sb.append("digraph G {").append(System.getProperty("line.separator"));
		
		for (int i = 0; i < getVerticesNumber(); i++) {
			if (listsArray[i] != null) {
				for (Edge e : listsArray[i]) {
					sb.append("  ")
					  .append(i)
					  .append(" -> ")
					  .append(e.getTo())
					  .append(System.getProperty("line.separator"));
				}
			}
		}
		
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		int testSize = 10;
		int from, to, value;

		WeightedUndirectedGraph wg = new WeightedUndirectedGraph(testSize);
		for (int i = 0; i < testSize; i++) {
			from = (int) (Math.random() * testSize);
			to = (int) (Math.random() * testSize);
			value = (int) (Math.random() * testSize);

			wg.addEdge(new Edge(from, to, value));
		}

		System.out.println(wg);

	}

	@Override
	public Iterator<Integer> neighbors(int s) {
		Iterator<Edge> it = listsArray[s].iterator();

		return new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Integer next() {
				if (hasNext()) {
					Edge e = it.next();
					return e.getTo();
				}
				throw new NoSuchElementException();
			}

		};
	}
}
