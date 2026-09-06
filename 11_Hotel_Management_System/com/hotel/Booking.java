package com.hotel;

public class Booking {

	private String guestName;
	private int roomNo;
	private String category;
	private int days;
	private double bill;

	public Booking(String guestName, int roomNo, String category, int days, double bill) {

		this.guestName = guestName;
		this.roomNo = roomNo;
		this.category = category;
		this.days = days;
		this.bill = bill;
	}

	@Override
	public String toString() {

		return "Guest: " + guestName + " | Room: " + roomNo + " | Category: " + category + " | Days: " + days
				+ " | Bill: " + bill;
	}
}
