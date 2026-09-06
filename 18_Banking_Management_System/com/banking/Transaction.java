package com.banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

	private String type;
	private double amount;
	private double balance;
	private LocalDateTime dateTime;

	public Transaction(String type, double amount, double balance) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.dateTime = LocalDateTime.now();
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		return type + " | Amount: Rs." + String.format("%.2f", amount) + " | Balance: Rs."
				+ String.format("%.2f", balance) + " | Date: " + dateTime.format(formatter);
	}
}