package com.java;

public class AccountATM {

	private int pin;
	private double balance;

	public AccountATM(int pin, double balance) {

		this.pin = pin;
		this.balance = balance;
	}

	public int getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {

		balance += amount;
	}

	public boolean withdraw(double amount) {

		if (amount > balance) {
			return false;
		}

		balance -= amount;
		return true;
	}
}