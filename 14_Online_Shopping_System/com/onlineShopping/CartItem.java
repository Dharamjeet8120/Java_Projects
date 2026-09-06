package com.onlineShopping;

public class CartItem {

	private Product product;
	private int quantity;

	public CartItem(Product product, int quantity) {

		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotal() {

		return product.getPrice() * quantity;
	}
}