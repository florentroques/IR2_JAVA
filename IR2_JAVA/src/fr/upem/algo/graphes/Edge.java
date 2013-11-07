package fr.upem.algo.graphes;

public class Edge {
	private final int from;
	private final int to;
	private final int value;
	
	public Edge(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
	
	public Edge (int from, int to) {
		this(from, to, 0);
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		
		Edge e = (Edge)obj;
		
		return from == e.getFrom() && to == e.getTo() && value == e.getValue();
	}
}
