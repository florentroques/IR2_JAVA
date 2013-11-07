package fr.upem.algo.graphes;

import java.util.Arrays;
import java.util.Random;

public class TestGraphe {
	private static void usage() {
		System.out.println("Usage : Test");
	}

	public static void main(String[] args) {
		if (args.length != 0)
			usage();
		else {
			Random alea = new Random();
			Graphe[] sommet = new Graphe[7];
			// creation des sommets
			System.out.println("\tListe des couples (sommet, info)");
			for (int i = 0; i < sommet.length; i++) {
				sommet[i] = new Graphe(i, alea.nextInt(10));
				System.out.print(sommet[i] + " ");
			}
			// creation aleatoire des aretes
			System.out.println("\n\tListe des aretes");
			for (int i = 0; i < sommet.length; i++)
				for (int j = i + 1; j < sommet.length; j++)
					if (alea.nextBoolean()) {
						Graphe.arete(sommet[i], sommet[j]);
						System.out.println(sommet[i] + " -- " + sommet[j]);
					}
			// parcours profondeur des sommets
			System.out.println("\tParcours en profondeur");
			Graphe.parcoursProfondeur(Arrays.asList(sommet));
			// parcours profondeur des sommets
			System.out.println("\tParcours en largeur");
			Graphe.parcoursLargeur(Arrays.asList(sommet));
			// existeChemin
			System.out
					.println("\tChemin en profondeur entre les sommets 3 et 5");
			Graphe.existeChemin(Arrays.asList(sommet), sommet[3], sommet[5]);
			System.out.println("\tPlus court chemin entre les sommets 3 et 5");
			Graphe.plusCourtChemin(Arrays.asList(sommet), sommet[3], sommet[5]);
		}
	}
}