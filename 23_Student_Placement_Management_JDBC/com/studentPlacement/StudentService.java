package com.studentPlacement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

	// 1. Add Student
	public boolean addStudent(Student student) {

		String sql = "INSERT INTO students(id, name, skill, placed) VALUES (?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSkill());
			ps.setBoolean(4, student.isPlaced());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Error adding student : " + e.getMessage());
			return false;
		}
	}

	// 2. View All Students
	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<>();

		String sql = "SELECT * FROM students";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
						rs.getBoolean("placed")));
			}

		} catch (Exception e) {
			System.out.println("Error fetching students : " + e.getMessage());
		}

		return students;
	}

	// 3. Search Student
	public Student searchStudent(int id) {

		String sql = "SELECT * FROM students WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
							rs.getBoolean("placed"));
				}
			}

		} catch (Exception e) {
			System.out.println("Error searching student : " + e.getMessage());
		}

		return null;
	}

	// 4. Update Student
	public boolean updateStudent(Student student) {

		String sql = "UPDATE students SET name = ?, skill = ? WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, student.getName());
			ps.setString(2, student.getSkill());
			ps.setInt(3, student.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Error updating student : " + e.getMessage());
			return false;
		}
	}

	// 5. Delete Student
	public boolean deleteStudent(int id) {

		String sql = "DELETE FROM students WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Error deleting student : " + e.getMessage());
			return false;
		}
	}

	// 6. Mark Student Placed
	public boolean markPlaced(int id) {

		String sql = "UPDATE students SET placed = true WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Error marking student placed : " + e.getMessage());
			return false;
		}
	}

	// 7. View Placed Students
	public List<Student> getPlacedStudents() {
		return getStudentsByStatus(true);
	}

	// 8. View Unplaced Students
	public List<Student> getUnplacedStudents() {
		return getStudentsByStatus(false);
	}

	private List<Student> getStudentsByStatus(boolean placed) {

		List<Student> students = new ArrayList<>();

		String sql = "SELECT * FROM students WHERE placed = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setBoolean(1, placed);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
							rs.getBoolean("placed")));
				}
			}

		} catch (Exception e) {
			System.out.println("Error fetching students : " + e.getMessage());
		}

		return students;
	}

	// 9. Search By Skill
	public List<Student> searchBySkill(String skill) {

		List<Student> students = new ArrayList<>();

		String sql = "SELECT * FROM students WHERE skill LIKE ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, "%" + skill + "%");

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
							rs.getBoolean("placed")));
				}
			}

		} catch (Exception e) {
			System.out.println("Error searching by skill : " + e.getMessage());
		}

		return students;
	}

	// 10. Placement Count
	public int getPlacementCount() {

		String sql = "SELECT COUNT(*) FROM students WHERE placed = true";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Error getting placement count : " + e.getMessage());
		}

		return 0;
	}

	// 11. Placement Percentage
	public double getPlacementPercentage() {

		int totalStudents = getTotalStudents();

		if (totalStudents == 0) {
			return 0.0;
		}

		int placedStudents = getPlacementCount();

		return (placedStudents * 100.0) / totalStudents;
	}

	// 12. Total Students
	public int getTotalStudents() {

		String sql = "SELECT COUNT(*) FROM students";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Error getting total students : " + e.getMessage());
		}

		return 0;
	}

	// 13. Highest Placement Skill Count
	public String getHighestPlacementSkill() {

		String sql = """
				SELECT skill, COUNT(*) AS total
				FROM students
				WHERE placed = true
				GROUP BY skill
				ORDER BY total DESC
				LIMIT 1
				""";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {

				String skill = rs.getString("skill");
				int count = rs.getInt("total");

				return skill + " (" + count + ")";
			}

		} catch (Exception e) {
			System.out.println("Error getting highest placement skill : " + e.getMessage());
		}

		return "No placement data";
	}

	// 14. Placement Dashboard
	public void showPlacementDashboard() {

		int totalStudents = getTotalStudents();
		int placedStudents = getPlacementCount();
		int unplacedStudents = totalStudents - placedStudents;
		double placementPercentage = getPlacementPercentage();
		String highestPlacementSkill = getHighestPlacementSkill();

		System.out.println();
		System.out.println("==========================================");
		System.out.println("          PLACEMENT DASHBOARD");
		System.out.println("==========================================");

		System.out.println("Total Students       : " + totalStudents);
		System.out.println("Placed Students      : " + placedStudents);
		System.out.println("Unplaced Students    : " + unplacedStudents);

		System.out.printf("Placement Percentage : %.2f%%%n", placementPercentage);

		System.out.println("Top Placement Skill : " + highestPlacementSkill);

		System.out.println("==========================================");
		System.out.println();
	}
}