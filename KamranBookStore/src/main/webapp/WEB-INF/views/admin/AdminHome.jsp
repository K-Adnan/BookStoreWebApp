<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3> ${emailAddress} <a href="../viewPersonalDetails">Personal Details</a></h3>
${message}
<h2>ADMIN HOME</h2>

<h3> <a href="viewUser"> View User </a></h3>
<h3> <a href="../searchBook"> Search Book</a></h3>
<h3> <a href="viewAllSales"> View All Sales</a></h3>
<h3> <a href="../author/listBook"> List New Book</a></h3>
<h3> <a href="viewAuthorRequests"> View Author Requests </a></h3>
<h3> <a href="../logout"> Log Out </a></h3>

</body>
</html>