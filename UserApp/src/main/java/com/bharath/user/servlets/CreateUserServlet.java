package com.bharath.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/addServlet",initParams = {
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost/servlet_app"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPassword", value = "123456")
})
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection
					(config.getInitParameter("dbUrl"),
					 config.getInitParameter("dbUser"),
					 config.getInitParameter("dbPassword"));
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		try(Statement statement = this.connection.createStatement()) {
			int result = statement.executeUpdate
					("""
						INSERT INTO USER(FirstName,LastName,Email,Password)
						VALUES ('%s','%s','%s','%s');
							""".formatted(firstName,lastName,email,password));
			
			PrintWriter out = res.getWriter();
			if(result > 0) {				
				out.println("<h1>User have inserted</h1>");
			} else {
				out.println("<h1>Error while inserting user</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
