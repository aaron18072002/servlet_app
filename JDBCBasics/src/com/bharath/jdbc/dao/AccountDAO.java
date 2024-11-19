package com.bharath.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountDAO {
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost/mysql","root","123456");
			System.out.println(connection.toString());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
