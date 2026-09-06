package com.java;

public class StudentMain {

	public static void main(String[] args) {

		while (true) {

			IO.println("\n===== Student Management System =====");
			IO.println("1. Add Student");
			IO.println("2. View Students");
			IO.println("3. Search Student");
			IO.println("4. Update Student");
			IO.println("5. Delete Student");
			IO.println("6. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				StudentService.addStudent();
				break;

			case 2:
				StudentService.viewStudents();
				break;

			case 3:
				StudentService.searchStudent();
				break;

			case 4:
				StudentService.updateStudent();
				break;

			case 5:
				StudentService.deleteStudent();
				break;

			case 6:
				IO.println("Thank You...");
				System.exit(0);

			default:
				IO.println("Invalid Choice!");
			}
		}
	}
}