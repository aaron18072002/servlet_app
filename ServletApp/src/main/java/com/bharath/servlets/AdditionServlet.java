package com.bharath.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("number1") == null && request.getParameter("number2") == null) {
			Long num1 = Long.valueOf(request.getParameter("number1"));
			Long num2 = Long.valueOf(request.getParameter("number2"));
			
			Long result = num1 + num2;
			
			PrintWriter out = response.getWriter();
			out.println("The result is " + result.toString());
		}
	}

}
