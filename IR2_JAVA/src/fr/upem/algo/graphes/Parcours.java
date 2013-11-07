package fr.upem.algo.graphes;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;

public class Parcours {
	public static void PP(Graph g) {
		boolean[] visited = new boolean[g.getVerticesNumber()];
		int count = 0;

		for (int s = 0; s < g.getVerticesNumber(); s++) {
			if (visited[s] == false) {
				count = PPrec(s, g, visited, count);
			}
		}
	}

	private static int PPrec(int s, Graph g, boolean[] visited, int count) {
		visited[s] = true;

		for (Iterator<Integer> it = g.neighbors(s); it.hasNext();) {
			int successeur = it.next();

			if (visited[successeur] == false) {
				return count + PPrec(successeur, g, visited, count);
			}
		}

		// for (int r = 0; s < g.getVerticesNumber(); s++) {
		// if (visited[r] == false) {
		// return count + PPrec(s, g, visited, count);
		// }
		// }

		return count;
	}

	public static void PL(Graph g) {
		boolean[] visited = new boolean[g.getVerticesNumber()];

		for (int s = 0; s < g.getVerticesNumber(); s++) {
			if (visited[s] == false) {
				PLit(s, g, visited);
			}
		}
	}

	private static void PLit(int s, Graph g, boolean[] visited) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.add(s);
		visited[s] = true;

		while (!deque.isEmpty()) {
			s = deque.remove();

			for (int r = 0; s < g.getVerticesNumber(); s++) {
				if (visited[r] == false) {
					deque.add(r);
					visited[r] = true;
				}
			}
		}
	}

	public static int shortestPath(Graph g, int sommet1, int sommet2) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		int[] predecesseurs = new int[g.getVerticesNumber()];
		int[] distance = new int[g.getVerticesNumber()];

		Arrays.fill(predecesseurs, -1);
		Arrays.fill(distance, -1);

		deque.add(sommet1);
		distance[sommet1] = 0;

		while (!deque.isEmpty()) {
			sommet1 = deque.remove();

			if (sommet1 == sommet2) {
				return distance[sommet2];
			}

			for (int r = 0; sommet1 < g.getVerticesNumber(); r++) {
				if (distance[r] == -1) {
					deque.add(r);
					distance[r] = distance[sommet1] + 1;
					predecesseurs[r] = sommet1;
				}
			}
		}

		return distance[sommet2];
	}
}
