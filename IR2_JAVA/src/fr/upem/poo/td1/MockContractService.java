package fr.upem.poo.td1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MockContractService implements ContractService{
	
	@Override
	public List<Contract> getContracts() {
		
		LinkedList<Contract> list = new LinkedList<>();
		Random r = new Random(0);
		for (int i=0 ; i<1_000_000 ; i++) {			
			long price = r.nextInt(10_000);
			long age = r.nextInt(100);
			list.add(new Contract(price, age));
		}
		return list;
	}

	
}
