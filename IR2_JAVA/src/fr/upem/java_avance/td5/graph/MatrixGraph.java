package fr.upem.java_avance.td5.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixGraph implements Graph {
    private final int[] matrix;
    final int verticesCount;

    public MatrixGraph(int verticesCount) {
        if (verticesCount <= 0) {
            throw new IllegalArgumentException();
        }
        int[] matrix = new int[verticesCount * verticesCount];
        Arrays.fill(matrix, NO_EDGE);
        this.matrix = matrix;
        this.verticesCount = verticesCount;
    }

    @Override
    public int verticesCount() {
        return verticesCount;
    }

    private void checkBounds(int src, int dst) {
        if (src < 0 || dst < 0 || src >= verticesCount || dst >= verticesCount) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean hasEdge(int src, int dst) {
        checkBounds(src, dst);
        return matrix[src * verticesCount + dst] != NO_EDGE;
    }

    @Override
    public int getWeight(int src, int dst) {
        checkBounds(src, dst);
        return matrix[src * verticesCount + dst];
    }

    @Override
    public void addEdge(int src, int dst, int weight) {
        checkBounds(src, dst);

        if (weight == NO_EDGE) {
            throw new IllegalArgumentException();
        }

        matrix[src * verticesCount + dst] = weight;
    }

    @Override
    public int removeEdge(int src, int dst) {
        checkBounds(src, dst);
        int weight = matrix[src * verticesCount + dst];
        matrix[src * verticesCount + dst] = NO_EDGE;

        return weight;
    }

    @Override
    public Iterator<Integer> neighbors(int vertex) {
        if (vertex < 0 || vertex >= verticesCount) {
            throw new IllegalArgumentException();
        }

        return new Iterator<Integer>() {
            private int index = nextEdge(0);
            private int last = -1;

            private int nextEdge(int start) {
                for (int i = start; i < verticesCount; i++) {
                    if (hasEdge(vertex, i)) {
                        return i;
                    }
                }

                return -1;
            }

            @Override
            public boolean hasNext() {
//				return currentIndex < verticesCount &&
//					   matrix[vertex * verticesCount + currentIndex] != NO_EDGE;

                return index != -1;
            }

            @Override
            public Integer next() {
//				return matrix[vertex * verticesCount + index++];
                if (index == -1) {
                    throw new NoSuchElementException();
                }

                int value = last = index;
                index = nextEdge(value + 1);
                return value;
            }

            @Override
            public void remove() {
                if (last == -1) {
                    throw new IllegalStateException();
                }
                
                removeEdge(vertex, last);
                last = -1;
            }

        };
    }
}
