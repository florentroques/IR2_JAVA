package fr.upem.concurrence.td3;

public class Main {

	public static void main(String[] args) {
		ProdConsSimple pcs = new ProdConsSimple();
		for (int i = 0; i < 2; i++) {
			new Thread(pcs.createProducer("producer " + i, 100)).start();
		}
		
		for (int i = 0; i < 3; i++) {
			new Thread(pcs.createConsumer(i, 100)).start();
		}
	}

}
