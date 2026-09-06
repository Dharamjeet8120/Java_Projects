package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/bankingApplication";

	private static final String USER = "root";
	private static final String PASSWORD = "812046";

	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {

			throw new RuntimeException("Database Connection Failed : " + e.getMessage());
		}
	}
}