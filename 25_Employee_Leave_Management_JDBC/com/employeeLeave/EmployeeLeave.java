package com.employeeLeave;

public class EmployeeLeave {

	private int id;
	private String employee;
	private int days;
	private String status;

	public EmployeeLeave() {
	}

	public EmployeeLeave(int id, String employee, int days, String status) {

		this.id = id;
		this.employee = employee;
		this.days = days;
		this.status = status;
	}

	public EmployeeLeave(String employee, int days, String status) {

		this.employee = employee;
		this.days = days;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {

        return "ID : " + id
                + " | Employee : " + employee
                + " | Days : " + days
                + " | Status : " + status;
    }
}
