package fr.upem.algo.graphes;

import java.util.Iterator;

public class DirectedGraph implements Graph {
	private final Matrix matrix;

	public DirectedGraph(int size) {
		this.matrix = new Matrix(size);
	}

	@Override
	public int getEdgesNumber() {
		return matrix.getEdgesNumber();
	}

	@Override
	public boolean existEdge(Edge e) {
		return matrix.getEdge(e) == 1;
	}

	@Override
	public void addEdge(Edge e) {
		matrix.setEdge(e, 1);
	}

	@Override
	public void removeEdge(Edge e) {
		matrix.setEdge(e, 0);
	}

	@Override
	public int getVerticesNumber() {
		return matrix.getVerticesNumber();
	}

	@Override
	public String toString() {
		return matrix.toString();
	}

	public static void main(String[] args) {
		int testSize = 10;
		int from;
		int to;

		DirectedGraph dg = new DirectedGraph(testSize);
		for (int i = 0; i < testSize; i++) {
			from = (int) (Math.random() * testSize);
			to = (int) (Math.random() * testSize);

			dg.addEdge(new Edge(from, to));
		}

		System.out.println(dg);

	}

	@Override
	public Iterator<Integer> neighbors(int s) {
		// TODO Auto-generated method stub
		return null;
	}
}
