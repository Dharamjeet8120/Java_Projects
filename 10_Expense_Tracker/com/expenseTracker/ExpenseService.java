package com.expenseTracker;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

	private static final List<Expense> expenses = new ArrayList<>();

	public static void startApplication() {

		while (true) {

			IO.println("\n1. Add Expense");
			IO.println("2. View Expenses");
			IO.println("3. Total Expense");
			IO.println("4. Exit");

			int choice = Integer.parseInt(IO.readln("Enter choice: "));

			switch (choice) {

			case 1 -> addExpense();

			case 2 -> viewExpenses();

			case 3 -> showTotal();

			case 4 -> {
				IO.println("Thank you for using Expense Tracker!");
				return;
			}

			default -> IO.println("Invalid choice!");
			}
		}
	}

	private static void addExpense() {

		String description = IO.readln("Description: ");

		double amount = Double.parseDouble(IO.readln("Amount: "));

		Expense expense = new Expense(description, amount);

		expenses.add(expense);

		IO.println("Expense added successfully!");
	}

	private static void viewExpenses() {

		if (expenses.isEmpty()) {
			IO.println("No expenses found.");
			return;
		}

		IO.println("\n--- Expense List ---");

		for (Expense expense : expenses) {
			IO.println(expense.toString());
		}
	}

	private static void showTotal() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        IO.println("Total Expense: " + total);
    }
}
