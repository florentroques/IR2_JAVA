package fr.upem.algo.graphes;

public class WeightedEdge extends Edge{
	private final int value;
	
	public WeightedEdge(int from, int to, int value) {
		super(from, to);
		this.value = value;
	}
}
