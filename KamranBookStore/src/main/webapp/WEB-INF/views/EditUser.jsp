<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="UpdateProfile">
	<h2> Profile Information </h2>
	<a href="home"> Home </a> <br/>
	First Name : <input name="firstName" value="${user.firstName}"><br/>
	Last Name : <input name="lastName" value="${user.lastName}"><br/>
	Phone Number : <input name="phoneNumber" value="${user.phoneNumber}"><br/>
	Address : <input size="50" name="address" value="${user.address}"><br/>
<input type="submit" value="Go"/>
</form>
</body>
</html>