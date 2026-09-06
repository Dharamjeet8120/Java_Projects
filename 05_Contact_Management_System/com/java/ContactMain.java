package com.java;

public class ContactMain {

	public static void main(String[] args) {

		while (true) {

			IO.println("\n===== Contact Management System =====");
			IO.println("1. Add Contact");
			IO.println("2. View Contacts");
			IO.println("3. Search Contact");
			IO.println("4. Delete Contact");
			IO.println("5. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				ContactService.addContact();
				break;

			case 2:
				ContactService.viewContacts();
				break;

			case 3:
				ContactService.searchContact();
				break;

			case 4:
				ContactService.deleteContact();
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