package com.java;

public class AccountMain {

	public static void main(String[] args) {

		while (true) {

			IO.println("\n===== Banking Management System =====");
			IO.println("1. Create Account");
			IO.println("2. View Accounts");
			IO.println("3. Deposit");
			IO.println("4. Withdraw");
			IO.println("5. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				AccountService.createAccount();
				break;

			case 2:
				AccountService.viewAccounts();
				break;

			case 3:
				AccountService.depositAmount();
				break;

			case 4:
				AccountService.withdrawAmount();
				break;

			case 5:
				IO.println("Thank You...");
				System.exit(0);

			default:
				IO.println("Invalid Choice!");
			}
		}
	}
}