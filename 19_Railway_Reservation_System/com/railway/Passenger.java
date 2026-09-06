package com.railway;

public class Passenger {

	private int ticketId;
	private String name;
	private int age;
	private String source;
	private String destination;

	public Passenger(int ticketId, String name, int age, String source, String destination) {

		this.ticketId = ticketId;
		this.name = name;
		this.age = age;
		this.source = source;
		this.destination = destination;
	}

	public int getTicketId() {
		return ticketId;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	@Override
	public String toString() {

		return ticketId + " | " + name + " | Age: " + age + " | " + source + " -> " + destination;
	}
}