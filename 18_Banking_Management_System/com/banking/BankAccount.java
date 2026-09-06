package com.banking;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private int accountNumber;
	private String accountHolder;
	private double balance;

	private List<Transaction> transactions = new ArrayList<>();

	public BankAccount(int accountNumber, String accountHolder, double balance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() {
		return balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void deposit(double amount) {
		balance += amount;

		transactions.add(new Transaction("Deposit", amount, balance));
	}

	public boolean withdraw(double amount) {

		if (amount > balance) {
			return false;
		}

		balance -= amount;

		transactions.add(new Transaction("Withdraw", amount, balance));

		return true;
	}

	@Override
	public String toString() {
		return accountNumber + " | " + accountHolder + " | Balance: Rs." + String.format("%.2f", balance);
	}
}