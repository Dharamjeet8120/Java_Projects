package com.eCommerce;

import java.sql.*;
import java.util.*;

public class ProductService {

	private Connection con;

	public ProductService() {

		con = DBConnection.getConnection();

		try {

			Statement st = con.createStatement();

			st.executeUpdate("CREATE TABLE IF NOT EXISTS products(" + "id INT PRIMARY KEY," + "name VARCHAR(100),"
					+ "price DOUBLE," + "qty INT)");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Add Product
	public void addProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			String name = IO.readln("Enter Product Name: ");

			double price = Double.parseDouble(IO.readln("Enter Price: "));

			int qty = Integer.parseInt(IO.readln("Enter Quantity: "));

			PreparedStatement ps = con.prepareStatement("INSERT INTO products VALUES(?,?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, price);
			ps.setInt(4, qty);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Product Added Successfully");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// View Products
	public void viewProducts() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products");

			IO.println("\n===== PRODUCT LIST =====");

			while (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("price") + " | Qty:"
						+ rs.getInt("qty"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Search Product
	public void searchProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("price") + " | Qty:"
						+ rs.getInt("qty"));

			} else {

				IO.println("Product Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Update Product
	public void updateProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			String name = IO.readln("Enter New Name: ");

			double price = Double.parseDouble(IO.readln("Enter New Price: "));

			int qty = Integer.parseInt(IO.readln("Enter New Quantity: "));

			PreparedStatement ps = con
					.prepareStatement("UPDATE products " + "SET name=?,price=?,qty=? " + "WHERE id=?");

			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setInt(3, qty);
			ps.setInt(4, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Product Updated");

			} else {

				IO.println("Product Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Delete Product
	public void deleteProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id=?");

			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Product Deleted");

			} else {

				IO.println("Product Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Sell Product
	public void sellProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			int qty = Integer.parseInt(IO.readln("Enter Sell Quantity: "));

			PreparedStatement ps = con
					.prepareStatement("UPDATE products " + "SET qty=qty-? " + "WHERE id=? AND qty>=?");

			ps.setInt(1, qty);
			ps.setInt(2, id);
			ps.setInt(3, qty);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Product Sold");

			} else {

				IO.println("Insufficient Stock");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Restock Product
	public void restockProduct() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Product ID: "));

			int qty = Integer.parseInt(IO.readln("Enter Restock Quantity: "));

			PreparedStatement ps = con.prepareStatement("UPDATE products " + "SET qty=qty+? " + "WHERE id=?");

			ps.setInt(1, qty);
			ps.setInt(2, id);

			ps.executeUpdate();

			IO.println("Stock Updated");

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Low Stock Alert
	public void lowStockAlert() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products WHERE qty < 10");

			IO.println("\n===== LOW STOCK PRODUCTS =====");

			boolean found = false;

			while (rs.next()) {

				found = true;

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Qty:" + rs.getInt("qty"));
			}

			if (!found) {

				IO.println("No Low Stock Products");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Out Of Stock Products
	public void outOfStockProducts() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products WHERE qty = 0");

			IO.println("\n===== OUT OF STOCK =====");

			boolean found = false;

			while (rs.next()) {

				found = true;

				IO.println(rs.getInt("id") + " | " + rs.getString("name"));
			}

			if (!found) {

				IO.println("No Out Of Stock Products");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Sort By Price
	public void sortByPrice() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products ORDER BY price");

			while (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("price"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Sort By Quantity
	public void sortByQuantity() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products ORDER BY qty");

			while (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Qty:" + rs.getInt("qty"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Search By Name
	public void searchByName() {

		try {

			String name = IO.readln("Enter Product Name: ");

			PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE name LIKE ?");

			ps.setString(1, "%" + name + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("price") + " | Qty:"
						+ rs.getInt("qty"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Most Expensive Product
	public void mostExpensiveProduct() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products ORDER BY price DESC LIMIT 1");

			if (rs.next()) {

				IO.println("Most Expensive Product : " + rs.getString("name") + " | Rs." + rs.getDouble("price"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Cheapest Product
	public void cheapestProduct() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products ORDER BY price ASC LIMIT 1");

			if (rs.next()) {

				IO.println("Cheapest Product : " + rs.getString("name") + " | Rs." + rs.getDouble("price"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Product Count
	public void productCount() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) total FROM products");

			if (rs.next()) {

				IO.println("Total Products : " + rs.getInt("total"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Total Inventory Value
	public void totalInventoryValue() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT SUM(price * qty) total FROM products");

			if (rs.next()) {

				IO.println("Total Inventory Value : Rs." + rs.getDouble("total"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Inventory Dashboard
	public void inventoryDashboard() {

		try {

			ResultSet count = con.createStatement().executeQuery("SELECT COUNT(*) total FROM products");

			count.next();

			ResultSet value = con.createStatement().executeQuery("SELECT SUM(price * qty) total FROM products");

			value.next();

			IO.println("\n===== INVENTORY DASHBOARD =====");

			IO.println("Total Products : " + count.getInt("total"));

			IO.println("Inventory Value : Rs." + value.getDouble("total"));

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}
}