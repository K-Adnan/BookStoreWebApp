<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List a new Book</title>
</head>
<body>
	<h2> List a new Book </h2>
	
	<sf:form method="post" action="doListBook" modelAttribute="book">
	<h2> Update Your Profile </h2>
	<a href="adminHome"> Home </a> <br/>
	<sf:input type="hidden" path="emailAddress" value="${user.emailAddress}"/>
	First Name : <sf:input path="firstName" value="${user.firstName}"/><br/>
<input type="submit" value="Go"/>
</sf:form>
	
</body>
</html>