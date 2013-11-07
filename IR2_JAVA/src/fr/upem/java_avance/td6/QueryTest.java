package fr.upem.java_avance.td6;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

@SuppressWarnings("static-method")
public class QueryTest {
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
  public void queryAll() throws ParseException {
    Query query = Query.parse("all");
    List<Donor> list = query.execute(DONORS, Donor.class);
    assertEquals(DONORS, list);
  }
  
  @Test
  public void queryAllLimit() throws ParseException {
    Query query = Query.parse("all limit 10");
    List<Donor> list = query.execute(DONORS, Donor.class);
    assertEquals(10, list.size());
    for(int i = 0; i < DONORS.size(); i++) {
      assertEquals(DONORS.get(i), list.get(i));
    }
  }
  
  @Test
  public void queryName() throws ParseException {
    Query query = Query.parse("name");
    List<String> names = query.execute(DONORS, String.class);
    for(int i = 0; i < DONORS.size(); i++) {
      assertEquals(DONORS.get(i).getName(), names.get(i));
    }
  }
  
  @Test
  public void queryNameLimit() throws ParseException {
    Query query = Query.parse("name limit 11");
    List<String> names = query.execute(DONORS, String.class);
    assertEquals(11, names.size());
    for(int i = 0; i < 11; i++) {
      assertEquals(DONORS.get(i).getName(), names.get(i));
    }
  }
  
  @Test
  public void queryGender() throws ParseException {
    Query query = Query.parse("gender");
    List<Gender> genders = query.execute(DONORS, Gender.class);
    for(int i = 0; i < DONORS.size(); i++) {
      assertEquals(DONORS.get(i).getGender(), genders.get(i));
    }
  }
  
  @Test
  public void queryGenderLimit() throws ParseException {
    Query query = Query.parse("gender limit 30");
    List<Gender> genders = query.execute(DONORS, Gender.class);
    assertEquals(30, genders.size());
    for(int i = 0; i < 30; i++) {
      assertEquals(DONORS.get(i).getGender(), genders.get(i));
    }
  }
  
  @Test
  public void queryCompany() throws ParseException {
    Query query = Query.parse("company");
    List<Company> companies = query.execute(DONORS, Company.class);
    for(int i = 0; i < DONORS.size(); i++) {
      assertEquals(DONORS.get(i).getCompany(), companies.get(i));
    }
  }
  
  @Test
  public void queryCompanyLimit() throws ParseException {
    Query query = Query.parse("company limit 20");
    List<Company> companies = query.execute(DONORS, Company.class);
    assertEquals(20, companies.size());
    for(int i = 0; i < 20; i++) {
      assertEquals(DONORS.get(i).getCompany(), companies.get(i));
    }
  }
  
  @Test
  public void queryAmount() throws ParseException {
    Query query = Query.parse("amount");
    List<Long> amounts = query.execute(DONORS, Long.class);
    for(int i = 0; i < DONORS.size(); i++) {
      assertEquals(DONORS.get(i).getAmount(), (long)amounts.get(i));
    }
  }
  
  @Test
  public void queryAmountLimit() throws ParseException {
    Query query = Query.parse("amount limit 77");
    List<Long> amounts = query.execute(DONORS, Long.class);
    assertEquals(77, amounts.size());
    for(int i = 0; i < 77; i++) {
      assertEquals(DONORS.get(i).getAmount(), (long)amounts.get(i));
    }
  }
  
  
}