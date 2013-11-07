package fr.upem.concurrence.td3;

import java.util.ArrayDeque;

public class ProdConsSimple {
	private final ArrayDeque<String> deque;

	public ProdConsSimple() {
		deque = new ArrayDeque<>();
	}

	public static void printMsgLoop(String msg, long time) {
		try {
			for (;/* !Thread.isInterrupted() */;) {
				System.out.println(msg);

				Thread.sleep(time);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			// throw new
			// AssertionError("No InterruptedException expected here");
		}
	}

	/**
	 * Cette Thread s'arrête dès qu'il y a une interruption
	 * 
	 * @param msg
	 * @param time
	 * @return Runnable
	 */
	public Runnable createProducer(String msg, int time) {
		return () -> {
			try {
				for (; !Thread.interrupted();) {
					Thread.sleep(time);

					// this = Runnable courant
					synchronized (deque) {
						deque.add(msg);
					}
				}
			} catch (InterruptedException ie) {
				return;
			}
		};
	}

	public Runnable createConsumer(int id, int time) {
		return () -> {
			try {
				for (; !Thread.interrupted();) {
					Thread.sleep(time);

					// this = Runnable courant
					synchronized (deque) {
						if (!deque.isEmpty()) {
							String msg = deque.remove();
							System.out
									.println("cons" + id + " consumes " + msg);
						}
					}
				}
			} catch (InterruptedException ie) {
				return;
			}
		};
	}	
}
