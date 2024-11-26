<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int num1 = Integer.parseInt(request.getParameter("number1"));
		int num2 = Integer.parseInt(request.getParameter("number2"));
		int sum = num1 + num2;
	%>
	<h1>
		Sum of <% out.println(num1); %> and <% out.println(num2); %> is <% out.println(sum); %>
	</h1>
</body>
</html>