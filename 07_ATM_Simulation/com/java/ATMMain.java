package com.java;

public class ATMMain {

	public static void main(String[] args) {

		if (!ATMService.login()) {
			return;
		}

		while (true) {

			IO.println("\n===== ATM SYSTEM =====");
			IO.println("1. Check Balance");
			IO.println("2. Deposit");
			IO.println("3. Withdraw");
			IO.println("4. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				ATMService.checkBalance();
				break;

			case 2:
				ATMService.deposit();
				break;

			case 3:
				ATMService.withdraw();
				break;

			case 4:
				IO.println("Thank You...");
				System.exit(0);

			default:
				IO.println("Invalid Choice!");
			}
		}
	}
}