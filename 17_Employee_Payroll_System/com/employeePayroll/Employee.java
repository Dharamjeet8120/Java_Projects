package com.employeePayroll;

public class Employee {

	private int id;
	private String name;
	private double basicSalary;

	public Employee(int id, String name, double basicSalary) {
		this.id = id;
		this.name = name;
		this.basicSalary = basicSalary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getHra() {
		return basicSalary * 0.20;
	}

	public double getDa() {
		return basicSalary * 0.10;
	}

	public double getPf() {
		return basicSalary * 0.12;
	}

	public double getGrossSalary() {
		return basicSalary + getHra() + getDa();
	}

	public double getNetSalary() {
		return getGrossSalary() - getPf();
	}

	@Override
	public String toString() {
		return id + " | " + name + " | Basic: Rs." + basicSalary + " | Gross: Rs." + getGrossSalary() + " | PF: Rs."
				+ getPf() + " | Net: Rs." + getNetSalary();
	}
}