package com.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

	// 1. Add Book
	public boolean addBook(Book book) {

		String sql = """
				INSERT INTO books
				(title, author, issued)
				VALUES (?, ?, ?)
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setBoolean(3, false);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error adding book : " + e.getMessage());

			return false;
		}
	}

	// 2. View All Books
	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM books ORDER BY id";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getBoolean("issued")));
			}

		} catch (Exception e) {

			System.out.println("Error fetching books : " + e.getMessage());
		}

		return books;
	}

	// 3. Search Book by ID
	public Book searchBookById(int id) {

		String sql = "SELECT * FROM books WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
							rs.getBoolean("issued"));
				}
			}

		} catch (Exception e) {

			System.out.println("Error searching book : " + e.getMessage());
		}

		return null;
	}

	// 4. Search Book by Title
	public List<Book> searchBookByTitle(String title) {

		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM books WHERE title LIKE ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, "%" + title + "%");

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
							rs.getBoolean("issued")));
				}
			}

		} catch (Exception e) {

			System.out.println("Error searching books : " + e.getMessage());
		}

		return books;
	}

	// 5. Update Book
	public boolean updateBook(Book book) {

		String sql = """
				UPDATE books
				SET title = ?, author = ?
				WHERE id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error updating book : " + e.getMessage());

			return false;
		}
	}

	// 6. Delete Book
	public boolean deleteBook(int id) {

		String sql = "DELETE FROM books WHERE id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error deleting book : " + e.getMessage());

			return false;
		}
	}

	// 7. Issue Book
	public boolean issueBook(int id) {

		String sql = """
				UPDATE books
				SET issued = true
				WHERE id = ? AND issued = false
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error issuing book : " + e.getMessage());

			return false;
		}
	}

	// 8. Return Book
	public boolean returnBook(int id) {

		String sql = """
				UPDATE books
				SET issued = false
				WHERE id = ? AND issued = true
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("Error returning book : " + e.getMessage());

			return false;
		}
	}

	// 9. View Available Books
	public List<Book> getAvailableBooks() {

		return getBooksByStatus(false);
	}

	// 10. View Issued Books
	public List<Book> getIssuedBooks() {

		return getBooksByStatus(true);
	}

	// Common status method
	private List<Book> getBooksByStatus(boolean issued) {

		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM books " + "WHERE issued = ? ORDER BY id";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setBoolean(1, issued);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
							rs.getBoolean("issued")));
				}
			}

		} catch (Exception e) {

			System.out.println("Error fetching books : " + e.getMessage());
		}

		return books;
	}

	// 11. Total Books
	public int getTotalBooks() {

		return getCount("SELECT COUNT(*) FROM books");
	}

	// 12. Total Available Books
	public int getAvailableCount() {

		return getCount("SELECT COUNT(*) FROM books " + "WHERE issued = false");
	}

	// 13. Total Issued Books
	public int getIssuedCount() {

		return getCount("SELECT COUNT(*) FROM books " + "WHERE issued = true");
	}

	// Common Count Method
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

	// 14. Library Dashboard
	public void showDashboard() {

		int total = getTotalBooks();
		int available = getAvailableCount();
		int issued = getIssuedCount();

		System.out.println();

		System.out.println("========== LIBRARY DASHBOARD ==========");

		System.out.println("Total Books     : " + total);

		System.out.println("Available Books : " + available);

		System.out.println("Issued Books    : " + issued);

		System.out.println("========================================");
	}
}