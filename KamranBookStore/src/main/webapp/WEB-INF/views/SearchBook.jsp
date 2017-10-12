<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Search</title>
</head>
<body>
<h2> Search for Books </h2> 

<h3> <a href="viewAllBooks"> View All Books</a></h3>
<form action="displayBooks" method="get"><br/>
	Find Book using ISBN : <input name="isbn"/><br/>
	<hr>
	<h4>Advanced Search</h4>
	Title : <input name="title"/><br/>
	Author : <input name="author"/><br/>
	Category : <input name="category"/><br/>
	Price : Min <input name="min" value="0.00" type="number" step="0.01" min="0.00"/> Max <input name="max" value="1000.00" type="number" step="0.01" min="0.00"/><br/>
	<input type="submit" value="Go"/>
</form>
</body>
</html>