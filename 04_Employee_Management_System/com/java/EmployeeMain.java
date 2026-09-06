package com.java;

public class EmployeeMain {

	public static void main(String[] args) {

		while (true) {

			IO.println("\n===== Employee Management System =====");
			IO.println("1. Add Employee");
			IO.println("2. View Employees");
			IO.println("3. Search By ID");
			IO.println("4. Search By Department");
			IO.println("5. Update Employee");
			IO.println("6. Delete Employee");
			IO.println("7. Sort By Salary");
			IO.println("8. Highest Salary Employee");
			IO.println("9. Average Salary");
			IO.println("10. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				EmployeeService.addEmployee();
				break;

			case 2:
				EmployeeService.viewEmployees();
				break;

			case 3:
				EmployeeService.searchById();
				break;

			case 4:
				EmployeeService.searchByDepartment();
				break;

			case 5:
				EmployeeService.updateEmployee();
				break;

			case 6:
				EmployeeService.deleteEmployee();
				break;

			case 7:
				EmployeeService.sortBySalary();
				break;

			case 8:
				EmployeeService.highestSalaryEmployee();
				break;

			case 9:
				EmployeeService.averageSalary();
				break;

			case 10:
				IO.println("Thank You...");
				System.exit(0);

			default:
				IO.println("Invalid Choice!");
			}
		}
	}
}