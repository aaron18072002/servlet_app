package com.bharath.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readServlet")
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection
					("jdbc:mysql://localhost/servlet_app", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(Statement statement = this.connection.createStatement()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM User");
			PrintWriter out = response.getWriter();
			
			out.print("<table>");
				out.print("<tr>");
					out.print("<th>");
						out.println("FirstName");
					out.print("</th>");
					out.print("<th>");
						out.println("LastName");
					out.print("</th>");
					out.print("<th>");
						out.println("Email");
					out.print("</th>");
					out.print("<th>");
						out.println("Password");
					out.print("</th>");
				out.print("</tr>");
			while (rs.next()) {
				out.print("<tr>");
					out.print("<th>");
						out.println(rs.getString(1));
					out.print("</th>");
					out.print("<th>");
						out.println(rs.getString(2));
					out.print("</th>");
					out.print("<th>");
						out.println(rs.getString(3));
					out.print("</th>");
					out.print("<th>");
						out.println(rs.getString(4));
					out.print("</th>");
				out.print("</tr>");
			}
			out.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			if(this.connection != null) {				
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
