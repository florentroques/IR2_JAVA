package fr.upem.java_avance.td6;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("static-method")
public class DonorsTest {
  private static final List<Donor> DONORS;
  static {
    Company[] companies = {
      new Company("Goole"),
      new Company("HAL"),
      new Company("FakeBook"),
      new Company("TimerWar"),
      new Company("Tviter"),
      new Company("Macrosoft"),
      new Company("NotFliks"),
      new Company("Banana"),
    };
    
    String[] firstNames = {
        "Bob", "Alice", "Vladimir", "Jean",
        "John", "Jane", "Alan", "Pamela",
        "Joe",  "Briana", "Mark", "Mandy",
        "Fredrik", "Michelle", "Charles"
      };
    String[] lastNames = {
        "O'tools", "McFierce", "Olov", "Voldmort",
        "McLane", "Doe", "Rose", "Turing",
        "Block", "Ruff", "Kitchen", "Joy",
        "Hans", "Aachen", "Purple"
      };
    
    Donor[] donors = new Donor[100];
    Random random = new Random(0);
    for(int i=0; i<donors.length; i++) {
      int index = random.nextInt(firstNames.length);
      Donor donor = new Donor(
          firstNames[index] + ' ' +
          lastNames[random.nextInt(lastNames.length)],
          (index % 2 == 0)? Gender.MALE: Gender.FEMALE,
          companies[random.nextInt(companies.length)],
          random.nextInt(50_000));
      donors[i] = donor;
    }
    DONORS = Collections.unmodifiableList(Arrays.asList(donors));
  }
  
  @Test
  public void totalDonation1() {
    Donor first = DONORS.get(0);
    long result = Donors.getTotalDonation(Collections.singletonList(first));
    assertEquals(first.getAmount(), result);
  }

  @Test
  public void totalDonation2() {
    long result = Donors.getTotalDonation(DONORS);
    assertEquals(2430057, result);
  }
  
  @Test
  public void allMales1() {
    List<Donor> allMales = Donors.getAllMales(DONORS);
    for(Donor donor: allMales) {
      assertTrue(donor.getGender() == Gender.MALE);
      assertTrue(donor.getGender().isMale());
    }
  }
  
  @Test
  public void allMales2() {
    List<Donor> allMales = Donors.getAllMales(DONORS);
    HashSet<Donor> allMaleSet = new HashSet<>(allMales);
    for(Donor donor: DONORS) {
      if (donor.getGender().isMale()) {
        assertTrue(allMaleSet.contains(donor));
      }
    }
  }
  
  @Test
  public void importantDonors1() {
    List<Donor> list = Donors.getImportantDonors(DONORS);
    assertEquals(10, list.size());
    for(Donor donor: list) {
      assertTrue(donor.getAmount() >= 10_000);
    }
  }
  
  @Test
  public void importantDonors2() {
    List<Donor> list = Donors.getImportantDonors(DONORS);
    HashSet<Donor> vipSet = new HashSet<>(list);
    long entryBarrier = list.get(9).getAmount();
    for(Donor donor: DONORS) {
      if (vipSet.contains(donor)) {
        assertTrue(donor.getAmount() >= entryBarrier);
      } else {
        assertTrue(donor.getAmount() <= entryBarrier);
      }
    }
  }
  
  @Test
  public void donorByCompany1() {
    Map<Company, List<Donor>> map = Donors.getDonorByCompany(DONORS);
    assertEquals(DONORS.size(), map.values().stream().mapToInt(List<Donor>::size).sum());
  }
  
  @Test
  public void donorByCompany2() {
    Map<Company, List<Donor>> map = Donors.getDonorByCompany(DONORS);
    map.forEach((company, donors) -> {
      for(Donor donor: donors) {
        assertEquals(company, donor.getCompany());
      }
    });
  }
}