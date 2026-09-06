package com.banking;

import java.sql.*;

public class AccountService {

	private Connection con;

	public AccountService() {

		con = DBConnection.getConnection();

		try {

			Statement st = con.createStatement();

			st.executeUpdate("CREATE TABLE IF NOT EXISTS accounts(" + "id INT PRIMARY KEY," + "name VARCHAR(100),"
					+ "balance DOUBLE)");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS transactions(" + "txn_id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "account_id INT," + "txn_type VARCHAR(50)," + "amount DOUBLE,"
					+ "txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Create Account
	public void createAccount() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			String name = IO.readln("Enter Account Holder Name: ");

			double balance = Double.parseDouble(IO.readln("Enter Opening Balance: "));

			PreparedStatement ps = con.prepareStatement("INSERT INTO accounts VALUES(?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, balance);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Account Created Successfully");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// View All Accounts
	public void viewAllAccounts() {

		try {

			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM accounts");

			IO.println("\n===== ACCOUNT LIST =====");

			while (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("balance"));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Search Account
	public void searchAccount() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println(rs.getInt("id") + " | " + rs.getString("name") + " | Rs." + rs.getDouble("balance"));

			} else {

				IO.println("Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Check Balance
	public void checkBalance() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			PreparedStatement ps = con.prepareStatement("SELECT balance FROM accounts WHERE id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println("Current Balance : Rs." + rs.getDouble("balance"));

			} else {

				IO.println("Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Deposit Money
	public void depositMoney() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			double amount = Double.parseDouble(IO.readln("Enter Deposit Amount: "));

			if (amount <= 0) {
				IO.println("Invalid Amount");
				return;
			}

			PreparedStatement ps = con
					.prepareStatement("UPDATE accounts " + "SET balance = balance + ? " + "WHERE id = ?");

			ps.setDouble(1, amount);
			ps.setInt(2, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				addTransaction(id, "Deposit", amount);

				IO.println("Amount Deposited Successfully");

			} else {

				IO.println("Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Withdraw Money
	public void withdrawMoney() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			double amount = Double.parseDouble(IO.readln("Enter Withdrawal Amount: "));

			if (amount <= 0) {
				IO.println("Invalid Amount");
				return;
			}

			PreparedStatement ps = con.prepareStatement(
					"UPDATE accounts " + "SET balance = balance - ? " + "WHERE id = ? AND balance >= ?");

			ps.setDouble(1, amount);
			ps.setInt(2, id);
			ps.setDouble(3, amount);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				addTransaction(id, "Withdraw", amount);

				IO.println("Amount Withdrawn Successfully");

			} else {

				IO.println("Insufficient Balance " + "or Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Transfer Money
	public void transferMoney() {

		try {

			int fromId = Integer.parseInt(IO.readln("From Account ID: "));

			int toId = Integer.parseInt(IO.readln("To Account ID: "));

			double amount = Double.parseDouble(IO.readln("Transfer Amount: "));

			if (amount <= 0) {

				IO.println("Invalid Amount");
				return;
			}

			if (fromId == toId) {

				IO.println("Source and Destination " + "Account Cannot Be Same");

				return;
			}

			con.setAutoCommit(false);

			PreparedStatement withdraw = con.prepareStatement(
					"UPDATE accounts " + "SET balance = balance - ? " + "WHERE id = ? AND balance >= ?");

			withdraw.setDouble(1, amount);
			withdraw.setInt(2, fromId);
			withdraw.setDouble(3, amount);

			int withdrawRows = withdraw.executeUpdate();

			if (withdrawRows == 0) {

				con.rollback();
				con.setAutoCommit(true);

				IO.println("Insufficient Balance " + "or Sender Account Not Found");

				return;
			}

			PreparedStatement deposit = con
					.prepareStatement("UPDATE accounts " + "SET balance = balance + ? " + "WHERE id = ?");

			deposit.setDouble(1, amount);
			deposit.setInt(2, toId);

			int depositRows = deposit.executeUpdate();

			if (depositRows == 0) {

				con.rollback();
				con.setAutoCommit(true);

				IO.println("Receiver Account Not Found");

				return;
			}

			addTransaction(fromId, "Transfer Sent", amount);

			addTransaction(toId, "Transfer Received", amount);

			con.commit();

			con.setAutoCommit(true);

			IO.println("Money Transferred Successfully");

		} catch (Exception e) {

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException ex) {
				IO.println(ex.getMessage());
			}

			IO.println(e.getMessage());
		}
	}

	// Add Transaction
	private void addTransaction(int accountId, String type, double amount) {

		try {

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO transactions" + "(account_id, txn_type, amount)" + " VALUES(?,?,?)");

			ps.setInt(1, accountId);
			ps.setString(2, type);
			ps.setDouble(3, amount);

			ps.executeUpdate();

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Update Account Holder Name
	public void updateAccountHolderName() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			String newName = IO.readln("Enter New Account Holder Name: ");

			if (newName.isBlank()) {
				IO.println("Name Cannot Be Empty");
				return;
			}

			PreparedStatement ps = con.prepareStatement("UPDATE accounts " + "SET name = ? " + "WHERE id = ?");

			ps.setString(1, newName);
			ps.setInt(2, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				IO.println("Account Holder Name Updated Successfully");

			} else {

				IO.println("Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Delete Account
	public void deleteAccount() {

		try {

			int id = Integer.parseInt(IO.readln("Enter Account ID: "));

			// Check account balance before deleting
			PreparedStatement check = con.prepareStatement("SELECT balance FROM accounts WHERE id = ?");

			check.setInt(1, id);

			ResultSet rs = check.executeQuery();

			if (!rs.next()) {

				IO.println("Account Not Found");
				return;
			}

			double balance = rs.getDouble("balance");

			if (balance > 0) {

				IO.println("Account Cannot Be Deleted!");

				IO.println("Please Withdraw Remaining Balance First.");

				return;
			}

			// Delete transaction history first
			PreparedStatement deleteTransactions = con
					.prepareStatement("DELETE FROM transactions " + "WHERE account_id = ?");

			deleteTransactions.setInt(1, id);
			deleteTransactions.executeUpdate();

			// Delete account
			PreparedStatement deleteAccount = con.prepareStatement("DELETE FROM accounts WHERE id = ?");

			deleteAccount.setInt(1, id);

			int rows = deleteAccount.executeUpdate();

			if (rows > 0) {

				IO.println("Account Deleted Successfully");

			} else {

				IO.println("Account Not Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Account Count
	public void accountCount() {

		try {

			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM accounts");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println("Total Accounts : " + rs.getInt(1));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Total Bank Balance
	public void totalBankBalance() {

		try {

			PreparedStatement ps = con.prepareStatement("SELECT COALESCE(SUM(balance), 0) " + "FROM accounts");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println("Total Bank Balance : Rs." + rs.getDouble(1));
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Highest Balance Account
	public void highestBalanceAccount() {

		try {

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM accounts " + "ORDER BY balance DESC " + "LIMIT 1");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println("\n===== HIGHEST BALANCE ACCOUNT =====");

				IO.println("Account ID : " + rs.getInt("id"));

				IO.println("Holder Name : " + rs.getString("name"));

				IO.println("Balance : Rs." + rs.getDouble("balance"));

			} else {

				IO.println("No Accounts Available");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Lowest Balance Account
	public void lowestBalanceAccount() {

		try {

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM accounts " + "ORDER BY balance ASC " + "LIMIT 1");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				IO.println("\n===== LOWEST BALANCE ACCOUNT =====");

				IO.println("Account ID : " + rs.getInt("id"));

				IO.println("Holder Name : " + rs.getString("name"));

				IO.println("Balance : Rs." + rs.getDouble("balance"));

			} else {

				IO.println("No Accounts Available");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	public void miniStatement() {

		try {

			int accountId = Integer.parseInt(IO.readln("Enter Account ID: "));

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM transactions " + "WHERE account_id = ? " + "ORDER BY txn_date DESC " + "LIMIT 5");

			ps.setInt(1, accountId);

			ResultSet rs = ps.executeQuery();

			IO.println("\n===== MINI STATEMENT =====");

			boolean found = false;

			while (rs.next()) {

				found = true;

				IO.println(rs.getInt("txn_id") + " | " + rs.getString("txn_type") + " | Rs." + rs.getDouble("amount")
						+ " | " + rs.getTimestamp("txn_date"));
			}

			if (!found) {

				IO.println("No Transactions Found");
			}

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	public void bankingDashboard() {

		try {

			IO.println("\n======================================");
			IO.println("          BANKING DASHBOARD");
			IO.println("======================================");

			// Total Accounts
			PreparedStatement countPs = con.prepareStatement("SELECT COUNT(*) FROM accounts");

			ResultSet countRs = countPs.executeQuery();

			int totalAccounts = 0;

			if (countRs.next()) {

				totalAccounts = countRs.getInt(1);
			}

			// Total Bank Balance
			PreparedStatement balancePs = con.prepareStatement("SELECT COALESCE(SUM(balance), 0) " + "FROM accounts");

			ResultSet balanceRs = balancePs.executeQuery();

			double totalBalance = 0;

			if (balanceRs.next()) {

				totalBalance = balanceRs.getDouble(1);
			}

			// Highest Balance Account
			PreparedStatement highestPs = con.prepareStatement(
					"SELECT id, name, balance " + "FROM accounts " + "ORDER BY balance DESC " + "LIMIT 1");

			ResultSet highestRs = highestPs.executeQuery();

			// Lowest Balance Account
			PreparedStatement lowestPs = con.prepareStatement(
					"SELECT id, name, balance " + "FROM accounts " + "ORDER BY balance ASC " + "LIMIT 1");

			ResultSet lowestRs = lowestPs.executeQuery();

			IO.println("Total Accounts       : " + totalAccounts);

			IO.println("Total Bank Balance   : Rs." + totalBalance);

			if (highestRs.next()) {

				IO.println("\nHighest Balance Account");

				IO.println("Account ID           : " + highestRs.getInt("id"));

				IO.println("Holder Name          : " + highestRs.getString("name"));

				IO.println("Balance              : Rs." + highestRs.getDouble("balance"));
			}

			if (lowestRs.next()) {

				IO.println("\nLowest Balance Account");

				IO.println("Account ID           : " + lowestRs.getInt("id"));

				IO.println("Holder Name          : " + lowestRs.getString("name"));

				IO.println("Balance              : Rs." + lowestRs.getDouble("balance"));
			}

			IO.println("\n======================================");

		} catch (Exception e) {

			IO.println(e.getMessage());
		}
	}

	// Transaction History
	public void transactionHistory() {

		try {

			int accountId = Integer.parseInt(IO.readln("Enter Account ID: "));

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM transactions " + "WHERE account_id = ? " + "ORDER BY txn_date DESC");

			ps.setInt(1, accountId);

			ResultSet rs = ps.executeQuery();

			IO.println("\n===== TRANSACTION HISTORY =====");

			boolean found = false;

			while (rs.next()) {

				found = true;

				IO.println("Transaction ID : " + rs.getInt("txn_id"));

				IO.println("Type : " + rs.getString("txn_type"));

				IO.println("Amount : Rs." + rs.getDouble("amount"));

				IO.println("Date : " + rs.getTimestamp("txn_date"));

				IO.println("--------------------------------");
			}

			if (!found) {

				IO.println("No Transaction History Found");
			}

		} catch (Exception e) {

			IO.println("Transaction History Error : " + e.getMessage());
		}
	}
}