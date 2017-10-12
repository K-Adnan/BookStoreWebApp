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
	<h3>LIST OF ALL BOOKS</h3>
		
	<table>
		<tr>
			<th>ISBN</th>
			<th>Author</th>
			<th>Title</th>
			<th>Category</th>
			<th>Price</th>
		</tr>
	<c:forEach items="${booksList}" var="b">
		<tr>
			<th>${b.isbn}</th>
			<th>${b.title}</th>
			<th>
				<c:forEach items="${b.authors}" var="a">
					${a.firstName} ${a.lastName}<br/>
				</c:forEach>
			</th>
			<th>${b.category}</th>
			<th>£${b.price}</th>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>