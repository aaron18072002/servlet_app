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

@WebServlet(urlPatterns = "/productServlet")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement pstmt;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection
					("jdbc:mysql://localhost/servlet_app","root","123456");
			this.pstmt = con.prepareStatement
					("""
						INSERT INTO Product(Name, Description, Price)
						VALUES (?,?,?);
							""");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String des = req.getParameter("description");
		Integer price = Integer.valueOf(req.getParameter("price"));
		
		try {
			this.pstmt.setString(1, name);
			this.pstmt.setString(2, des);
			this.pstmt.setInt(3, price);
			
			int result =  this.pstmt.executeUpdate();
			
			PrintWriter out = resp.getWriter();
			
			out.println("<html>");
				out.println("<body>");
					out.println("<h1>"+result+" product has created"+"</h1>");
				out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(this.con != null && this.pstmt != null) {
			try {
				this.con.close();
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
