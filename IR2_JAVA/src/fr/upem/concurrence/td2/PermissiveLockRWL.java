package fr.upem.concurrence.td2;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class PermissiveLockRWL {
	private int version = 0;
	private long lastUpdate = System.nanoTime();
	private final ReadLock readLock;
	private final WriteLock writeLock;

	public PermissiveLockRWL() {
		ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
		readLock = rwl.readLock();
		writeLock = rwl.writeLock();
	}

	private void update(long currentTime) {
		// writeLock.lock();
		// try {
		// if (currentTime > lastUpdate + 1000000000L) {
		// lastUpdate = currentTime;
		// version++;
		// }
		// } finally {
		// writeLock.unlock();
		// }

		boolean updated = false;

		readLock.lock();
		try {
			if (updated) {
				readLock.unlock();
				// Dès qu'une thread passe dans writeLock.lock()
				// les thread sont bloquée au niveau précédent readLock.lock();
				writeLock.lock();

				try {
					if (currentTime > lastUpdate + 1000000000L) {
						lastUpdate = currentTime;
						version++;
					}
				} finally {
					writeLock.unlock();
				}
			}
		} finally {
			if (!updated) {
				readLock.unlock();
			}
		}

	}

	private String display(int id) {
		readLock.lock();
		try {
			return lastUpdate + " " + version + " " + id;
		} finally {
			readLock.unlock();
		}
	}

	public static void main(String[] args) {
		final PermissiveLockRWL pl = new PermissiveLockRWL();

		// reader threads
		for (int i = 0; i < 10; i++) {
			final int id = i;
			new Thread(() -> {
				for (;;) {
					System.out.println(pl.display(id));
				}
			}).start();
		}

		// writer thread
		new Thread(() -> {
			for (;;) {
				long currentTime = System.nanoTime();
//				if (currentTime > (pl.getLastUpdate() + 1000000000L)) {
//					pl.setVersion(pl.getVersion() + 1);
//					pl.setLastUpdate(currentTime);
//				}
				pl.update(currentTime);
			}
		}).start();
	}
}