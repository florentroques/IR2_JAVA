package fr.upem.concurrence.td2;

import java.util.concurrent.locks.ReentrantLock;

public class PermissiveLock {
	private int version = 0;
	private long lastUpdate = System.nanoTime();
	private final ReentrantLock lock = new ReentrantLock();

	public int getVersion() {
		lock.lock();
		try {
			return version;
		} finally {
			lock.unlock();
		}
	}

	public void setVersion(int version) {
		lock.lock();
		try {
			this.version = version;
		} finally {
			lock.unlock();
		}
	}

	public long getLastUpdate() {
		lock.lock();
		try {
			return lastUpdate;
		} finally {
			lock.unlock();
		}
	}

	public void setLastUpdate(long lastUpdate) {
		lock.lock();
		try {
			this.lastUpdate = lastUpdate;
		} finally {
			lock.unlock();
		}
	}
	
	private void update(long currentTime) {
		lock.lock();
		try {
			if (currentTime > lastUpdate + 1000000000L) {
				lastUpdate = currentTime;
				version++;
			}
		} finally {
			lock.unlock();
		}
		
	}
	
	private String display(int id) {
		lock.lock();
		try {
			return lastUpdate + " " + version + " " + id;
		} finally {
			lock.unlock();
		}
	}	

	public static void main(String[] args) {
		final PermissiveLock pl = new PermissiveLock();

		// reader threads
		for (int i = 0; i < 10; i++) {
			final int id = i;
			new Thread(() -> {
				for (;;) {
					System.out.println("lecteur " + id + ": webcam_"
							+ pl.getVersion() + ".jpg " + "("
							+ pl.getLastUpdate() + ")");
				}
			}).start();
		}

		// writer thread
		new Thread(() -> {
			for (;;) {
				long currentTime = System.nanoTime();
				if (currentTime > (pl.getLastUpdate() + 1000000000L)) {
					pl.setVersion(pl.getVersion() + 1);
					pl.setLastUpdate(currentTime);
				}
			}
		}).start();
	}
}