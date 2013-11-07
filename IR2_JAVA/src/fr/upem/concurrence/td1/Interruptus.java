package fr.upem.concurrence.td1;
public class Interruptus {

	public static void main(String[] args) {

		int nbThreads = Integer.parseInt(args[0]);

		for (int i = 0; i < nbThreads; i++) {
			// new Thread(new HelloRunnable(i)).start();

			int id = i;

			new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					System.out.println("hello " + id + " " + j);
				}
			}).start();

			Thread.interrupted();
			Thread t = new Thread();
			t.isInterrupted();

		}
	}
}
