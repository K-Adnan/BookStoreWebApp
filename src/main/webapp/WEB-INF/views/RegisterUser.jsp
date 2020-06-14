<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
<img src="images/bookstore-logo.jpg" alt="Login Picture" class="avatar">
	<h2> Register New User </h2>
    	<sf:form method="post" action="doRegisterUser" modelAttribute="user">
    		<label for="country">First Name :</label> <sf:input type="text" path="firstName" /><br/>
    		<label for="country">Last Name :</label> <sf:input type="text" path="lastName" /><br/>
    		<label for="country">Email Address :</label> <sf:input type="text" path="emailAddress" /><br/>
    		<label for="country">Postal Address :</label> <sf:input type="text" path="address" /><br/>
    		<label for="country">Phone Number :</label> <sf:input type="text" path="phoneNumber" /><br/>
    		<label for="country">Password :</label> <sf:input type="password" path="password" /><br/>
    		<label for="country">Confirm Password :</label> <sf:input type="password" path="confirmPassword" /><br/>
    		<input type="submit" value="Register">
    		<a href="returnToHome"> Return to Login </a>
    	</sf:form>
<br/>
</body>
</html>
