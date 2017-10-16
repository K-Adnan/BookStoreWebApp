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
</style>
</head>
<body>
<a href="home"> Home </a> <br/>
	<h2>Complete Order</h2>
	
	<h3>Order Total : £${total}</h3>
	
	<form action="placeOrder?cartId=${cart.cartId}" method="post">
		First Name : <input name="emailAddress" value="${user.firstName}"/><br/>
		Last Name : <input name="emailAddress" value="${user.lastName}"/><br/>
		Address : <input name="emailAddress" value="${user.address}"/><br/>
		Phone Number : <input name="emailAddress" value="${user.phoneNumber}"/><br/>
		
		<input type="submit" value="Place Order"/>
	</form>


</body>
</html>