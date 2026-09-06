package com.inventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryService {

	private static List<Product> products = new ArrayList<>();

	private static double totalSales = 0;

	public static void startApplication() {

		while (true) {

			IO.println("\n===== INVENTORY MANAGEMENT =====");
			IO.println("1. Add Product");
			IO.println("2. View Products");
			IO.println("3. Sell Product");
			IO.println("4. Search Product");
			IO.println("5. Update Product");
			IO.println("6. Delete Product");
			IO.println("7. Add Stock");
			IO.println("8. Low Stock Alert");
			IO.println("9. Inventory Report");
			IO.println("10. Sort By Price");
			IO.println("11. Total Sales");
			IO.println("12. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				addProduct();
				break;

			case 2:
				viewProducts();
				break;

			case 3:
				sellProduct();
				break;

			case 4:
				searchProduct();
				break;

			case 5:
				updateProduct();
				break;

			case 6:
				deleteProduct();
				break;

			case 7:
				addStock();
				break;

			case 8:
				lowStockAlert();
				break;

			case 9:
				inventoryReport();
				break;

			case 10:
				sortByPrice();
				break;

			case 11:
				showSales();
				break;

			case 12:
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

		int quantity = Integer.parseInt(IO.readln("Quantity: "));

		double price = Double.parseDouble(IO.readln("Price: "));

		products.add(new Product(id, name, category, quantity, price));

		IO.println("Product Added Successfully!");
	}

	private static void viewProducts() {

		if (products.isEmpty()) {

			IO.println("No Products Found!");
			return;
		}

		for (Product product : products) {

			IO.println(product);
		}
	}

	private static void sellProduct() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		int qty = Integer.parseInt(IO.readln("Sell Quantity: "));

		for (Product product : products) {

			if (product.getId() == id) {

				if (product.sellProduct(qty)) {

					totalSales += qty * product.getPrice();

					IO.println("Sale Successful!");

				} else {

					IO.println("Insufficient Stock!");
				}

				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void searchProduct() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		for (Product product : products) {

			if (product.getId() == id) {

				IO.println(product);
				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void updateProduct() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		for (Product product : products) {

			if (product.getId() == id) {

				product.setName(IO.readln("New Name: "));

				product.setCategory(IO.readln("New Category: "));

				product.setPrice(Double.parseDouble(IO.readln("New Price: ")));

				IO.println("Product Updated!");
				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void deleteProduct() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		boolean removed = products.removeIf(p -> p.getId() == id);

		if (removed) {

			IO.println("Product Deleted!");

		} else {

			IO.println("Product Not Found!");
		}
	}

	private static void addStock() {

		int id = Integer.parseInt(IO.readln("Product ID: "));

		int qty = Integer.parseInt(IO.readln("Add Quantity: "));

		for (Product product : products) {

			if (product.getId() == id) {

				product.addStock(qty);

				IO.println("Stock Added!");
				return;
			}
		}

		IO.println("Product Not Found!");
	}

	private static void lowStockAlert() {

		IO.println("\n=== LOW STOCK PRODUCTS ===");

		for (Product product : products) {

			if (product.getQuantity() < 5) {

				IO.println(product);
			}
		}
	}

	private static void inventoryReport() {

		int totalQuantity = 0;

		for (Product product : products) {

			totalQuantity += product.getQuantity();
		}

		IO.println("\n===== REPORT =====");
		IO.println("Products : " + products.size());

		IO.println("Quantity : " + totalQuantity);

		IO.println("Sales : " + totalSales);
	}

	private static void sortByPrice() {

		products.sort(Comparator.comparingDouble(Product::getPrice));

		IO.println("Sorted By Price!");
	}

	private static void showSales() {

		IO.println("Total Sales Amount : " + totalSales);
	}
}