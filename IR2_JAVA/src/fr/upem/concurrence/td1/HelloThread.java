package fr.upem.concurrence.td1;
public class HelloThread {

	public static void main(String[] args) {
		
//		Runnable r0 = new HelloRunnable(0);
//		Runnable r1 = new HelloRunnable(1);
//
//		Thread t0 = new Thread(r0);
//		Thread t1 = new Thread(r1);
//
//		t0.start();
//		t1.start();
		
		int nbThreads = Integer.parseInt(args[0]);

		for (int i = 0; i < nbThreads; i++) {
			//new Thread(new HelloRunnable(i)).start();
			
			int id = i;
			
			new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					System.out.println("hello " + id + " " + j);
				}				
			}).start();
			
			
		}

		/**/
		
		

		/* La thread main meurt, car il n'y a plus rien dans la pile */
	}
}
