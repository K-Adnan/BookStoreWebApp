<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> Request to become an Author </h2>

<p>NOTE: Your request will be sent to an Administrator who will approve or reject it within the next 24 hours.</p>

	<sf:form method="post" action="doRegisterUa" modelAttribute="ua">
		First Name : <sf:input type="text" path="firstName" /><br/>
		Last Name : <sf:input type="text" path="lastName" /><br/>
		Postal Address : <sf:input type="text" path="address" /><br/>
		Phone Number : <sf:input type="text" path="phoneNumber" /><br/>
		Password : <sf:input type="password" path="password" /><br/>
		Confirm Password : <sf:input type="password" path="confirmPassword" /><br/>
		<br /> <input type="submit" value="Request">
	</sf:form>
</body>
</html>
