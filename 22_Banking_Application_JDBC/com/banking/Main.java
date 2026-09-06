package com.banking;

public class Main {

	public static void main(String[] args) {

		try {

			AccountService service = new AccountService();

			while (true) {

				showMenu();

				try {

					int choice = Integer.parseInt(IO.readln("Enter Your Choice: "));

					switch (choice) {

					case 1 -> service.createAccount();

					case 2 -> service.viewAllAccounts();

					case 3 -> service.searchAccount();

					case 4 -> service.depositMoney();

					case 5 -> service.withdrawMoney();

					case 6 -> service.transferMoney();

					case 7 -> service.checkBalance();

					case 8 -> service.updateAccountHolderName();

					case 9 -> service.deleteAccount();

					case 10 -> service.accountCount();

					case 11 -> service.totalBankBalance();

					case 12 -> service.highestBalanceAccount();

					case 13 -> service.lowestBalanceAccount();

					case 14 -> service.transactionHistory();

					case 15 -> service.miniStatement();

					case 16 -> service.bankingDashboard();

					case 17 -> {

						IO.println("\nThank You For Using " + "Banking Management System.");

						IO.println("Application Closed Successfully.");

						return;
					}

					default ->

						IO.println("Invalid Choice! " + "Please Enter 1-17.");
					}

				} catch (NumberFormatException e) {

					IO.println("Invalid Input! " + "Please Enter a Number.");

				} catch (Exception e) {

					IO.println("Service Failure: " + e.getMessage());
				}

				IO.println("\nPress Enter to Continue...");

				IO.readln("");
			}

		} catch (Exception e) {

			IO.println("Application Startup Failed.");

			IO.println("Reason: " + e.getMessage());
		}
	}

	private static void showMenu() {

		IO.println("\n==============================================");

		IO.println("          BANKING MANAGEMENT SYSTEM");

		IO.println("==============================================");

		IO.println(" 1. Create Account");
		IO.println(" 2. View All Accounts");
		IO.println(" 3. Search Account");
		IO.println(" 4. Deposit Money");
		IO.println(" 5. Withdraw Money");
		IO.println(" 6. Transfer Money");
		IO.println(" 7. Check Balance");
		IO.println(" 8. Update Account Holder Name");
		IO.println(" 9. Delete Account");
		IO.println("10. Account Count");
		IO.println("11. Total Bank Balance");
		IO.println("12. Highest Balance Account");
		IO.println("13. Lowest Balance Account");
		IO.println("14. Transaction History");
		IO.println("15. Mini Statement");
		IO.println("16. Banking Dashboard");
		IO.println("17. Exit");

		IO.println("==============================================");
	}
}
