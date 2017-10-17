<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Kamran's BookStore</h2>
${message}
<h3> Please login or register</h3>
<h3> <a href="registerUser"> Register </a></h3>
<h3> <a href="registerAuthor"> Register as Author </a></h3>

<form action="j_security_check" method="post">
       
       Email Address : <input name="j_username" /> <br/>
       Password: <input type="password" name="j_password"/><br/>
       <input type="Submit" value="Login" />
</form>

<br/>
</body>
</html>
