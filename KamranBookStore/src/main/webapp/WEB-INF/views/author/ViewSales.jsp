<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Sales</title>
<style>
tr, th{
    border-bottom: 1px solid grey;
}
</style>
</head>
<body>
	<a href="../home"> Home </a> <br/>
	<h2> View Sales </h2>
	
	<table>
		<tr>
			<th></th>
			<th>Title</th>
			<th>Number Sold</th>
			<th>Total Sales</th>
			<th>Unit Price</th>
			<th>Avg. Rating</th>
			<th>Edit</th>
		</tr>
	<c:forEach items="${listOfBooks}" var="b">
		<tr>
			<th><a href="../displayBook?isbn=${b.isbn}"><img src="${b.imageUrl}" height="80" width="auto"></a></th>
			<th> <a href="../displayBook?isbn=${b.isbn}"> ${b.title} </a></th>
			<th>${b.price * b.sales}</th>
			<th>${b.sales}</th>
			<th>£${b.price}</th>
			<th>${b.avgCustomerRating} (${b.numberOfReviews})</th>
			<th> <a href="editBook?isbn=${b.isbn}"> <img src="https://www.iconexperience.com/_img/o_collection_png/green_dark_grey/512x512/plain/edit.png" height="30" width="auto"></a> </th>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>