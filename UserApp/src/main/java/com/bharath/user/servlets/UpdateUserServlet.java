package com.bharath.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Override
	public void init() throws ServletException {
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection
					("jdbc:mysql://localhost/servlet_app", "root", "123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try(Statement statement = this.connection.createStatement()) {
			int result = statement.executeUpdate
					("""
						UPDATE USER 
						SET Password = '%s'
						WHERE Email = '%s';
							""".formatted(password,email));
			
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<h1>User password has updated</h1>");
			} else {
				out.println("<h1>Error while update user</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
