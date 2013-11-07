package fr.upem.concurrence.td2;

public class Point {
	private int x;
	private int y;
	private Object lock = new Object();

	public void move(int x, int y) {
		synchronized (lock) {
			this.x = x;
			this.y = y;
		}
	}

	@Override
	public String toString() {
		synchronized (lock) {
			return "(" + x + ',' + y + ')';
		}
	}

	public static void main(String[] args) {
		Point p = new Point();
		for (int i = 0; i < 2; i++) {
			int id = i;
			new Thread(() -> {
				for (;;) {
					p.move(id, id);
					System.out.println(p);
				}
			}).start();
		}
	}
}
