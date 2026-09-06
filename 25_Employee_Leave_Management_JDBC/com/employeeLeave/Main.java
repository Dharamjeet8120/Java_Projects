package com.employeeLeave;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		LeaveService service = new LeaveService();

		while (true) {

			System.out.println();
			System.out.println("==========================================");
			System.out.println("       EMPLOYEE LEAVE MANAGEMENT SYSTEM");
			System.out.println("==========================================");

			System.out.println("1.  Apply Leave");
			System.out.println("2.  View All Leave Requests");
			System.out.println("3.  Search Leave by ID");
			System.out.println("4.  Update Leave Request");
			System.out.println("5.  Approve Leave");
			System.out.println("6.  Reject Leave");
			System.out.println("7.  Delete Leave");
			System.out.println("8.  View Pending Leaves");
			System.out.println("9.  View Approved Leaves");
			System.out.println("10. View Rejected Leaves");
			System.out.println("11. Total Leave Requests");
			System.out.println("12. Total Pending Leaves");
			System.out.println("13. Total Approved Leaves");
			System.out.println("14. Total Rejected Leaves");
			System.out.println("15. Leave Dashboard");
			System.out.println("16. Exit");

			System.out.println("==========================================");

			String choice = IO.readln("Enter your choice: ");

			switch (choice) {

			case "1":

				String employee = IO.readln("Enter Employee Name: ");

				int days = Integer.parseInt(IO.readln("Enter Leave Days: "));

				if (days <= 0) {
					IO.println("Leave days must be greater than 0.");
					break;
				}

				EmployeeLeave leave = new EmployeeLeave(employee, days, "Pending");

				if (service.applyLeave(leave)) {

					IO.println("Leave applied successfully.");

				} else {

					IO.println("Failed to apply leave.");
				}

				break;

			case "2":

				List<EmployeeLeave> allLeaves = service.getAllLeaves();

				if (allLeaves.isEmpty()) {

					IO.println("No leave requests found.");

				} else {

					System.out.println();
					System.out.println("========== ALL LEAVE REQUESTS ==========");

					for (EmployeeLeave l : allLeaves) {
						System.out.println(l);
					}
				}

				break;

			case "3":

				int searchId = Integer.parseInt(IO.readln("Enter Leave ID: "));

				EmployeeLeave foundLeave = service.searchLeave(searchId);

				if (foundLeave != null) {

					System.out.println();
					System.out.println("========== LEAVE FOUND ==========");

					System.out.println(foundLeave);

				} else {

					IO.println("Leave request not found.");
				}

				break;

			case "4":

				int updateId = Integer.parseInt(IO.readln("Enter Leave ID: "));

				EmployeeLeave existingLeave = service.searchLeave(updateId);

				if (existingLeave == null) {

					IO.println("Leave request not found.");

					break;
				}

				if (!existingLeave.getStatus().equalsIgnoreCase("Pending")) {

					IO.println("Only pending leave can be updated.");

					break;
				}

				String newEmployee = IO.readln("Enter New Employee Name: ");

				int newDays = Integer.parseInt(IO.readln("Enter New Leave Days: "));

				if (newDays <= 0) {

					IO.println("Leave days must be greater than 0.");

					break;
				}

				EmployeeLeave updatedLeave = new EmployeeLeave(updateId, newEmployee, newDays,
						existingLeave.getStatus());

				if (service.updateLeave(updatedLeave)) {

					IO.println("Leave updated successfully.");

				} else {

					IO.println("Failed to update leave.");
				}

				break;

			case "5":

				int approveId = Integer.parseInt(IO.readln("Enter Leave ID: "));

				if (service.approveLeave(approveId)) {

					IO.println("Leave approved successfully.");

				} else {

					IO.println("Leave not found or already processed.");
				}

				break;

			case "6":

				int rejectId = Integer.parseInt(IO.readln("Enter Leave ID: "));

				if (service.rejectLeave(rejectId)) {

					IO.println("Leave rejected successfully.");

				} else {

					IO.println("Leave not found or already processed.");
				}

				break;

			case "7":

				int deleteId = Integer.parseInt(IO.readln("Enter Leave ID: "));

				if (service.deleteLeave(deleteId)) {

					IO.println("Leave deleted successfully.");

				} else {

					IO.println("Leave request not found.");
				}

				break;

			case "8":

				List<EmployeeLeave> pendingLeaves = service.getPendingLeaves();

				if (pendingLeaves.isEmpty()) {

					IO.println("No pending leaves found.");

				} else {

					System.out.println();
					System.out.println("========== PENDING LEAVES ==========");

					for (EmployeeLeave l : pendingLeaves) {
						System.out.println(l);
					}
				}

				break;

			case "9":

				List<EmployeeLeave> approvedLeaves = service.getApprovedLeaves();

				if (approvedLeaves.isEmpty()) {

					IO.println("No approved leaves found.");

				} else {

					System.out.println();
					System.out.println("========== APPROVED LEAVES ==========");

					for (EmployeeLeave l : approvedLeaves) {
						System.out.println(l);
					}
				}

				break;

			case "10":

				List<EmployeeLeave> rejectedLeaves = service.getRejectedLeaves();

				if (rejectedLeaves.isEmpty()) {

					IO.println("No rejected leaves found.");

				} else {

					System.out.println();
					System.out.println("========== REJECTED LEAVES ==========");

					for (EmployeeLeave l : rejectedLeaves) {
						System.out.println(l);
					}
				}

				break;

			case "11":

				IO.println("Total Leave Requests : " + service.getTotalLeaves());

				break;

			case "12":

				IO.println("Total Pending Leaves : " + service.getPendingCount());

				break;

			case "13":

				IO.println("Total Approved Leaves : " + service.getApprovedCount());

				break;

			case "14":

				IO.println("Total Rejected Leaves : " + service.getRejectedCount());

				break;

			case "15":

				service.showDashboard();

				break;

			case "16":

				IO.println("Thank you for using Employee Leave Management System.");

				return;

			default:

				IO.println("Invalid choice. Please enter 1 to 16.");
			}
		}
	}
}