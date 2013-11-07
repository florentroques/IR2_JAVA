package fr.upem.poo.td3.refactor_x;

import java.util.Objects;

public class Record {
	private final Account accountSender;
	private final Account accountReceiver;
	private final long sumTransfer;

	public Record(Account accountSender, Account accountReceiver, long sumTransfer) {
		this.accountSender = Objects.requireNonNull(accountSender);
		this.accountReceiver = Objects.requireNonNull(accountReceiver);
		this.sumTransfer = sumTransfer;
	}

	public long getSumTransfer() {
		return sumTransfer;
	}

	public Account getAccountSender() {
		return accountSender;
	}

	public Account getAccountReceiver() {
		return accountReceiver;
	}
	
	@Override
	public String toString() {
	    return "  " + accountSender.getFirstName() + " "
                + accountSender.getLastName() + " --> "
                + sumTransfer + " --> " + " "
                + accountReceiver.getFirstName() + " "
                + accountReceiver.getLastName();
	}
}