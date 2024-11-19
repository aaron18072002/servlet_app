package com.bharath.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost/mysql","root","123456");
			Statement statement = connection.createStatement();
//			int result = statement.executeUpdate
//					("""
//						INSERT INTO ACCOUNT(accno,lastname,firstname,bal)
//						VALUES (2,'Nguyen','Nemo',15000);
//							""");
//			System.out.printf("%d rows affected", result).println();
			int result = statement.executeUpdate
					("""
						SET @currentBalOfNemo = (SELECT bal FROM ACCOUNT WHERE accno = 2);

						SET SQL_SAFE_UPDATES = 0; 
						UPDATE ACCOUNT
						SET bal = @currentBalOfNemo + 35000
						WHERE accno = 2;
						SET SQL_SAFE_UPDATES = 1;
							""");
			System.out.printf("%d rows affected", result).println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
