<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
        <sf:form method="post" action="doRegisterUa" modelAttribute="ua">
        <h2> Request to become an Author </h2>
        <p>NOTE: Your request will be sent to an Administrator who will approve or reject it within the next 24 hours.</p>


		<label for="country">First Name : </label><sf:input type="text" path="firstName" /><br/>
		<label for="country">Last Name : </label><sf:input type="text" path="lastName" /><br/>
		<label for="country">Postal Address : </label><sf:input type="text" path="address" /><br/>
		<label for="country">Phone Number : </label><sf:input type="text" path="phoneNumber" /><br/>
		<label for="country">Password : </label><sf:input type="password" path="password" /><br/>
		<label for="country">Confirm Password : </label><sf:input type="password" path="confirmPassword" /><br/>
		<br /> <input type="submit" value="Submit Request">
		<a href="returnToHome"> Return to Login </a>
	</sf:form>
</body>
</html>