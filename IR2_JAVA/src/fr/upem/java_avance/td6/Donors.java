package fr.upem.java_avance.td6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Donors {

    private Donors() {

    }

    public static long getTotalDonation(List<Donor> donors) {
//        long totalAmount = 0;
//
//        for (Donor donor : donors) {
//            totalAmount += donor.getAmount();
//        }
//        
//        return totalAmount;

        return donors.stream()
//                   .mapToLong(donor->donor.getAmount())
                     .mapToLong(Donor::getAmount)
                     .sum();
    }

    public static List<Donor> getAllMales(List<Donor> donors) {
//        ArrayList<Donor> maleDonorsList = new ArrayList<>();
//
//        for (Donor donor : donors) {
//            if (donor.getGender().isMale()) {
//                maleDonorsList.add(donor);
//            }
//        }
//
//        return maleDonorsList;
        
        return donors.stream()
                     .filter(donor->donor.getGender().isMale())
                     .collect(Collectors.<Donor>toList());
    }
    
    public static List<Donor> getImportantDonors(List<Donor> donors) {
        return donors.stream()
                     .filter(donor -> donor.getAmount() >= 10000)
                     .sorted(Comparator.<Donor>comparingLong(Donor::getAmount).reversed())
                     .limit(10)
                     .collect(Collectors.<Donor>toList());
    }

    public static Map<Company, List<Donor>> getDonorByCompany(List<Donor> donors) {
        return donors.stream()
                     .collect(Collectors.<Donor, Company>groupingBy(Donor::getCompany));
    }
}
