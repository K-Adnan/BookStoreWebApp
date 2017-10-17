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
</style>
</head>
<body>
<a href="../home"> Home </a> <br/>
	<h3>View Rejected Authors</h3>
		
	<table>
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>Phone Number</th>
			<th>Reason for Rejection</th>
		</tr>
	<c:forEach items="${rejectedAuthors}" var="rj">
		<tr>
			<th>${rj.id}</th>
			<th>${rj.firstName}</th>
			<th>${rj.lastName}</th>
			<th>${rj.address}</th>
			<th>${rj.phoneNumber}</th>
			<th>${rj.reasonForRejection}</th>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>