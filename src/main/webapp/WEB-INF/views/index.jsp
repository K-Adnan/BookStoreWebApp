<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

	<form action="j_security_check" method="post">
	<h2>Kamran Book Store</h2>
	  <div class="imgcontainer">
	<div class="message">
		<p>${message}</p>
	</div>
    <img src="images/bookstore-logo.jpg" alt="Login Picture" class="avatar">
  </div>
  
	<div class="container">
		<label><b>Username</b></label>
		<input type="text" placeholder="Enter Username" name="j_username" required>
		<label><b>Password</b></label>
		<input type="password" placeholder="Enter Password" name="j_password" required>
		<button type="Submit">Login</button>
		<div class="registerbuttons">
			<a href="registerUser"> Register </a>
			<a href="registerAuthor"> Register as Author </a>
		</div>
		<div class="forgotContainer">
			<span class="psw"><a href="forgotPassword">Forgot password?</a></span>
		</div>
	</div>
	</form>

<br/>
</body>
</html>
