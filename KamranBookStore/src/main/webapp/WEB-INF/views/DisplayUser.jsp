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
<h2> User Information </h2>
First Name : ${profile.firstName}<br/>
Last Name : ${profile.lastName}<br/>
Gender : ${profile.gender}<br/>
Profession : ${profile.profession}<br/>
Hobbies : ${profile.hobbies}<br/>

<a href="EditProfile"> Edit Profile </a>
</body>
</html>