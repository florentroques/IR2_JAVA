package fr.upem.algo.graphes;

import java.util.Iterator;

public interface Graph {
	public int getVerticesNumber();

	public int getEdgesNumber();

	public boolean existEdge(Edge e);

	public void addEdge(Edge e);

	public void removeEdge(Edge e);

	public String toString();

	public Iterator<Integer> neighbors(int s);
}
