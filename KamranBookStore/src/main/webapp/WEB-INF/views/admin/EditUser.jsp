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
<title>Insert title here</title>
</head>
<body>

<sf:form method="post" action="UpdateProfile" modelAttribute="user">
	<h2> Update Your Profile </h2>
	<a href="adminHome"> Home </a> <br/>
	<sf:input type="hidden" path="emailAddress" value="${user.emailAddress}"/>
	First Name : <sf:input path="firstName" value="${user.firstName}"/><br/>
	Last Name : <sf:input path="lastName" value="${user.lastName}"/><br/>
	Phone Number : <sf:input path="phoneNumber" value="${user.phoneNumber}"/><br/>
	Address : <sf:input size="50" path="address" value="${user.address}"/><br/>
<input type="submit" value="Go"/>
</sf:form>

</body>
</html>