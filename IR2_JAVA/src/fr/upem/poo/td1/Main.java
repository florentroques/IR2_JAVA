package fr.upem.poo.td1;

import java.util.List;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		MockConnectionService mcs = new MockConnectionService();
		List<Connection> listConnections = mcs.getConnections();

		Statistics<Connection> statsConnections = Statistics.getStatistics(
				listConnections, new Projection<Connection>() {
					@Override
					public long getValue(Connection c) {
						return c.getEnd() - c.getStart();
					}

				}, new Predicat<Connection>() {
					@Override
					public boolean filter(Connection c) {
						return c.getEnd() == -1;
					}
				});

		System.out.println("Moyenne connection : "
				+ statsConnections.getAverage());
		System.out.println("Ecart type connection : "
				+ statsConnections.getStandardDeviation());

		MockContractService mcs2 = new MockContractService();
		List<Contract> listContracts = mcs2.getContracts();

		Statistics<Contract> statsPriceContracts = Statistics.getStatistics(
				listContracts, new Projection<Contract>() {
					@Override
					public long getValue(Contract c) {
						return c.getPrice();
					}

				}, new Predicat<Contract>() {
					@Override
					public boolean filter(Contract c) {
						return c.getPrice() < 0;
					}
				});

		System.out.println("Moyenne price contract : "
				+ statsPriceContracts.getAverage());
		System.out.println("Ecart type price contract : "
				+ statsPriceContracts.getStandardDeviation());

		Statistics<Contract> statsAgeContracts = Statistics.getStatistics(
				listContracts, new Projection<Contract>() {
					@Override
					public long getValue(Contract c) {
						return c.getAge();
					}

				}, new Predicat<Contract>() {
					@Override
					public boolean filter(Contract c) {
						return c.getPrice() < 0;
					}
				});

		System.out.println("Moyenne age contract : "
				+ statsAgeContracts.getAverage());
		System.out.println("Ecart type age contract : "
				+ statsAgeContracts.getStandardDeviation());
	}
}
