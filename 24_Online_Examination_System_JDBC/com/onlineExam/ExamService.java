package com.onlineExam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamService {

	// 1. Add Question
	public boolean addQuestion(Question question) {

		String sql = """
				INSERT INTO questions
				(id, q, a, b, c, ans)
				VALUES (?, ?, ?, ?, ?, ?)
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, question.getId());
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getOptionA());
			ps.setString(4, question.getOptionB());
			ps.setString(5, question.getOptionC());
			ps.setString(6, String.valueOf(question.getAnswer()));

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error adding question : " + e.getMessage());

			return false;
		}
	}

	// 2. View All Questions
	public List<Question> getAllQuestions() {

		List<Question> questions = new ArrayList<>();

		String sql = "SELECT * FROM questions";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				questions.add(new Question(rs.getInt("id"), rs.getString("q"), rs.getString("a"), rs.getString("b"),
						rs.getString("c"), rs.getString("ans").charAt(0)));
			}

		} catch (Exception e) {

			System.out.println("Error fetching questions : " + e.getMessage());
		}

		return questions;
	}

	// 3. Search Question
	public Question searchQuestion(int id) {

		String sql = "SELECT * FROM questions WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					return new Question(rs.getInt("id"), rs.getString("q"), rs.getString("a"), rs.getString("b"),
							rs.getString("c"), rs.getString("ans").charAt(0));
				}
			}

		} catch (Exception e) {

			System.out.println("Error searching question : " + e.getMessage());
		}

		return null;
	}

	// 4. Update Question
	public boolean updateQuestion(Question question) {

		String sql = """
				UPDATE questions
				SET q = ?, a = ?, b = ?, c = ?, ans = ?
				WHERE id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, question.getQuestion());
			ps.setString(2, question.getOptionA());
			ps.setString(3, question.getOptionB());
			ps.setString(4, question.getOptionC());
			ps.setString(5, String.valueOf(question.getAnswer()));
			ps.setInt(6, question.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error updating question : " + e.getMessage());

			return false;
		}
	}

	// 5. Delete Question
	public boolean deleteQuestion(int id) {

		String sql = "DELETE FROM questions WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error deleting question : " + e.getMessage());

			return false;
		}
	}

	// 6. Get Total Questions
	public int getTotalQuestions() {

		String sql = "SELECT COUNT(*) FROM questions";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {

			System.out.println("Error getting total questions : " + e.getMessage());
		}

		return 0;
	}

	// 7. Check Answer
	public boolean checkAnswer(int questionId, char userAnswer) {

		String sql = "SELECT ans FROM questions WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, questionId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					char correctAnswer = rs.getString("ans").charAt(0);

					return Character.toUpperCase(userAnswer) == Character.toUpperCase(correctAnswer);
				}
			}

		} catch (Exception e) {

			System.out.println("Error checking answer : " + e.getMessage());
		}

		return false;
	}

	// 8. Get Exam Questions
	public List<Question> getExamQuestions() {

		return getAllQuestions();
	}
}