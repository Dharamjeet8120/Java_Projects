package com.food;

public class OrderItem {

	private FoodItem foodItem;
	private int quantity;

	public OrderItem(FoodItem foodItem, int quantity) {

		this.foodItem = foodItem;
		this.quantity = quantity;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotal() {

		return foodItem.getPrice() * quantity;
	}
}