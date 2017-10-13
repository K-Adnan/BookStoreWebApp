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
	<h2>${book.title}</h2>
	By
	<c:forEach items="${book.authors}" var="a">
		${a.firstName} ${a.lastName}<br/>
	</c:forEach>
	<h4> <a href="viewBooksByCategory?category=${book.category}"> ${book.category} </a></h4>
	<h4>ISBN : ${book.isbn}</h4>
	<h4>${book.numberOfPages} Pages</h4>
	<h4>Price : �${book.price}</h4>
	<h4><a href="addBookToBasket?isbn={book.isbn}">Add to Basket</a></h4>
</body>
</html>