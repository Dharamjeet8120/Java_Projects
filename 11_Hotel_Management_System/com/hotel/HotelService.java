package com.hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelService {

	static List<Room> rooms = new ArrayList<>();
	static List<Booking> history = new ArrayList<>();

	static double totalRevenue = 0;

	static {

		rooms.add(new Room(1, "Standard", 1000));
		rooms.add(new Room(2, "Standard", 1000));
		rooms.add(new Room(3, "Standard", 1000));

		rooms.add(new Room(4, "Deluxe", 2500));
		rooms.add(new Room(5, "Deluxe", 2500));
		rooms.add(new Room(6, "Deluxe", 2500));

		rooms.add(new Room(7, "Suite", 5000));
		rooms.add(new Room(8, "Suite", 5000));
		rooms.add(new Room(9, "Suite", 5000));
		rooms.add(new Room(10, "Suite", 5000));
	}

	public static void startApplication() {

		while (true) {

			IO.println("\n===== HOTEL MANAGEMENT =====");
			IO.println("1. View All Rooms");
			IO.println("2. View Available Rooms");
			IO.println("3. Book Room");
			IO.println("4. Checkout");
			IO.println("5. Search Guest");
			IO.println("6. Booking History");
			IO.println("7. Total Revenue");
			IO.println("8. Admin Report");
			IO.println("9. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				viewAllRooms();
				break;

			case 2:
				viewAvailableRooms();
				break;

			case 3:
				bookRoom();
				break;

			case 4:
				checkoutRoom();
				break;

			case 5:
				searchGuest();
				break;

			case 6:
				bookingHistory();
				break;

			case 7:
				showRevenue();
				break;

			case 8:
				adminReport();
				break;

			case 9:
				IO.println("Thank You!");
				return;

			default:
				IO.println("Invalid Choice!");
			}
		}
	}

	private static void viewAllRooms() {

		for (Room room : rooms) {

			IO.println(room);
		}
	}

	private static void viewAvailableRooms() {

		for (Room room : rooms) {

			if (!room.isBooked()) {

				IO.println(room);
			}
		}
	}

	private static void bookRoom() {

		int roomNo = Integer.parseInt(IO.readln("Room Number: "));

		Room room = rooms.get(roomNo - 1);

		if (room.isBooked()) {

			IO.println("Room Already Booked!");
			return;
		}

		String guest = IO.readln("Guest Name: ");

		int days = Integer.parseInt(IO.readln("Days: "));

		room.bookRoom(guest, days);

		double bill = room.getPrice() * days;

		totalRevenue += bill;

		history.add(new Booking(guest, room.getRoomNo(), room.getCategory(), days, bill));

		IO.println("Room Booked Successfully!");
		IO.println("Total Bill : " + bill);
	}

	private static void checkoutRoom() {

		int roomNo = Integer.parseInt(IO.readln("Room Number: "));

		Room room = rooms.get(roomNo - 1);

		if (!room.isBooked()) {

			IO.println("Room Already Available!");
			return;
		}

		room.checkout();

		IO.println("Checkout Successful!");
	}

	private static void searchGuest() {

		String guest = IO.readln("Guest Name: ");

		boolean found = false;

		for (Room room : rooms) {

			if (room.getGuestName().equalsIgnoreCase(guest)) {

				IO.println("Room No : " + room.getRoomNo());

				found = true;
			}
		}

		if (!found) {

			IO.println("Guest Not Found!");
		}
	}

	private static void bookingHistory() {

		if (history.isEmpty()) {

			IO.println("No Booking History!");
			return;
		}

		for (Booking booking : history) {

			IO.println(booking);
		}
	}

	private static void showRevenue() {

		IO.println("Total Revenue : " + totalRevenue);
	}

	private static void adminReport() {

		int booked = 0;

		for (Room room : rooms) {

			if (room.isBooked()) {

				booked++;
			}
		}

		IO.println("\n===== ADMIN REPORT =====");
		IO.println("Total Rooms : " + rooms.size());

		IO.println("Booked Rooms : " + booked);

		IO.println("Available Rooms : " + (rooms.size() - booked));

		IO.println("Revenue : " + totalRevenue);
	}
}
