package com.bharath.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sourceServlet")
public class SourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies.length > 0 && cookies != null)
		for(Cookie cookie:cookies) {
			System.out.println(cookie.getName());
			System.out.println(cookie.getValue());
		}
		response.addCookie(new Cookie("accessToken", "123456"));
		String userName= request.getParameter("userName");
		HttpSession session = request.getSession();
		session.setAttribute("user", userName);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//URL rewriting
		String url = "targetServlet?assessToken=123456";
		out.println("<a href='targetServlet'>Click here to get username</a>");
	}

}
