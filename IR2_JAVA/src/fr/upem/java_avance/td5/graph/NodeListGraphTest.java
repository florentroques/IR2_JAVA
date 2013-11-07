package fr.upem.java_avance.td5.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.Test;

@SuppressWarnings("static-method")
public class NodeListGraphTest {
  @Test
  public void testVerticesCount() {
    NodeListGraph graph = new NodeListGraph(2);
    assertEquals(2, graph.verticesCount());
    NodeListGraph graph2 = new NodeListGraph(55);
    assertEquals(55, graph2.verticesCount());
    NodeListGraph graph3 = new NodeListGraph(747);
    assertEquals(747, graph3.verticesCount());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidVerticesCount() {
    Graph graph = new NodeListGraph(-17);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidVerticesCount2() {
    Graph graph = new NodeListGraph(0);
  }
  
  @Test
  public void testHasEdgeEmpty() {
    NodeListGraph graph = new NodeListGraph(34);
    for(int  i = 0; i < graph.verticesCount(); i++) {
      for(int j = 0; j < graph.verticesCount(); j++) {
        assertFalse(graph.hasEdge(i, j));
      } 
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testHasEdgeValid1() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.hasEdge(-1, 3);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testHasEdgeValid2() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.hasEdge(2, -1);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testHasEdgeValid3() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.hasEdge(5, 2);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testHasEdgeValid4() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.hasEdge(3, 5);
  }
  
  @Test
  public void testGetWeightEmpty() {
    NodeListGraph graph = new NodeListGraph(41);
    for(int i = 0; i < graph.verticesCount(); i++) {
      for(int j = 0; j < graph.verticesCount(); j++) {
        assertEquals(Graph.NO_EDGE, graph.getWeight(i, j));
      } 
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testGetWeightValid1() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.getWeight(-1, 3);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testGetWeightValid2() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.getWeight(2, -1);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testGetWeightValid3() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.getWeight(5, 2);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testGetWeightValid4() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.getWeight(3, 5);
  }
  
  @Test
  public void testAddEdge() {
    NodeListGraph graph = new NodeListGraph(7);
    graph.addEdge(3, 4, 2);
    assertTrue(graph.hasEdge(3, 4));
    assertEquals(2, graph.getWeight(3, 4));
  }
  
  @Test
  public void testAddEdgeTwice() {
    NodeListGraph graph = new NodeListGraph(7);
    graph.addEdge(3, 4, 2);
    graph.addEdge(3, 4, 3);
    assertTrue(graph.hasEdge(3, 4));
    assertEquals(3, graph.getWeight(3, 4));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testAddEdgeValid1() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.addEdge(-1, 3, 7);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testAddEdgeValid2() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.addEdge(2, -1, 8);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testAddEdgeValid3() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.addEdge(5, 2, 9);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testAddEdgeValid4() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.addEdge(3, 5, 10);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testAddEdgeNoEdge() {
    NodeListGraph graph = new NodeListGraph(3);
    graph.addEdge(1, 1, Graph.NO_EDGE);
  }
  
  @Test
  public void testAddEdgeALot() {
    NodeListGraph graph = new NodeListGraph(17);
    ThreadLocalRandom random = ThreadLocalRandom.current();
    IntStream.range(0, 1000).forEach(index -> {
      int i = random.nextInt(17);
      int j = random.nextInt(17);
      int value = random.nextInt(10_000) - 5_000;
      graph.addEdge(i, j, value);
      assertTrue(graph.hasEdge(i, j));
      assertEquals(value, graph.getWeight(i, j));
    });
  }
  
  @Test
  public void testRemoveEdge() {
    NodeListGraph graph = new NodeListGraph(7);
    graph.addEdge(3, 4, 2);
    assertEquals(2, graph.removeEdge(3, 4));
    assertFalse(graph.hasEdge(3, 4));
    assertEquals(Graph.NO_EDGE, graph.getWeight(3, 4));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testRemoveEdgeValid1() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.removeEdge(-1, 3);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testRemoveEdgeValid2() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.removeEdge(2, -1);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testRemoveEdgeValid3() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.removeEdge(5, 2);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testRemoveEdgeValid4() {
    NodeListGraph graph = new NodeListGraph(5);
    graph.removeEdge(3, 5);
  }
  
  @Test
  public void testRemoveEdgeALot() {
    NodeListGraph graph = new NodeListGraph(10);
    for(int i = 0; i < graph.verticesCount(); i++) {
      for(int j = 0; j < graph.verticesCount(); j++) {
        int vertex = graph.removeEdge(i, j);
        assertEquals(Graph.NO_EDGE, vertex);
        
        graph.addEdge(i, j, i * graph.verticesCount() + j);
      } 
    }
    
    for(int i = 0; i < graph.verticesCount(); i++) {
      for(int j = 0; j < graph.verticesCount(); j++) {
        int vertex = graph.removeEdge(i, j);
        assertEquals(i * graph.verticesCount() + j, vertex);
        
        int vertex2 = graph.removeEdge(i, j);
        assertEquals(Graph.NO_EDGE, vertex2);
      } 
    }
  }
  
  @Test
  public void testNeighborsEmptyHasNext() {
    NodeListGraph graph = new NodeListGraph(6);
    assertFalse(graph.neighbors(0).hasNext());
  }
  
  @Test(expected=NoSuchElementException.class)
  public void testNeighborsEmptyNext() {
    NodeListGraph graph = new NodeListGraph(6);
    graph.neighbors(0).next();
  }
  
  @Test
  public void testNeighborsOneEdge() {
    NodeListGraph graph = new NodeListGraph(6);
    graph.addEdge(1, 2, 3);
    Iterator<Integer> iterator = graph.neighbors(1);
    assertTrue(iterator.hasNext());
    assertEquals(2, (int)iterator.next());
    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      fail();
    } catch(NoSuchElementException e) {
      // ok !
    }
  }
  
  @Test
  public void testNeighborsNoHasNext() {
    NodeListGraph graph = new NodeListGraph(10);
    for(int i = 0; i < 10; i++) {
      graph.addEdge(5, i, -1);  
    }
    
    HashSet<Integer> result = new HashSet<>();
    HashSet<Integer> expected = new HashSet<>();
    Iterator<Integer> iterator = graph.neighbors(5);
    for(int i = 0; i < 10; i++) {
      expected.add(i);
      result.add(iterator.next());
    }
    assertEquals(expected, result);
    
    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      fail();
    } catch(NoSuchElementException e) {
      // ok !
    }
  }
  
  @Test(timeout = 5000)
  public void testNeighborsNonDestructive() {
    NodeListGraph graph = new NodeListGraph(12);
    for(int i = 0; i < 12; i++) {
      graph.addEdge(5, i, 67);  
    }
    Iterator<Integer> neighbors = graph.neighbors(5);
    while(neighbors.hasNext()) {
      neighbors.next();
    }
    for(int i = 0; i < 12; i++) {
      assertEquals(67, graph.getWeight(5, i));  
    }
  }
  
  @Test(timeout = 5000)
  public void testNeighborSeveralHasNext() {
    NodeListGraph graph = new NodeListGraph(14);
    graph.addEdge(3, 7, 2);
    graph.addEdge(3, 5, 3);
    graph.addEdge(7, 3, 4);
    Iterator<Integer> neighbors = graph.neighbors(3);
    assertTrue(neighbors.hasNext());
    int vertex1 = neighbors.next();
    for(int i = 0; i < 5; i++) {
      assertTrue(neighbors.hasNext());
    }
    int vertex2 = neighbors.next();
    assertFalse(neighbors.hasNext());
    assertTrue((vertex1 == 5 && vertex2 == 7) ||
               (vertex1 == 7 && vertex2 == 5));
  }
  
  @Test
  public void testRemove() {
    NodeListGraph graph = new NodeListGraph(11);
    graph.addEdge(3, 10, 13);
    Iterator<Integer> neighbors = graph.neighbors(3);
    assertEquals(10, (int)neighbors.next());
    neighbors.remove();
    assertFalse(graph.hasEdge(3, 10));
    assertEquals(Graph.NO_EDGE, graph.getWeight(3, 10));
  }
  
  @Test(expected = IllegalStateException.class)
  public void testRemoveInvalid() {
    NodeListGraph graph = new NodeListGraph(21);
    graph.addEdge(20, 19, 20);
    Iterator<Integer> neighbors = graph.neighbors(20);
    neighbors.remove();
  }
  
  @Test
  public void testRemoveTwiceInvalid() {
    NodeListGraph graph = new NodeListGraph(21);
    graph.addEdge(20, 19, 20);
    Iterator<Integer> neighbors = graph.neighbors(20);
    neighbors.next();
    neighbors.remove();
    assertFalse(graph.hasEdge(20, 19));
    assertEquals(Graph.NO_EDGE, graph.getWeight(20, 19));
    try {
      neighbors.remove();
      fail();
    } catch(IllegalStateException e) {
      // ok !
    }
  }
  
  @Test
  public void testRemoveALot() {
    NodeListGraph graph = new NodeListGraph(50);
    for(int i = 0; i < 50; i++) {
      for(int j = 0; j < i; j++) {
        graph.addEdge(i, j, i + j);
      } 
    }
    
    for(int i = 0; i < 50; i++) {
      Iterator<Integer> neighbors = graph.neighbors(i);
      for(int j = 0; j < i; j++) {
        assertTrue(neighbors.hasNext());
        neighbors.next();
        neighbors.remove();
      }
      assertFalse(neighbors.hasNext());
    }
    
    for(int i = 0; i < 50; i++) {
      for(int j = 0; j < 50; j++) {
        assertFalse(graph.hasEdge(i, j));
        assertEquals(Graph.NO_EDGE, graph.getWeight(i, j));
      }
    }
  }
}