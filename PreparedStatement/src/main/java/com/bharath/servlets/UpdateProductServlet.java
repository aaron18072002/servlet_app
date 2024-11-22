package com.bharath.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement stmt;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection
					("jdbc:mysql://localhost/servlet_app","root","123456");
			this.stmt = con.prepareStatement
					("""
						UPDATE Product
						SET Price = ?
						WHERE Id = ?;
							""");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer price = Integer.valueOf(request.getParameter("price"));
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		try {
			this.stmt.setInt(1, price);
			this.stmt.setInt(2, id);
			
			int result = this.stmt.executeUpdate();	
			
			PrintWriter out = response.getWriter();
			out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if(this.con != null && this.stmt != null) {
			try {
				this.con.close();
				this.stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
