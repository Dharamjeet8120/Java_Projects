package com.library;

public class Book {

	private int id;
	private String title;
	private String author;
	private boolean issued;

	public Book() {
	}

	public Book(int id, String title, String author, boolean issued) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.issued = issued;
	}

	public Book(String title, String author, boolean issued) {
		this.title = title;
		this.author = author;
		this.issued = issued;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	@Override
	public String toString() {

		return "ID : " + id + " | Title : " + title + " | Author : " + author + " | Status : "
				+ (issued ? "Issued" : "Available");
	}
}