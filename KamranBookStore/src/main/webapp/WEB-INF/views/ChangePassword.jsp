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
<form method="post" action="updateNewPassword">
<h2> Change Password </h2>
	Current Password : <input size="15" name="currentPassword" type="password" value=""/><br/>
	New Password : <input size="15" name="password" type="password" value=""/><br/>
	Confirm New Password : <input size="15" name="confirmPassword" type="password"/><br/>
<input type="submit" value="Go"/>
</form>
</body>
</html>