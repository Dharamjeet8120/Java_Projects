package com.food;

import java.util.ArrayList;
import java.util.List;

public class FoodService {

	private static List<FoodItem> menu = new ArrayList<>();

	private static List<OrderItem> cart = new ArrayList<>();

	private static List<String> orderHistory = new ArrayList<>();

	private static double totalRevenue = 0;

	static {

		menu.add(new FoodItem(1, "Biryani", 180));

		menu.add(new FoodItem(2, "Fried Rice", 140));

		menu.add(new FoodItem(3, "Burger", 120));

		menu.add(new FoodItem(4, "Pizza", 250));

		menu.add(new FoodItem(5, "Momos", 100));
	}

	public static void startApplication() {

		while (true) {

			IO.println("\n===== FOOD ORDERING SYSTEM =====");

			IO.println("1. View Menu");
			IO.println("2. Add Food Item");
			IO.println("3. Search Food");
			IO.println("4. Update Food");
			IO.println("5. Delete Food");

			IO.println("6. Place Order");
			IO.println("7. View Cart");
			IO.println("8. Remove From Cart");

			IO.println("9. Generate Bill");
			IO.println("10. Order History");
			IO.println("11. Revenue Report");

			IO.println("12. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				viewMenu();
				break;

			case 2:
				addFood();
				break;

			case 3:
				searchFood();
				break;

			case 4:
				updateFood();
				break;

			case 5:
				deleteFood();
				break;

			case 6:
				placeOrder();
				break;

			case 7:
				viewCart();
				break;

			case 8:
				removeFromCart();
				break;

			case 9:
				generateBill();
				break;

			case 10:
				viewOrderHistory();
				break;

			case 11:
				revenueReport();
				break;

			case 12:
				IO.println("Thank You!");
				return;

			default:
				IO.println("Invalid Choice!");
			}
		}
	}

	private static void viewMenu() {

		if (menu.isEmpty()) {

			IO.println("Menu Is Empty!");
			return;
		}

		IO.println("\n===== FOOD MENU =====");

		for (FoodItem food : menu) {

			IO.println(food);
		}
	}

	private static void addFood() {

		int id = Integer.parseInt(IO.readln("Food ID: "));

		String name = IO.readln("Food Name: ");

		double price = Double.parseDouble(IO.readln("Price: "));

		menu.add(new FoodItem(id, name, price));

		IO.println("Food Added Successfully!");
	}

	private static void searchFood() {

		String name = IO.readln("Food Name: ");

		boolean found = false;

		for (FoodItem food : menu) {

			if (food.getName().equalsIgnoreCase(name)) {

				IO.println(food);

				found = true;
			}
		}

		if (!found) {

			IO.println("Food Not Found!");
		}
	}

	private static void updateFood() {

		int id = Integer.parseInt(IO.readln("Food ID: "));

		for (FoodItem food : menu) {

			if (food.getId() == id) {

				String name = IO.readln("New Food Name: ");

				double price = Double.parseDouble(IO.readln("New Price: "));

				food.setName(name);
				food.setPrice(price);

				IO.println("Food Updated Successfully!");

				return;
			}
		}

		IO.println("Food Not Found!");
	}

	private static void deleteFood() {

		int id = Integer.parseInt(IO.readln("Food ID: "));

		boolean removed = menu.removeIf(food -> food.getId() == id);

		if (removed) {

			IO.println("Food Deleted Successfully!");

		} else {

			IO.println("Food Not Found!");
		}
	}

	private static void placeOrder() {

		int id = Integer.parseInt(IO.readln("Food ID: "));

		int quantity = Integer.parseInt(IO.readln("Quantity: "));

		if (quantity <= 0) {

			IO.println("Invalid Quantity!");
			return;
		}

		for (FoodItem food : menu) {

			if (food.getId() == id) {

				cart.add(new OrderItem(food, quantity));

				IO.println("Food Added To Cart!");

				return;
			}
		}

		IO.println("Food Not Found!");
	}

	private static void viewCart() {

		if (cart.isEmpty()) {

			IO.println("Cart Is Empty!");
			return;
		}

		IO.println("\n===== YOUR CART =====");

		double total = 0;

		for (OrderItem item : cart) {

			IO.println(item.getFoodItem().getName() + " | Qty: " + item.getQuantity() + " | Rs." + item.getTotal());

			total += item.getTotal();
		}

		IO.println("----------------------");
		IO.println("Subtotal : Rs." + total);
	}

	private static void removeFromCart() {

		int id = Integer.parseInt(IO.readln("Food ID: "));

		boolean removed = cart.removeIf(item -> item.getFoodItem().getId() == id);

		if (removed) {

			IO.println("Item Removed From Cart!");

		} else {

			IO.println("Item Not Found In Cart!");
		}
	}

	private static void generateBill() {

		if (cart.isEmpty()) {

			IO.println("Cart Is Empty!");
			return;
		}

		double subtotal = 0;

		IO.println("\n========== BILL ==========");

		for (OrderItem item : cart) {

			IO.println(item.getFoodItem().getName() + " x " + item.getQuantity() + " = Rs." + item.getTotal());

			subtotal += item.getTotal();
		}

		double discount = 0;

		String coupon = IO.readln("Coupon Code (FOOD10): ");

		if (coupon.equalsIgnoreCase("FOOD10")) {

			discount = subtotal * 0.10;
		}

		double taxableAmount = subtotal - discount;

		double gst = taxableAmount * 0.05;

		double finalAmount = taxableAmount + gst;

		IO.println("--------------------------");

		IO.println("Subtotal : Rs." + subtotal);

		IO.println("Discount : Rs." + discount);

		IO.println("GST (5%) : Rs." + gst);

		IO.println("Final Amount : Rs." + finalAmount);

		totalRevenue += finalAmount;

		orderHistory.add("Order Amount : Rs." + finalAmount);

		cart.clear();

		IO.println("Order Placed Successfully!");
	}

	private static void viewOrderHistory() {

		if (orderHistory.isEmpty()) {

			IO.println("No Order History!");
			return;
		}

		IO.println("\n===== ORDER HISTORY =====");

		for (String order : orderHistory) {

			IO.println(order);
		}
	}

	private static void revenueReport() {

		IO.println("\n===== REVENUE REPORT =====");

		IO.println("Total Orders : " + orderHistory.size());

		IO.println("Total Revenue : Rs." + totalRevenue);
	}
}