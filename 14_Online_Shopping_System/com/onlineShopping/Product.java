package com.onlineShopping;

public class Product {

	private int id;
	private String name;
	private String category;
	private int stock;
	private double price;

	public Product(int id, String name, String category, int stock, double price) {

		this.id = id;
		this.name = name;
		this.category = category;
		this.stock = stock;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public int getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {

		return id + " | " + name + " | " + category + " | Stock: " + stock + " | Rs." + price;
	}
}