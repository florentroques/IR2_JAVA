package fr.upem.algo.graphes;

public class Matrix {
	private final int[][] matrix;
	
	public Matrix(int matrixSize) {
		matrix = new int[matrixSize][matrixSize];
	}
	
	public int getEdge(Edge e) {
		return matrix[e.getFrom()][e.getTo()];
	}
	
	public void setEdge(Edge e, int value) {
		matrix[e.getFrom()][e.getTo()] = value;
	}
	
	public int getEdgesNumber() {
		int edgesNumber = 0;
		
		for (int i = 0; i< matrix.length; i++)  {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					edgesNumber++;
				}
			}
		}
		
		return edgesNumber;
	}
	
	public int getVerticesNumber() {
		return matrix.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of vertices : ")
		  .append(getVerticesNumber())
		  .append('\n')
		  .append("Number of edges : ")
		  .append(getEdgesNumber())
		  .append('\n');
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					sb.append(i)
					  .append(" -> ")
					  .append(j)
					  .append('\n');
				}
			}
		}
		return sb.toString();
	}
}
