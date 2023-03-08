package com.Calculator3.Calculator3;

import java.sql.*;
import java.util.Scanner;


public class MavenDemo {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Calculator3";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sqljava106@2023";
	public static void main(String[] args) 
	{
		 
		// Prompt user to enter data
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter first number:");
		int num1 = scanner.nextInt();
		System.out.println("Enter second number:");
		int num2 = scanner.nextInt();
		System.out.println("Enter operation (+, -, *, /):");
		String operator = scanner.next();

		// Calculate result
		int result = 0;
		switch (operator) {
		    case "+":
		        result = num1 + num2;
		        break;
		    case "-":
		        result = num1 - num2;
		        break;
		    case "*":
		        result = num1 * num2;
		        break;
		    case "/":
		        result = num1 / num2;
		        break;
		}

		// Create a new document
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO calculations (num1, num2, operator, result) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, num1);
            stmt.setDouble(2, num2);
            stmt.setString(3, operator);
            stmt.setDouble(4, result);
            stmt.executeUpdate();
            System.out.println("Data stored in database.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
		scanner.close();
		

	}

}
