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
</style>
</head>
<body>
<a href="../home"> Home </a> <br/>
	<h3>View Author Requests</h3>
		
	<table>
		<tr>
			<th>Book Title</th>
			<th>Number Sold</th>
			<th>Unit Price</th>
			<th>Total Sales</th>
		</tr>
	<c:forEach items="${booksList}" var="b">
		<tr>
			<th>${b.title}</th>
			<th>${b.sales}</th>
			<th>${b.price}</th>
			<th>${b.sales * b.price}</th>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>