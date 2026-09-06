package com.banking;

import java.util.ArrayList;
import java.util.List;

public class BankingService {

	private static List<BankAccount> accounts = new ArrayList<>();

	private static BankAccount currentAccount;

	public static void startApplication() {

		while (true) {

			IO.println("\n========== BANKING SYSTEM ==========");

			IO.println("1. Create Account");
			IO.println("2. Login Account");
			IO.println("3. View All Accounts");
			IO.println("4. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1 -> createAccount();

			case 2 -> loginAccount();

			case 3 -> viewAllAccounts();

			case 4 -> {
				IO.println("Thank You!");
				return;
			}

			default -> IO.println("Invalid Choice!");
			}
		}
	}

	private static void createAccount() {

		IO.println("\n===== CREATE ACCOUNT =====");

		int accountNumber = Integer.parseInt(IO.readln("Account Number: "));

		if (findAccount(accountNumber) != null) {
			IO.println("Account Already Exists!");
			return;
		}

		String accountHolder = IO.readln("Account Holder Name: ");

		double initialBalance = Double.parseDouble(IO.readln("Initial Balance: "));

		if (initialBalance < 0) {
			IO.println("Invalid Balance!");
			return;
		}

		BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);

		accounts.add(account);

		IO.println("Account Created Successfully!");
	}

	private static void loginAccount() {

		int accountNumber = Integer.parseInt(IO.readln("Enter Account Number: "));

		BankAccount account = findAccount(accountNumber);

		if (account == null) {
			IO.println("Account Not Found!");
			return;
		}

		currentAccount = account;

		IO.println("Welcome, " + currentAccount.getAccountHolder() + "!");

		userMenu();
	}

	private static void userMenu() {

		while (true) {

			IO.println("\n===== USER ACCOUNT =====");

			IO.println("1. Account Details");
			IO.println("2. Check Balance");
			IO.println("3. Deposit");
			IO.println("4. Withdraw");
			IO.println("5. Transaction History");
			IO.println("6. Logout");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1 -> accountDetails();

			case 2 -> checkBalance();

			case 3 -> deposit();

			case 4 -> withdraw();

			case 5 -> transactionHistory();

			case 6 -> {
				currentAccount = null;
				IO.println("Logout Successful!");
				return;
			}

			default -> IO.println("Invalid Choice!");
			}
		}
	}

	private static void accountDetails() {

		IO.println("\n===== ACCOUNT DETAILS =====");

		IO.println("Account Number : " + currentAccount.getAccountNumber());

		IO.println("Account Holder : " + currentAccount.getAccountHolder());

		IO.println("Balance        : Rs." + String.format("%.2f", currentAccount.getBalance()));
	}

	private static void checkBalance() {

		IO.println("Current Balance: Rs." + String.format("%.2f", currentAccount.getBalance()));
	}

	private static void deposit() {

		double amount = Double.parseDouble(IO.readln("Enter Deposit Amount: "));

		if (amount <= 0) {
			IO.println("Invalid Amount!");
			return;
		}

		currentAccount.deposit(amount);

		IO.println("Deposit Successful!");

		checkBalance();
	}

	private static void withdraw() {

		double amount = Double.parseDouble(IO.readln("Enter Withdrawal Amount: "));

		if (amount <= 0) {
			IO.println("Invalid Amount!");
			return;
		}

		if (currentAccount.withdraw(amount)) {

			IO.println("Withdrawal Successful!");

			checkBalance();

		} else {

			IO.println("Insufficient Balance!");
		}
	}

	private static void transactionHistory() {

		if (currentAccount.getTransactions().isEmpty()) {

			IO.println("No Transactions Found!");
			return;
		}

		IO.println("\n===== TRANSACTION HISTORY =====");

		for (Transaction transaction : currentAccount.getTransactions()) {

			IO.println(transaction);
		}
	}

	private static void viewAllAccounts() {

		if (accounts.isEmpty()) {
			IO.println("No Accounts Found!");
			return;
		}

		IO.println("\n===== ALL ACCOUNTS =====");

		for (BankAccount account : accounts) {
			IO.println(account);
		}
	}

	private static BankAccount findAccount(int accountNumber) {

		for (BankAccount account : accounts) {

			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}

		return null;
	}
}