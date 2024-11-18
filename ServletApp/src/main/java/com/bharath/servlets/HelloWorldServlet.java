package com.bharath.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebServlet(urlPatterns = { "/helloWorld", "/" })
public class HelloWorldServlet extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.println("<html>");
			out.println("<body>");
				out.println("<h1>Hello World!</h1>");
			out.println("</body>");
		out.println("</html>");
	}
}
