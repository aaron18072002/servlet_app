package com.bharath.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost/mysql","root","123456");
			Statement statement = connection.createStatement();
			
			int insertedResult = statement.executeUpdate
					("""
						INSERT INTO ACCOUNT(accno,lastname,firstname,bal)
						VALUES (1000,'A','B',12000);
							""");
			System.out.printf("%d rows affected", insertedResult).println();
			
			int deletedResult = statement.executeUpdate
					(""" 
						DELETE FROM ACCOUNT
						WHERE accno = 1000;	
							""");
			System.out.printf("%d rows affected", deletedResult).println();
			
			System.out.println("-------------------");
			
			ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNT");
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt(4));
				System.out.println("--------------------");
			}
			
//			int result = statement.executeUpdate
//					("""
//						SET @currentBalOfNemo = (SELECT bal FROM ACCOUNT WHERE accno = 2);
//
//						SET SQL_SAFE_UPDATES = 0; 
//						UPDATE ACCOUNT
//						SET bal = @currentBalOfNemo + 35000
//						WHERE accno = 2;
//						SET SQL_SAFE_UPDATES = 1;
//							""");
//			System.out.printf("%d rows affected", result).println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
