package com.employeePayroll;

import java.util.ArrayList;
import java.util.List;

public class PayrollService {

	private static List<Employee> employees = new ArrayList<>();

	public static void startApplication() {

		while (true) {

			IO.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
			IO.println("1. Add Employee");
			IO.println("2. View Employees");
			IO.println("3. Generate Payroll");
			IO.println("4. Search Employee");
			IO.println("5. Update Employee");
			IO.println("6. Delete Employee");
			IO.println("7. Payroll Summary");
			IO.println("8. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1 -> addEmployee();

			case 2 -> viewEmployees();

			case 3 -> generatePayroll();

			case 4 -> searchEmployee();

			case 5 -> updateEmployee();

			case 6 -> deleteEmployee();

			case 7 -> payrollSummary();

			case 8 -> {
				IO.println("Thank You!");
				return;
			}

			default -> IO.println("Invalid Choice!");
			}
		}
	}

	private static void addEmployee() {

		int id = Integer.parseInt(IO.readln("Employee ID: "));

		String name = IO.readln("Employee Name: ");

		double basicSalary = Double.parseDouble(IO.readln("Basic Salary: "));

		if (basicSalary <= 0) {
			IO.println("Salary must be greater than zero!");
			return;
		}

		employees.add(new Employee(id, name, basicSalary));

		IO.println("Employee Added Successfully!");
	}

	private static void viewEmployees() {

		if (employees.isEmpty()) {
			IO.println("No Employees Found!");
			return;
		}

		IO.println("\n===== EMPLOYEE LIST =====");

		for (Employee employee : employees) {
			IO.println(employee);
		}
	}

	private static void generatePayroll() {

		if (employees.isEmpty()) {
			IO.println("No Employees Found!");
			return;
		}

		IO.println("\n========== PAYROLL ==========");

		for (Employee employee : employees) {

			IO.println("--------------------------------");

			IO.println("Employee ID   : " + employee.getId());
			IO.println("Employee Name : " + employee.getName());
			IO.println("Basic Salary  : Rs." + employee.getBasicSalary());
			IO.println("HRA (20%)     : Rs." + employee.getHra());
			IO.println("DA (10%)      : Rs." + employee.getDa());
			IO.println("Gross Salary  : Rs." + employee.getGrossSalary());
			IO.println("PF (12%)      : Rs." + employee.getPf());
			IO.println("Net Salary    : Rs." + employee.getNetSalary());
		}

		IO.println("--------------------------------");
	}

	private static void searchEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		for (Employee employee : employees) {

			if (employee.getId() == id) {

				IO.println("\nEmployee Found!");
				IO.println(employee);
				return;
			}
		}

		IO.println("Employee Not Found!");
	}

	private static void updateEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		for (Employee employee : employees) {

			if (employee.getId() == id) {

				String name = IO.readln("New Name: ");

				double salary = Double.parseDouble(IO.readln("New Basic Salary: "));

				if (salary <= 0) {
					IO.println("Invalid Salary!");
					return;
				}

				employee.setName(name);
				employee.setBasicSalary(salary);

				IO.println("Employee Updated Successfully!");
				return;
			}
		}

		IO.println("Employee Not Found!");
	}

	private static void deleteEmployee() {

		int id = Integer.parseInt(IO.readln("Enter Employee ID: "));

		boolean removed = employees.removeIf(employee -> employee.getId() == id);

		if (removed) {
			IO.println("Employee Deleted Successfully!");
		} else {
			IO.println("Employee Not Found!");
		}
	}

	private static void payrollSummary() {

		if (employees.isEmpty()) {
			IO.println("No Payroll Data Available!");
			return;
		}

		double totalBasic = 0;
		double totalGross = 0;
		double totalPF = 0;
		double totalNet = 0;

		for (Employee employee : employees) {

			totalBasic += employee.getBasicSalary();
			totalGross += employee.getGrossSalary();
			totalPF += employee.getPf();
			totalNet += employee.getNetSalary();
		}

		IO.println("\n===== PAYROLL SUMMARY =====");

		IO.println("Total Employees : " + employees.size());
		IO.println("Total Basic     : Rs." + totalBasic);
		IO.println("Total Gross     : Rs." + totalGross);
		IO.println("Total PF        : Rs." + totalPF);
		IO.println("Total Net Salary: Rs." + totalNet);
	}
}