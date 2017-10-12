<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${message}
<h2> View Personal Details </h2>
Email Address : ${user.emailAddress}<br/>
First Name : ${user.firstName}<br/>
Last Name : ${user.lastName}<br/>
Address : ${user.address}<br/>
Phone Number : ${user.phoneNumber}<br/>

<br/><br/>
<a href="editPersonalDetails"> Edit Profile </a>
</body>
</html>