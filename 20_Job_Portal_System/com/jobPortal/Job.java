package com.jobPortal;

public class Job {

	private int id;
	private String role;
	private String company;
	private String location;
	private double salary;

	public Job(int id, String role, String company, String location, double salary) {

		this.id = id;
		this.role = role;
		this.company = company;
		this.location = location;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public String getCompany() {
		return company;
	}

	public String getLocation() {
		return location;
	}

	public double getSalary() {
		return salary;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {

		return id + " | " + role + " | " + company + " | " + location + " | Rs." + salary;
	}
}