package com.library;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		LibraryService service = new LibraryService();

		while (true) {

			System.out.println();

			System.out.println("==========================================");

			System.out.println("        LIBRARY MANAGEMENT SYSTEM");

			System.out.println("==========================================");

			System.out.println("1.  Add Book");
			System.out.println("2.  View All Books");
			System.out.println("3.  Search Book by ID");
			System.out.println("4.  Search Book by Title");
			System.out.println("5.  Update Book");
			System.out.println("6.  Delete Book");
			System.out.println("7.  Issue Book");
			System.out.println("8.  Return Book");
			System.out.println("9.  View Available Books");
			System.out.println("10. View Issued Books");
			System.out.println("11. Total Books");
			System.out.println("12. Total Available Books");
			System.out.println("13. Total Issued Books");
			System.out.println("14. Library Dashboard");
			System.out.println("15. Exit");

			System.out.println("==========================================");

			String choice = IO.readln("Enter your choice: ");

			switch (choice) {

			// 1. Add Book
			case "1":

				String title = IO.readln("Enter Book Title: ");

				String author = IO.readln("Enter Author Name: ");

				if (title.isBlank() || author.isBlank()) {

					IO.println("Title and Author are required.");

					break;
				}

				Book book = new Book(title, author, false);

				if (service.addBook(book)) {

					IO.println("Book added successfully.");

				} else {

					IO.println("Failed to add book.");
				}

				break;

			// 2. View All Books
			case "2":

				List<Book> books = service.getAllBooks();

				if (books.isEmpty()) {

					IO.println("No books found.");

				} else {

					System.out.println();

					System.out.println("========== ALL BOOKS ==========");

					for (Book b : books) {

						System.out.println(b);
					}
				}

				break;

			// 3. Search Book by ID
			case "3":

				int searchId = Integer.parseInt(IO.readln("Enter Book ID: "));

				Book foundBook = service.searchBookById(searchId);

				if (foundBook != null) {

					System.out.println();

					System.out.println("========== BOOK FOUND ==========");

					System.out.println(foundBook);

				} else {

					IO.println("Book not found.");
				}

				break;

			// 4. Search Book by Title
			case "4":

				String searchTitle = IO.readln("Enter Book Title: ");

				List<Book> searchedBooks = service.searchBookByTitle(searchTitle);

				if (searchedBooks.isEmpty()) {

					IO.println("No books found.");

				} else {

					System.out.println();

					System.out.println("========== SEARCH RESULT ==========");

					for (Book b : searchedBooks) {

						System.out.println(b);
					}
				}

				break;

			// 5. Update Book
			case "5":

				int updateId = Integer.parseInt(IO.readln("Enter Book ID: "));

				Book existingBook = service.searchBookById(updateId);

				if (existingBook == null) {

					IO.println("Book not found.");

					break;
				}

				String newTitle = IO.readln("Enter New Title: ");

				String newAuthor = IO.readln("Enter New Author: ");

				if (newTitle.isBlank() || newAuthor.isBlank()) {

					IO.println("Title and Author are required.");

					break;
				}

				Book updatedBook = new Book(updateId, newTitle, newAuthor, existingBook.isIssued());

				if (service.updateBook(updatedBook)) {

					IO.println("Book updated successfully.");

				} else {

					IO.println("Failed to update book.");
				}

				break;

			// 6. Delete Book
			case "6":

				int deleteId = Integer.parseInt(IO.readln("Enter Book ID: "));

				Book bookToDelete = service.searchBookById(deleteId);

				if (bookToDelete == null) {

					IO.println("Book not found.");

					break;
				}

				if (bookToDelete.isIssued()) {

					IO.println("Issued book cannot be deleted.");

					break;
				}

				if (service.deleteBook(deleteId)) {

					IO.println("Book deleted successfully.");

				} else {

					IO.println("Failed to delete book.");
				}

				break;

			// 7. Issue Book
			case "7":

				int issueId = Integer.parseInt(IO.readln("Enter Book ID: "));

				Book bookToIssue = service.searchBookById(issueId);

				if (bookToIssue == null) {

					IO.println("Book not found.");

					break;
				}

				if (bookToIssue.isIssued()) {

					IO.println("Book is already issued.");

					break;
				}

				if (service.issueBook(issueId)) {

					IO.println("Book issued successfully.");

				} else {

					IO.println("Failed to issue book.");
				}

				break;

			// 8. Return Book
			case "8":

				int returnId = Integer.parseInt(IO.readln("Enter Book ID: "));

				Book bookToReturn = service.searchBookById(returnId);

				if (bookToReturn == null) {

					IO.println("Book not found.");

					break;
				}

				if (!bookToReturn.isIssued()) {

					IO.println("Book is already available.");

					break;
				}

				if (service.returnBook(returnId)) {

					IO.println("Book returned successfully.");

				} else {

					IO.println("Failed to return book.");
				}

				break;

			// 9. Available Books
			case "9":

				List<Book> availableBooks = service.getAvailableBooks();

				if (availableBooks.isEmpty()) {

					IO.println("No available books found.");

				} else {

					System.out.println();

					System.out.println("======= AVAILABLE BOOKS =======");

					for (Book b : availableBooks) {

						System.out.println(b);
					}
				}

				break;

			// 10. Issued Books
			case "10":

				List<Book> issuedBooks = service.getIssuedBooks();

				if (issuedBooks.isEmpty()) {

					IO.println("No issued books found.");

				} else {

					System.out.println();

					System.out.println("========= ISSUED BOOKS =========");

					for (Book b : issuedBooks) {

						System.out.println(b);
					}
				}

				break;

			// 11. Total Books
			case "11":

				IO.println("Total Books : " + service.getTotalBooks());

				break;

			// 12. Total Available Books
			case "12":

				IO.println("Total Available Books : " + service.getAvailableCount());

				break;

			// 13. Total Issued Books
			case "13":

				IO.println("Total Issued Books : " + service.getIssuedCount());

				break;

			// 14. Dashboard
			case "14":

				service.showDashboard();

				break;

			// 15. Exit
			case "15":

				IO.println("Thank you for using Library Management System.");

				return;

			default:

				IO.println("Invalid choice. Please enter 1 to 15.");
			}
		}
	}
}