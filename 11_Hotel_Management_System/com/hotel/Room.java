package com.hotel;

public class Room {

	private int roomNo;
	private String category;
	private double price;
	private boolean booked;
	private String guestName;
	private int days;

	public Room(int roomNo, String category, double price) {

		this.roomNo = roomNo;
		this.category = category;
		this.price = price;
		this.booked = false;
		this.guestName = "";
		this.days = 0;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public boolean isBooked() {
		return booked;
	}

	public String getGuestName() {
		return guestName;
	}

	public int getDays() {
		return days;
	}

	public void bookRoom(String guestName, int days) {

		this.guestName = guestName;
		this.days = days;
		this.booked = true;
	}

	public void checkout() {

		this.booked = false;
		this.guestName = "";
		this.days = 0;
	}

	@Override
	public String toString() {

		return roomNo + " | " + category + " | " + price + " | " + (booked ? guestName : "Available");
	}
}
