package fr.upem.poo.td1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MockConnectionService implements ConnectionService {

	@Override
	public List<Connection> getConnections() {
		LinkedList<Connection> list = new LinkedList<>();
		Random r = new Random(0);
		long start, end;
		for(int i=0;i<1_000_000;i++) {
			start= r.nextInt(1_000_000_000);
			end= start +r.nextInt(24*60*60*1000);
			
			list.add(new Connection(start, end));			
		}
				
		return list;
	}

}
