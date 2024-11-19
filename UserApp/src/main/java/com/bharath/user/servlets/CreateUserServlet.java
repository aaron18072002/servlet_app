package com.bharath.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Override
	public void init() throws ServletException {		
		super.init();
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello World!</h1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {		
		super.doPost(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		try {
			if(this.connection != null) {				
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
