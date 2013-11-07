package fr.upem.concurrence.td1;
public class Test {
	private volatile int value;

	public static void main(String[] args) {
		Test test = new Test();
		for (int i = 0; i < 2; i++) {
			int id = i;
			new Thread(() -> {
				for (;;) {
					test.value = id;
					if (test.value != id) {
						System.out.println("id " + id + ' ' + test.value);
					}
				}
			}).start();
		}
	}
}