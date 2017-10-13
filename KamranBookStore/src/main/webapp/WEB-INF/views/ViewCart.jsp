<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, th{
    border: 1px solid grey;
}
</style>
</head>
<body>
${message}
<a href="home"> Home </a> <br/>
	<h2>Shopping Cart</h2>
	<h3>Total Amount : ${cart.total}</h3><br/>
	<c:forEach items="${cart.cartItems}" var="c">
		Title : ${c.book.title}<br/>
		Price : £${c.book.price}<br/>
		Subtotal : £${c.subTotal}<br/>
		${c.quantity}<br/>
		<hr>
	</c:forEach>
</body>
</html>