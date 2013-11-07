package fr.upem.concurrence.td3;

public class Consumer {
	private void periodicallyDisplayMessage() {
		for(;;) {
			System.out.println("message de test");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
