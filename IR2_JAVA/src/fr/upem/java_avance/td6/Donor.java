package fr.upem.java_avance.td6;

import java.util.Objects;

public class Donor {
    private final String name;
    private final Gender gender;
    private final Company company;
    private final long amount;

    public Donor(String name, Gender gender, Company company, long amount) {
        this.name = Objects.requireNonNull(name);
        this.gender = Objects.requireNonNull(gender);
        this.company = Objects.requireNonNull(company);
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Company getCompany() {
        return company;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ' ' + gender + " (" + company + ") " + amount + '$';
    }
}