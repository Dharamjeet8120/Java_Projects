package com.java;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

	static List<Account> accounts = new ArrayList<>();

	public static void createAccount() {

		int no = Integer.parseInt(IO.readln("Account Number: "));
		String name = IO.readln("Account Holder Name: ");

		accounts.add(new Account(no, name, 0));

		IO.println("Account Created Successfully!");
	}

	public static void viewAccounts() {

		if (accounts.isEmpty()) {

			IO.println("No Accounts Found!");
			return;
		}

		for (Account acc : accounts) {

			IO.println(acc);
		}
	}

	public static void depositAmount() {

		int no = Integer.parseInt(IO.readln("Account Number: "));
		double amount = Double.parseDouble(IO.readln("Amount: "));

		boolean found = false;

		for (Account acc : accounts) {

			if (acc.no == no) {

				acc.deposit(amount);

				IO.println("Amount Deposited Successfully!");
				found = true;
				break;
			}
		}

		if (!found) {

			IO.println("Account Not Found!");
		}
	}

	public static void withdrawAmount() {

		int no = Integer.parseInt(IO.readln("Account Number: "));
		double amount = Double.parseDouble(IO.readln("Amount: "));

		boolean found = false;

		for (Account acc : accounts) {

			if (acc.no == no) {

				if (acc.withdraw(amount)) {

					IO.println("Withdrawal Successful!");

				} else {

					IO.println("Insufficient Balance!");
				}

				found = true;
				break;
			}
		}

		if (!found) {

			IO.println("Account Not Found!");
		}
	}
}