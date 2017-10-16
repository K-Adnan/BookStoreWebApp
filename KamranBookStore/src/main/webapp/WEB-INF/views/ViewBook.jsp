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
table, th {
	border: 1px solid grey;
}
</style>
</head>
<body>
	${message}
	<a href="home"> Home </a>
	<br />
	<img src="${book.imageUrl}" height="300" width="auto">
	<h2>${book.title}</h2>
	By
	<c:forEach items="${book.authors}" var="a">
		${a.firstName} ${a.lastName}<br />
	</c:forEach>
	<h4>
		<a href="viewBooksByCategory?category=${book.category}">
			${book.category} </a>
	</h4>
	<h4>ISBN : ${book.isbn}</h4>
	<h4>${book.numberOfPages} Pages</h4>
	<h4>Price : £${book.price}</h4>
	<form action="addBookToBasket?isbn=${book.isbn}" method="post">
		<input name="quantity" size="1"> <input type="Submit"
			value="Add to Cart" />
	</form>
	Add Customer Rating
	<form action="rateBook?isbn=${book.isbn}" method="post">
		<select name="rating">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> <input type="submit" value="Rate">
	</form>
</body>
</html>