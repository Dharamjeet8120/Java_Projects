package com.railway;

import java.util.ArrayList;
import java.util.List;

public class RailwayService {

	private static List<Passenger> passengers = new ArrayList<>();

	private static int nextTicketId = 1001;

	public static void startApplication() {

		while (true) {

			IO.println("\n========== RAILWAY RESERVATION SYSTEM ==========");

			IO.println("1. Book Ticket");
			IO.println("2. View Passengers");
			IO.println("3. Search Ticket");
			IO.println("4. Cancel Ticket");
			IO.println("5. Total Booked Tickets");
			IO.println("6. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1 -> bookTicket();

			case 2 -> viewPassengers();

			case 3 -> searchTicket();

			case 4 -> cancelTicket();

			case 5 -> totalTickets();

			case 6 -> {
				IO.println("Thank You!");
				return;
			}

			default -> IO.println("Invalid Choice!");
			}
		}
	}

	private static void bookTicket() {

		IO.println("\n===== BOOK TICKET =====");

		String name = IO.readln("Passenger Name: ");

		int age = Integer.parseInt(IO.readln("Passenger Age: "));

		if (age <= 0) {
			IO.println("Invalid Age!");
			return;
		}

		String source = IO.readln("Source: ");

		String destination = IO.readln("Destination: ");

		if (source.equalsIgnoreCase(destination)) {
			IO.println("Source and Destination cannot be same!");
			return;
		}

		Passenger passenger = new Passenger(nextTicketId++, name, age, source, destination);

		passengers.add(passenger);

		IO.println("\nTicket Booked Successfully!");

		IO.println("-----------------------------");
		IO.println("Ticket ID   : " + passenger.getTicketId());
		IO.println("Passenger   : " + passenger.getName());
		IO.println("Age         : " + passenger.getAge());
		IO.println("Route       : " + passenger.getSource() + " -> " + passenger.getDestination());
		IO.println("-----------------------------");
	}

	private static void viewPassengers() {

		if (passengers.isEmpty()) {
			IO.println("No Passengers Found!");
			return;
		}

		IO.println("\n========== PASSENGER LIST ==========");

		for (Passenger passenger : passengers) {
			IO.println(passenger);
		}
	}

	private static void searchTicket() {

		int ticketId = Integer.parseInt(IO.readln("Enter Ticket ID: "));

		for (Passenger passenger : passengers) {

			if (passenger.getTicketId() == ticketId) {

				IO.println("\nTicket Found!");
				IO.println(passenger);

				return;
			}
		}

		IO.println("Ticket Not Found!");
	}

	private static void cancelTicket() {

		int ticketId = Integer.parseInt(IO.readln("Enter Ticket ID: "));

		boolean removed = passengers.removeIf(passenger -> passenger.getTicketId() == ticketId);

		if (removed) {
			IO.println("Ticket Cancelled Successfully!");
		} else {
			IO.println("Ticket Not Found!");
		}
	}

	private static void totalTickets() {

		IO.println("\nTotal Booked Tickets: " + passengers.size());
	}
}