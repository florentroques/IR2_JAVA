package fr.upem.java_avance.td5.graph;

import java.util.Iterator;

/**
 * An oriented graph with values on edges and
 * not on vertices.
 */
public interface Graph {
  /**
   * used by getWeight to indicate that there is no edge.
   */
  public static final int NO_EDGE = Integer.MIN_VALUE;
  
  /**
   * Number of vertices.
   * @return the number of vertices.
   */
  public int verticesCount(); 
  
  /**
   * Returns true if there is an edge between the vertex
   * {@code src} and the vertex {@code dst}.
   * @param src source vertex.
   * @param dst destination vertex.
   * @return true if there is an edge, false otherwise.
   * @throws IllegalArgumentException if src or dst is
   *         not a valid vertex number
   */
  public boolean hasEdge(int src, int dst);
  
  /**
   * Return the weight of an edge.
   * @param src source vertex.
   * @param dst destination vertex.
   * @return the weight of the edge between {@code src}
   *         and {@code dst} or {@code NO_EDGE} if there
   *         is no edge.
   * @throws IllegalArgumentException if src or dst is
   *         not a valid vertex number
   *         
   * @see #hasEdge(int, int)
   */
  public int getWeight(int src, int dst);
  
  /**
   * Add an edge between to vertices or replace it
   * if an edge was already existing 
   * @param src source vertex.
   * @param dst destination vertex.
   * @param weight weight of the edge.
   * @throws IllegalArgumentException if src or dst is
   *         not a valid vertex number
   * @throws IllegalArgumentException if weight is equals
   *        to {@link Graph#NO_EDGE}.
   */
  public void addEdge(int src, int dst, int weight); 
  
  /**
   * Remove the edge between two vertices.
   * @param src src source vertex.
   * @param dst dst destination vertex.
   * @return the value of the removed edge or
   *         {@code NO_EDGE} if there is no edge.
   * @throws IllegalArgumentException if src or dst is
   *         not a valid vertex number
   */
  public int removeEdge(int src, int dst); 
  
  /**
   * Returns all the vertices that are connected to
   * the vertex taken as parameter.
   * The order of the vertices may be different that
   * the insertion order.
   * @param vertex a vertex.
   * @return an iterator on all vertices connected
   *         to the specified vertex.
   * @throws IllegalArgumentException if vertex is
   *         not a valid vertex number
   */
  public Iterator<Integer> neighbors(int vertex);
  
  /**
   * Returns all the edges that are connected to
   * the vertex taken as parameter.
   * @param vertex a vertex.
   * @return an iterator on all edges that have
   *         the vertex taken as parameter as
   *         source.
   */
  //public Iterator<Edge> edges(int vertex);
}
