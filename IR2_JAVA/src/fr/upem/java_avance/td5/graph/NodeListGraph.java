package fr.upem.java_avance.td5.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class NodeListGraph implements Graph {
    private final LinkedList<Pair>[] array;

    @SuppressWarnings("unchecked")
    public NodeListGraph(int verticesCount) {
        if (verticesCount <= 0) {
            throw new IllegalArgumentException();
        }

        LinkedList<Pair>[] array = (LinkedList<Pair>[]) new LinkedList<?>[verticesCount];

        Arrays.fill(array, new LinkedList<>());
//        for (int i = 0; i < verticesCount; i++) {
//            array[i] = new LinkedList<>();
//        }
        this.array = array;
    }

    private void checkBounds(int src, int dst) {
        if (src < 0 || dst < 0 || src >= array.length || dst >= array.length) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int verticesCount() {
        return array.length;
    }

    @Override
    public boolean hasEdge(int src, int dst) {
        checkBounds(src, dst);

        for (Pair pair : array[src]) {
            if (pair.dst == dst) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getWeight(int src, int dst) {
        checkBounds(src, dst);
        
        for (Pair pair : array[src]) {
            if (pair.dst == dst) {
                return pair.weight;
            }
        }

        return NO_EDGE;
    }

    @Override
    public void addEdge(int src, int dst, int weight) {
        checkBounds(src, dst);
        
        if (weight == NO_EDGE) {
            throw new IllegalArgumentException();
        }
        
        array[src].add(new Pair(dst, weight));
    }

    @Override
    public int removeEdge(int src, int dst) {
        checkBounds(src, dst);
        for (Pair pair : array[src]) {
            if (pair.dst == dst) {
                int weight = pair.weight;
                array[src].remove(pair);
                return weight;
            }
        }

        return NO_EDGE;
    }

    @Override
    public Iterator<Integer> neighbors(int vertex) {
        // TODO Auto-generated method stub

        // Iterator<Arc> it
        // hasNext it.hasNext

        return null;
    }

    private static class Pair {
        private final int dst;
        private final int weight;

        Pair(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }

    }

}
