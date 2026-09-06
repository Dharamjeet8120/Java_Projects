package com.java;


public class ATMService {

	static AccountATM account = new AccountATM(1234, 10000);

	public static boolean login() {

		int pin = Integer.parseInt(IO.readln("Enter PIN: "));

		if (pin == account.getPin()) {

			IO.println("Login Successful");
			return true;
		}

		IO.println("Invalid PIN");
		return false;
	}

	public static void checkBalance() {

		IO.println("Balance : " + account.getBalance());
	}

	public static void deposit() {

		double amount = Double.parseDouble(IO.readln("Enter Amount: "));

		account.deposit(amount);

		IO.println("Amount Deposited Successfully");
	}

	public static void withdraw() {

		double amount = Double.parseDouble(IO.readln("Enter Amount: "));

		if (account.withdraw(amount)) {

			IO.println("Withdrawal Successful");

		} else {

			IO.println("Insufficient Balance");
		}
	}
}