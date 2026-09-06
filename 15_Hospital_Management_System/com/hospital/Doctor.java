package com.hospital;

public class Doctor {

	private int doctorId;
	private String doctorName;
	private String specialization;

	public Doctor(int doctorId, String doctorName, String specialization) {

		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	@Override
	public String toString() {

		return doctorId + " | " + doctorName + " | " + specialization;
	}
}