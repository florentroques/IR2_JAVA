package fr.upem.poo.td3.refactor_x;

import java.util.ArrayDeque;
import java.util.Deque;

public class Account {
    private final long accountNumber;
    private long balance;
    private final String firstName;
    private final String lastName;
    private final ArrayDeque<Record> listOfLastRecords = new ArrayDeque<>(
            NumberOfRecords);
    private static int NumberOfRecords = 3;

    public Account(long accountNumber, long balance, String firstName,
            String lastName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void fixBalance(Record record, long sumTransfer) {
        balance = balance + sumTransfer;
        addRecord(record);
    }

    public void addRecord(Record record) {
        if (listOfLastRecords.size() == NumberOfRecords) {
            listOfLastRecords.pollLast();
        }

        listOfLastRecords.offerFirst(record);

    }

    public Deque<Record> getListOfLastRecords() {
        return new ArrayDeque<>(listOfLastRecords);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + balance;
    }
}