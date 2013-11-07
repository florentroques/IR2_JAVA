package fr.upem.concurrence.td2;

import java.util.concurrent.locks.ReentrantLock;

public class LockedPoint {
	private int x;
	private int y;
	private ReentrantLock lock = new ReentrantLock();

	public void move(int x, int y) {
		lock.lock();
		try {
			this.x = x;
			this.y = y;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public String toString() {
		lock.lock();
		try {
			return "(" + x + ',' + y + ')';
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		LockedPoint lp = new LockedPoint();
		for (int i = 0; i < 2; i++) {
			int id = i;
			new Thread(() -> {
				for (;;) {
					lp.move(id, id);
					System.out.println(lp);
				}
			}).start();
		}
	}
}
