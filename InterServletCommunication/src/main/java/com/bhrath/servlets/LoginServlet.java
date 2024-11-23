package com.bhrath.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement statement;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection
					("jdbc:mysql://localhost/servlet_app","root","123456");
			this.statement = con.prepareStatement
					("""
						SELECT * FROM User
						WHERE Email = ?
						AND Password = ?;
							""");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			this.statement.setString(1, email);
			this.statement.setString(2, password);
			
			this.statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(this.con != null && this.statement != null) {
			try {
				this.con.close();
				this.statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
