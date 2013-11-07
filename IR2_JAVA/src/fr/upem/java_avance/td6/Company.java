package fr.upem.java_avance.td6;

import java.util.Objects;

public class Company implements Comparable<Company> {
    private final String name;

    public Company(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Company)) {
            return false;
        }
        Company company = (Company) obj;
        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Company company) {
        return name.compareTo(company.name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
