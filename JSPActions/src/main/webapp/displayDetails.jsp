<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="product" class="com.bharath.trainnings.jsp.Product">
		<jsp:setProperty name="product" property="*"></jsp:setProperty>
	</jsp:useBean>
	
	Product Details
	</br>Id:<jsp:getProperty property="id" name="product"/>
	</br>Name:<jsp:getProperty property="name" name="product"/>
	</br>Description:<jsp:getProperty property="name" name="product"/>
	</br>Price:<jsp:getProperty property="price" name="product"/>
</body>
</html>