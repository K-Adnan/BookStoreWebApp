<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Kamran's BookStore</h2>
${message}
<h3> Please login or register</h3>
<h3> <a href="register"> Register </a></h3>
<form action="home">
  Email Address :<br>
  <input type="text" name="emailAddress"><br>
  Password : <br>
  <input type="text" name="password"><br><br>
  <input type="submit" value="Submit">
</form>
<br/>
</body>
</html>
