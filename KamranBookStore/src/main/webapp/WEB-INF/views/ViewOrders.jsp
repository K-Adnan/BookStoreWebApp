<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, th{
	text-align: left;
}
</style>
</head>
<body>
${message}
<a href="home"> Home </a> <br/>
	<h2>Order History</h2>
	<c:forEach items="${orders}" var="o">
		<h4>Order Date : ${o.orderDate}</h4>
		<c:forEach items="${o.cart.cartItems}" var="c">
			${c.quantity}x		
			<a href="displayBook?isbn=${c.book.isbn}">${c.book.title}</a><br/>
		</c:forEach>
		<bold>Total : ${o.cart.total}</bold>
	<hr>
	</c:forEach>
	<c:choose>
	
    <c:when test="${cart.total > '0'}">
	<a href="proceedCheckout?cartId=${cart.cartId}"> Proceed to Checkout </a> <br/>
    </c:when>
</c:choose>
	
</body>
</html>