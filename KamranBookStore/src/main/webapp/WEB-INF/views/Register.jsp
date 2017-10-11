<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<sf:form method="post" action="doRegister" modelAttribute="user">
		First Name <sf:input type="text" path="profile.firstName" /> <br /> 
		Last Name <sf:input type="text" path="profile.lastName" /> <br /> 
		Gender <br /> 
		<sf:radiobutton	path="profile.gender" value="Male" /> Male 
		<sf:radiobutton path="profile.gender" value="Female" /> Female <br /> Profession 
		<sf:select path="profile.profession">
				<sf:option value="Student">Student</sf:option>
				<sf:option value="Self Employed">Self Employed</sf:option>
				<sf:option value="Employed In Private Sector">
				Employed In	Private Sector</sf:option>
			<sf:option value="Employed In Public Sector">Employed In Public
				Sector</sf:option>
		</sf:select> <br /> 
		Username <sf:input type="text" path="username" /> <br /> 
		Password <sf:password path="password" /> <br /> 
		Confirm Password <sf:password path="confirmPassword" /> <br /> 
		Hobbies <br /> <sf:checkbox path="profile.hobbies" value="Sports" />Sports 
		<sf:checkbox path="profile.hobbies" value="Music" />Music 
		<sf:checkbox path="profile.hobbies" value="Playing Games Online" />Playing Games Online 
		<sf:checkbox path="profile.hobbies" value="Sleeping" />Sleeping
		<br /> <input type="submit" value="Register">
	</sf:form>
</body>
</html>




