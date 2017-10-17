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
<title>List a new Book</title>
</head>
<body>
	<a href="../home"> Home </a> <br/>
	<h2> Edit Book </h2>
	
	<sf:form method="post" action="doEditBook?isbn=${book.isbn}" modelAttribute="book">
		Title : <sf:input path="title" size="70"/><br/>
		Quantity Available : <sf:input path="quantity"/><br/>
		Release Year : <sf:input path="releaseYear" /><br/>
		Number of Pages : <sf:input path="numberOfPages"/><br/>
		Category : <sf:input path="category"/><br/>
		Image (URL) : <sf:input path="imageUrl"/><br/>
		Author(s) : <input name="authorsString" size="100" value="${authorString}"/><br/>
		List any additional authors by adding their usernames separated by a comma (Use the list of Authors below to find authors)<br/>
		(e.g. adam.kay,william.shakespeare)<br/>
		
		<form:select path="authors" items="${authorsList}"/>
		<input type="submit" value="Go"/>
	</sf:form>
	
</body>
</html>