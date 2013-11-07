package fr.upem.concurrence.td1;

public class HelloRunnable implements Runnable{
	private final int id;
	
	public HelloRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10000; i++) {
			System.out.println("hello " + id + " " + i);
		}
	}
}
