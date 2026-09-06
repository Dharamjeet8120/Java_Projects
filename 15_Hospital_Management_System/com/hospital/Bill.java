package com.hospital;

public class Bill {

	private int patientId;
	private String patientName;
	private double amount;

	public Bill(int patientId, String patientName, double amount) {

		this.patientId = patientId;
		this.patientName = patientName;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {

		return patientId + " | " + patientName + " | Rs." + amount;
	}
}