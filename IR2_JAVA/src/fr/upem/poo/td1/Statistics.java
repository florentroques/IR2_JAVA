package fr.upem.poo.td1;

import java.util.List;
import java.util.function.Predicate;

/**
 * 
 * stats (List<? extends T> list, Projection<> proj) { double sum, sum2;
 * 
 * for (T t : list) { if(predicat.filter(t)) { val = proj.getVal(); sum += val;
 * sum2 += val*val; } } }
 * 
 */

public class Statistics<T> {
	private final double average;
	private final double standardDeviation;

	private Statistics(double average, double standardDeviation) {
		this.average = average;
		this.standardDeviation = standardDeviation;
	}

	// public int getTotalConnections(List<Connection> list) {
	// return list.size();
	// }

	// public static double getAverageConnectionTime(List<Connection> list) {
	// // ConnectionService cs = new MockConnectionService();
	// // List<Connection> list = cs.getAllConnections();
	//
	// double sum = 0;
	//
	// for (Connection e : list) {
	// if (e.getEnd() == -1) {
	// continue;
	// }
	//
	// sum += e.getConnectionTime();
	// }
	// return sum / list.size();
	// }
	//
	// public static double getAverageContractPrice(List<Contract> list) {
	// // ContractService cs = new MockContractService();
	// // List<Contract> list = cs.getAllContracts();
	//
	// double sum = 0, sum2 = 0;
	// int val;
	//
	// for (Contract contract : list) {
	// val = contract.getPrice();
	// sum2 += val * val;
	// sum += val;
	// }
	//
	// double average = sum / list.size();
	// // standard deviation = ecart type
	// double stddev = Math.sqrt(sum2 / list.size() - average * average);
	//
	// return stddev;
	// }

	private static double calculateAverage(long sumValues, long numberValues) {
		return sumValues / numberValues;
	}

	private static double calculateStandardDeviation(long squareSum,
			long numberValues, double average) {
		return Math.sqrt(Math.abs((squareSum / numberValues) - average * average));
	}

	public static <T> Statistics<T> getStatistics(List<T> list, Projection<T> proj,
			Predicat<T> predicate) {
		
		long sum = 0, sum2 = 0, val;

		for (T t : list) {
			// getEnd == -1 pour Connection ou true pr Contract
			if (predicate.filter(t)) {
				continue;
			}

			// getEnd-getStart ou getPrice
			val = proj.getValue(t);
			sum += val;
			sum2 += val * val;
		}

		double average = calculateAverage(sum, list.size());
		double standardDeviation = calculateStandardDeviation(sum2,
				list.size(), average);

		return new Statistics<T>(average, standardDeviation);
	}

	public double getAverage() {
		return average;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}
}
