package fr.upem.algo.graphes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Edge e1 = new Edge(3, 5);
		Edge e2 = new Edge(5, 2);
		Edge e3 = new Edge(0, 1);
		Edge e4 = new Edge(1, 3);
		Edge e5 = new Edge(3, 2);

		WeightedUndirectedGraph wuc = new WeightedUndirectedGraph(7);
		wuc.addEdge(e1);
		wuc.addEdge(e2);
		wuc.addEdge(e3);
		wuc.addEdge(e4);
		wuc.addEdge(e5);
		System.out.println(wuc);

		System.out.println(Parcours.shortestPath(wuc, 5, 2));

		// try {
		// Graph g =
		// load("/home/6ire2a/froques/workspace-lambda/IR2_JAVA/src/fr/upem/algo/graphes/gnutella.dot");
		// System.out.println(g);
		// } catch (FileNotFoundException e) {
		// System.out.println("test");
		// }

	}

	// public static Graph load(String path) throws FileNotFoundException {
	//
	// File fichier = new File(path);
	//
	// if (!fichier.exists()) {
	// throw new FileNotFoundException("The file not exist");
	// }
	//
	// FileInputStream fis = new FileInputStream(fichier);
	// byte[] data = new byte[(int) fichier.length()];
	// try {
	// fis.read(data);
	// fis.close();
	// String s = new String(data, "UTF-8");
	//
	// Graph retour = new WeightedUndirectedGraph(10);
	//
	// String[] elements = s.split("\\{\n");
	// elements = elements[1].split(";\n");
	//
	// for (String element : elements) {
	//
	// try {
	// String[] first = element.split(" -> ");
	// String[] second = first[1].split("='");
	// String[] fird = second[1].split("'");
	//
	// int label = Integer.parseInt(fird[0]);
	// int origine = Integer.parseInt(first[0]);
	// int destination = Integer
	// .parseInt(second[0].split(" \\[")[0]);
	//
	// retour.addEdge(new Edge(label, origine, destination));
	// } catch (Exception e) {
	// }
	// }
	//
	// return retour;
	//
	// } catch (IOException e) {
	// }
	//
	// return null;
	// }

	public static Graph load(String path) throws FileNotFoundException {

		File fichier = new File(path);

		if (!fichier.exists()) {
			throw new FileNotFoundException("The file not exist");
		}

		FileInputStream fis = new FileInputStream(fichier);
		byte[] data = new byte[(int) fichier.length()];
		try {
			fis.read(data);
			fis.close();
			String s = new String(data, "UTF-8");

			Graph retour = new WeightedUndirectedGraph(10);

			String[] elements = s.split("\\{\n");
			elements = elements[1].split(";\n");

			for (String element : elements) {

				try {
					element = element.replaceAll(" ", "");
					String[] first = element.split("->");
					String[] second = first[1].split("='");

					int origine = Integer.parseInt(first[0]);
					int destination = Integer
							.parseInt(second[0].split(" \\[")[0]);

					retour.addEdge(new Edge(origine, destination));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}

			return retour;

		} catch (IOException e) {
		}

		return null;
	}

}
