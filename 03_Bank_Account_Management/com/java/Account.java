package com.java;

public class Account {

	int no;
	private String name;
	private double bal;

	public Account(int no, String name, double bal) {

		this.no = no;
		this.name = name;
		this.bal = bal;
	}

	public void deposit(double amount) {

		bal += amount;
	}

	public boolean withdraw(double amount) {

		if (amount > bal) {
			return false;
		}

		bal -= amount;
		return true;
	}

	@Override
	public String toString() {

		return no + " | " + name + " | Balance: " + bal;
	}
}