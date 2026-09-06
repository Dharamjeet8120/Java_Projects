package com.onlineShopping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingService {

	private static List<Product> products = new ArrayList<>();

	private static List<CartItem> cart = new ArrayList<>();

	private static List<String> orderHistory = new ArrayList<>();

	private static double totalRevenue = 0;

	public static void startApplication() {

		while (true) {

			IO.println("\n===== ONLINE SHOPPING SYSTEM =====");
			IO.println("1. Add Product");
			IO.println("2. View Products");
			IO.println("3. Search Product");
			IO.println("4. Add To Cart");
			IO.println("5. View Cart");
			IO.println("6. Generate Bill");
			IO.println("7. Order History");
			IO.println("8. Revenue Report");
			IO.println("9. Sort By Price");
			IO.println("10. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				addProduct();
				break;

			case 2:
				viewProducts();
				break;

			case 3:
				searchProduct();
				break;

			case 4:
				addToCart();
				break;

			case 5:
				viewCart();
				break;

			case 6:
				generateBill();
				break;

			case 7:
				viewOrderHistory();
				break;

			case 8:
				revenueReport();
				break;

			case 9:
				sortByPrice();
				break;

			case 10:
				return;

			default:
				IO.println("Invalid Choice!");
			}
		}
	}

	private static void addProduct() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		String name = IO.readln("Product Name: ");

		String category = IO.readln("Category: ");

		int stock = Integer.parseInt(IO.readln("Stock: "));

		double price = Double.parseDouble(IO.readln("Price: "));

		products.add(new Product(id, name, category, stock, price));

		IO.println("Product Added Successfully!");
	}

	private static void viewProducts() {

		if (products.isEmpty()) {

			IO.println("No Products Available!");
			return;
		}

		for (Product product : products) {

			IO.println(product);
		}
	}

	private static void searchProduct() {

		int id = Integer.parseInt(IO.readln("Enter Product ID: "));

		for (Product product : products) {

			if (product.getId() == id) {

				IO.println(product);
				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void addToCart() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		int quantity = Integer.parseInt(IO.readln("Quantity: "));

		for (Product product : products) {

			if (product.getId() == id) {

				if (quantity > product.getStock()) {

					IO.println("Insufficient Stock!");
					return;
				}

				cart.add(new CartItem(product, quantity));

				product.setStock(product.getStock() - quantity);

				IO.println("Added To Cart!");
				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void viewCart() {

		if (cart.isEmpty()) {

			IO.println("Cart Is Empty!");
			return;
		}

		for (CartItem item : cart) {

			IO.println(item.getProduct().getName() + " | Qty: " + item.getQuantity() + " | Total: " + item.getTotal());
		}
	}

	private static void generateBill() {

		if (cart.isEmpty()) {

			IO.println("Cart Is Empty!");
			return;
		}

		double total = 0;

		IO.println("\n===== INVOICE =====");

		for (CartItem item : cart) {

			IO.println(item.getProduct().getName() + " | Qty: " + item.getQuantity() + " | Amount: " + item.getTotal());

			total += item.getTotal();
		}

		double discount = 0;

		String coupon = IO.readln("Coupon Code (JAVA10): ");

		if (coupon.equalsIgnoreCase("JAVA10")) {

			discount = total * 0.10;
		}

		double gst = total * 0.18;

		double finalAmount = total + gst - discount;

		IO.println("-----------------------");
		IO.println("Subtotal : " + total);
		IO.println("GST (18%) : " + gst);
		IO.println("Discount : " + discount);
		IO.println("Final Amount : " + finalAmount);

		totalRevenue += finalAmount;

		orderHistory.add("Order Amount : " + finalAmount);

		cart.clear();
	}

	private static void viewOrderHistory() {

		if (orderHistory.isEmpty()) {

			IO.println("No Orders Found!");
			return;
		}

		for (String order : orderHistory) {

			IO.println(order);
		}
	}

	private static void revenueReport() {

		IO.println("\n===== REVENUE REPORT =====");
		IO.println("Total Orders : " + orderHistory.size());

		IO.println("Total Revenue : " + totalRevenue);
	}

	private static void sortByPrice() {

		products.sort(Comparator.comparingDouble(Product::getPrice));

		IO.println("Products Sorted By Price!");
	}
}