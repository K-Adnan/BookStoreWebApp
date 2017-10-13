<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> Register New Admin </h2>
	<sf:form method="post" action="doRegisterAdmin" modelAttribute="admin">
		First Name : <sf:input type="text" path="firstName" /><br/>
		Last Name : <sf:input type="text" path="lastName" /><br/>
		Postal Address : <sf:input type="text" path="address" /><br/>
		Phone Number : <sf:input type="text" path="phoneNumber" /><br/>
		Password : <sf:input type="password" path="password" /><br/>
		Confirm Password : <sf:input type="password" path="confirmPassword" /><br/>
		<br /> <input type="submit" value="Register">
	</sf:form>
</body>
</html>