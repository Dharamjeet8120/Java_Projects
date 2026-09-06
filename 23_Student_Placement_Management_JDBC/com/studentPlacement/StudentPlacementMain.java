package com.studentPlacement;

import java.util.List;

public class StudentPlacementMain {

	public static void main(String[] args) {

		StudentService service = new StudentService();

		while (true) {

			System.out.println();
			System.out.println("==========================================");
			System.out.println("     STUDENT PLACEMENT MANAGEMENT SYSTEM");
			System.out.println("==========================================");

			System.out.println("1.  Add Student");
			System.out.println("2.  View All Students");
			System.out.println("3.  Search Student");
			System.out.println("4.  Update Student");
			System.out.println("5.  Delete Student");
			System.out.println("6.  Mark Student Placed");
			System.out.println("7.  View Placed Students");
			System.out.println("8.  View Unplaced Students");
			System.out.println("9.  Search By Skill");
			System.out.println("10. Placement Count");
			System.out.println("11. Placement Percentage");
			System.out.println("12. Total Students");
			System.out.println("13. Highest Placement Skill Count");
			System.out.println("14. Placement Dashboard");
			System.out.println("15. Exit");

			System.out.println("==========================================");

			String choice = IO.readln("Enter your choice: ");

			switch (choice) {

			// 1. Add Student
			case "1":

				int id = Integer.parseInt(IO.readln("Enter Student ID: "));

				String name = IO.readln("Enter Student Name: ");

				String skill = IO.readln("Enter Student Skill: ");

				Student student = new Student(id, name, skill, false);

				if (service.addStudent(student)) {
					IO.println("Student added successfully.");
				} else {
					IO.println("Failed to add student.");
				}

				break;

			// 2. View All Students
			case "2":

				List<Student> students = service.getAllStudents();

				if (students.isEmpty()) {

					IO.println("No students found.");

				} else {

					IO.println();
					IO.println("========== ALL STUDENTS ==========");

					for (Student s : students) {
						IO.println(s.toString());
					}
				}

				break;

			// 3. Search Student
			case "3":

				int searchId = Integer.parseInt(IO.readln("Enter Student ID: "));

				Student foundStudent = service.searchStudent(searchId);

				if (foundStudent != null) {

					IO.println("Student Found:");
					IO.println(foundStudent.toString());

				} else {

					IO.println("Student not found.");
				}

				break;

			// 4. Update Student
			case "4":

				int updateId = Integer.parseInt(IO.readln("Enter Student ID: "));

				Student existingStudent = service.searchStudent(updateId);

				if (existingStudent == null) {

					IO.println("Student not found.");

				} else {

					String newName = IO.readln("Enter New Name: ");

					String newSkill = IO.readln("Enter New Skill: ");

					Student updatedStudent = new Student(updateId, newName, newSkill, existingStudent.isPlaced());

					if (service.updateStudent(updatedStudent)) {

						IO.println("Student updated successfully.");

					} else {

						IO.println("Failed to update student.");
					}
				}

				break;

			// 5. Delete Student
			case "5":

				int deleteId = Integer.parseInt(IO.readln("Enter Student ID: "));

				if (service.deleteStudent(deleteId)) {

					IO.println("Student deleted successfully.");

				} else {

					IO.println("Student not found or delete failed.");
				}

				break;

			// 6. Mark Student Placed
			case "6":

				int placedId = Integer.parseInt(IO.readln("Enter Student ID: "));

				if (service.markPlaced(placedId)) {

					IO.println("Student marked as placed successfully.");

				} else {

					IO.println("Student not found or operation failed.");
				}

				break;

			// 7. View Placed Students
			case "7":

				List<Student> placedStudents = service.getPlacedStudents();

				if (placedStudents.isEmpty()) {

					IO.println("No placed students found.");

				} else {

					IO.println();
					IO.println("========== PLACED STUDENTS ==========");

					for (Student s : placedStudents) {
						IO.println(s.toString());
					}
				}

				break;

			// 8. View Unplaced Students
			case "8":

				List<Student> unplacedStudents = service.getUnplacedStudents();

				if (unplacedStudents.isEmpty()) {

					IO.println("No unplaced students found.");

				} else {

					IO.println();
					IO.println("========== UNPLACED STUDENTS ==========");

					for (Student s : unplacedStudents) {
						IO.println(s.toString());
					}
				}

				break;

			// 9. Search By Skill
			case "9":

				String searchSkill = IO.readln("Enter Skill: ");

				List<Student> skillStudents = service.searchBySkill(searchSkill);

				if (skillStudents.isEmpty()) {

					IO.println("No students found with this skill.");

				} else {

					IO.println();
					IO.println("========== SEARCH RESULT ==========");

					for (Student s : skillStudents) {
						IO.println(s.toString());
					}
				}

				break;

			// 10. Placement Count
			case "10":

				int placementCount = service.getPlacementCount();

				IO.println("Total Placed Students : " + placementCount);

				break;

			// 11. Placement Percentage
			case "11":

				double percentage = service.getPlacementPercentage();

				IO.println(String.format("Placement Percentage : %.2f%%", percentage));

				break;

			// 12. Total Students
			case "12":

				int totalStudents = service.getTotalStudents();

				IO.println("Total Students : " + totalStudents);

				break;

			// 13. Highest Placement Skill Count
			case "13":

				String highestSkill = service.getHighestPlacementSkill();

				IO.println("Highest Placement Skill : " + highestSkill);

				break;

			// 14. Placement Dashboard
			case "14":

				service.showPlacementDashboard();

				break;

			// 15. Exit
			case "15":

				IO.println("Thank you for using Student Placement Management System.");

				return;

			default:

				IO.println("Invalid choice. Please enter 1 to 15.");
			}
		}
	}
}