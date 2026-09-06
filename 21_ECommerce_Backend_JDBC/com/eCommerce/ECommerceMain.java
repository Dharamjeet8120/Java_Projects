package com.eCommerce;

public class ECommerceMain {

	public static void main(String[] args) {

		ProductService service = new ProductService();

		while (true) {

			IO.println("\n========== INVENTORY MANAGEMENT SYSTEM ==========");

			IO.println("1. Add Product");
			IO.println("2. View Products");
			IO.println("3. Search Product");
			IO.println("4. Update Product");
			IO.println("5. Delete Product");
			IO.println("6. Sell Product");
			IO.println("7. Restock Product");
			IO.println("8. Low Stock Alert");
			IO.println("9. Out Of Stock Products");
			IO.println("10. Sort By Price");
			IO.println("11. Sort By Quantity");
			IO.println("12. Search By Name");
			IO.println("13. Most Expensive Product");
			IO.println("14. Cheapest Product");
			IO.println("15. Product Count");
			IO.println("16. Total Inventory Value");
			IO.println("17. Inventory Dashboard");
			IO.println("18. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				service.addProduct();
				break;

			case 2:
				service.viewProducts();
				break;

			case 3:
				service.searchProduct();
				break;

			case 4:
				service.updateProduct();
				break;

			case 5:
				service.deleteProduct();
				break;

			case 6:
				service.sellProduct();
				break;

			case 7:
				service.restockProduct();
				break;

			case 8:
				service.lowStockAlert();
				break;

			case 9:
				service.outOfStockProducts();
				break;

			case 10:
				service.sortByPrice();
				break;

			case 11:
				service.sortByQuantity();
				break;

			case 12:
				service.searchByName();
				break;

			case 13:
				service.mostExpensiveProduct();
				break;

			case 14:
				service.cheapestProduct();
				break;

			case 15:
				service.productCount();
				break;

			case 16:
				service.totalInventoryValue();
				break;

			case 17:
				service.inventoryDashboard();
				break;

			case 18:
				IO.println("Thank You...");
				System.exit(0);
				break;

			default:
				IO.println("Invalid Choice");
			}
		}
	}
}