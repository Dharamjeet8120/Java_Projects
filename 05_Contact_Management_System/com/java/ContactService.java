package com.java;

import java.util.Map;
import java.util.TreeMap;

public class ContactService {

	static Map<String, String> contacts = new TreeMap<>();

	public static void addContact() {

		String name = IO.readln("Enter Name: ");
		String phone = IO.readln("Enter Phone Number: ");

		contacts.put(name, phone);

		IO.println("Contact Added Successfully!");
	}

	public static void viewContacts() {

		if (contacts.isEmpty()) {

			IO.println("No Contacts Found!");
			return;
		}

		contacts.forEach((name, phone) -> IO.println(name + " : " + phone));
	}

	public static void searchContact() {

		String name = IO.readln("Enter Name: ");

		String phone = contacts.get(name);

		if (phone != null) {

			IO.println("Phone Number : " + phone);

		} else {

			IO.println("Contact Not Found!");
		}
	}

	public static void deleteContact() {

		String name = IO.readln("Enter Name: ");

		if (contacts.remove(name) != null) {

			IO.println("Contact Deleted Successfully!");

		} else {

			IO.println("Contact Not Found!");
		}
	}
}