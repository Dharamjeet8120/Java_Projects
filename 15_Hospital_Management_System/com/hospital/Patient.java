package com.hospital;

public class Patient {

	private int id;
	private String name;
	private int age;
	private String disease;

	public Patient(int id, String name, int age, String disease) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.disease = disease;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisease() {
		return disease;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	@Override
	public String toString() {

		return id + " | " + name + " | " + age + " | " + disease;
	}
}