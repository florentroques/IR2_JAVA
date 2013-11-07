package fr.upem.java_avance.td4.queue;

import java.util.LinkedList;
import java.util.List;

public class Trees {

	public static List<Tree> breadthFirstSearch(Tree tree) {
		LinkedList<Tree> list = new LinkedList<>();
		ResizableFifo<Tree> fifo = new ResizableFifo<>(1);

		fifo.offer(tree);
		list.add(tree);

		while (!fifo.isEmpty()) {
			Tree tmp = fifo.poll();
			
			if (tmp.getLeft() != null) {
				fifo.offer(tmp.getLeft());
				list.add(tmp.getLeft());
			}
			if (tmp.getRight() != null) {
				fifo.offer(tmp.getRight());
				list.add(tmp.getRight());
			}
		}
		
		return list;
	}
}
