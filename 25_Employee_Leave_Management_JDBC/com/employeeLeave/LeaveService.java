package com.employeeLeave;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaveService {

	// 1. Apply Leave
	public boolean applyLeave(EmployeeLeave leave) {

		String sql = """
				INSERT INTO leaves
				(employee, days, status)
				VALUES (?, ?, ?)
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, leave.getEmployee());
			ps.setInt(2, leave.getDays());
			ps.setString(3, "Pending");

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error applying leave : " + e.getMessage());

			return false;
		}
	}

	// 2. View All Leave Requests
	public List<EmployeeLeave> getAllLeaves() {

		List<EmployeeLeave> leaves = new ArrayList<>();

		String sql = "SELECT * FROM leaves ORDER BY id";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				EmployeeLeave leave = new EmployeeLeave(rs.getInt("id"), rs.getString("employee"), rs.getInt("days"),
						rs.getString("status"));

				leaves.add(leave);
			}

		} catch (Exception e) {

			System.out.println("Error fetching leaves : " + e.getMessage());
		}

		return leaves;
	}

	// 3. Search Leave by ID
	public EmployeeLeave searchLeave(int id) {

		String sql = "SELECT * FROM leaves WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					return new EmployeeLeave(rs.getInt("id"), rs.getString("employee"), rs.getInt("days"),
							rs.getString("status"));
				}
			}

		} catch (Exception e) {

			System.out.println("Error searching leave : " + e.getMessage());
		}

		return null;
	}

	// 4. Update Leave Request
	public boolean updateLeave(EmployeeLeave leave) {

		String sql = """
				UPDATE leaves
				SET employee = ?, days = ?
				WHERE id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, leave.getEmployee());
			ps.setInt(2, leave.getDays());
			ps.setInt(3, leave.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error updating leave : " + e.getMessage());

			return false;
		}
	}

	// 5. Approve Leave
	public boolean approveLeave(int id) {

		String sql = "UPDATE leaves SET status = 'Approved' " + "WHERE id = ? AND status = 'Pending'";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error approving leave : " + e.getMessage());

			return false;
		}
	}

	// 6. Reject Leave
	public boolean rejectLeave(int id) {

		String sql = "UPDATE leaves SET status = 'Rejected' " + "WHERE id = ? AND status = 'Pending'";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error rejecting leave : " + e.getMessage());

			return false;
		}
	}

	// 7. Delete Leave
	public boolean deleteLeave(int id) {

		String sql = "DELETE FROM leaves WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error deleting leave : " + e.getMessage());

			return false;
		}
	}

	// 8. View Pending Leaves
	public List<EmployeeLeave> getPendingLeaves() {

		return getLeavesByStatus("Pending");
	}

	// 9. View Approved Leaves
	public List<EmployeeLeave> getApprovedLeaves() {

		return getLeavesByStatus("Approved");
	}

	// 10. View Rejected Leaves
	public List<EmployeeLeave> getRejectedLeaves() {

		return getLeavesByStatus("Rejected");
	}

	// Common method for status-based search
	private List<EmployeeLeave> getLeavesByStatus(String status) {

		List<EmployeeLeave> leaves = new ArrayList<>();

		String sql = "SELECT * FROM leaves " + "WHERE status = ? ORDER BY id";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					leaves.add(new EmployeeLeave(rs.getInt("id"), rs.getString("employee"), rs.getInt("days"),
							rs.getString("status")));
				}
			}

		} catch (Exception e) {

			System.out.println("Error fetching " + status + " leaves : " + e.getMessage());
		}

		return leaves;
	}

	// 11. Total Leave Requests
	public int getTotalLeaves() {

		return getCount("SELECT COUNT(*) FROM leaves");
	}

	// 12. Total Pending Leaves
	public int getPendingCount() {

		return getCount("SELECT COUNT(*) FROM leaves " + "WHERE status = 'Pending'");
	}

	// 13. Total Approved Leaves
	public int getApprovedCount() {

		return getCount("SELECT COUNT(*) FROM leaves " + "WHERE status = 'Approved'");
	}

	// 14. Total Rejected Leaves
	public int getRejectedCount() {

		return getCount("SELECT COUNT(*) FROM leaves " + "WHERE status = 'Rejected'");
	}

	// Common count method
	private int getCount(String sql) {

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {

			System.out.println("Error getting count : " + e.getMessage());
		}

		return 0;
	}

	// 15. Leave Dashboard
	public void showDashboard() {

		int total = getTotalLeaves();
		int pending = getPendingCount();
		int approved = getApprovedCount();
		int rejected = getRejectedCount();

		System.out.println();
		System.out.println("========== LEAVE DASHBOARD ==========");

		System.out.println("Total Leave Requests : " + total);

		System.out.println("Pending Leaves       : " + pending);

		System.out.println("Approved Leaves      : " + approved);

		System.out.println("Rejected Leaves      : " + rejected);

		System.out.println("=====================================");
	}
}