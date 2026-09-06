package com.hospital;

public class Appointment {

	private int patientId;
	private String patientName;
	private String doctorName;
	private String date;

	public Appointment(int patientId, String patientName, String doctorName, String date) {

		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.date = date;
	}

	@Override
	public String toString() {

		return patientId + " | " + patientName + " | " + doctorName + " | " + date;
	}
}