package com.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {

	static List<Employee> employees = new ArrayList<>();

	public static void addEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));
		String name = IO.readln("Enter Employee Name: ");
		String department = IO.readln("Enter Department: ");
		double salary = Double.parseDouble(IO.readln("Enter Salary: "));

		employees.add(new Employee(id, name, department, salary));

		IO.println("Employee Added Successfully!");
	}

	public static void viewEmployees() {

		if (employees.isEmpty()) {

			IO.println("No Employees Found!");
			return;
		}

		employees.forEach(IO::println);
	}

	public static void searchById() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		for (Employee emp : employees) {

			if (emp.getId() == id) {

				IO.println(emp);
				return;
			}
		}

		IO.println("Employee Not Found!");
	}

	public static void searchByDepartment() {

		String department = IO.readln("Enter Department: ");

		boolean found = false;

		for (Employee emp : employees) {

			if (emp.getDepartment().equalsIgnoreCase(department)) {

				IO.println(emp);
				found = true;
			}
		}

		if (!found) {

			IO.println("No Employees Found!");
		}
	}

	public static void updateEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		for (Employee emp : employees) {

			if (emp.getId() == id) {

				emp.setName(IO.readln("Enter New Name: "));
				emp.setDepartment(IO.readln("Enter New Department: "));
				emp.setSalary(Double.parseDouble(IO.readln("Enter New Salary: ")));

				IO.println("Employee Updated Successfully!");
				return;
			}
		}

		IO.println("Employee Not Found!");
	}

	public static void deleteEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		boolean removed = employees.removeIf(emp -> emp.getId() == id);

		if (removed) {

			IO.println("Employee Deleted Successfully!");

		} else {

			IO.println("Employee Not Found!");
		}
	}

	public static void sortBySalary() {

		employees.sort(Comparator.comparingDouble(Employee::getSalary));

		IO.println("Employees Sorted By Salary");
	}

	public static void highestSalaryEmployee() {

		if (employees.isEmpty()) {

			IO.println("No Employees Found!");
			return;
		}

		Employee emp = Collections.max(employees, Comparator.comparingDouble(Employee::getSalary));

		IO.println("Highest Salary Employee:");
		IO.println(emp);
	}

	public static void averageSalary() {

		if (employees.isEmpty()) {

			IO.println("No Employees Found!");
			return;
		}

		double avg = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);

		IO.println("Average Salary : " + avg);
	}
}