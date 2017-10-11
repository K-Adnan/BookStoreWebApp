<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="PageDirectives.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello ${username}
<h2> ADD NEW EMPLOYEE </h2>
<sf:form method="post" action="processAddEmployee" modelAttribute="employee">
	Employee ID : <sf:input path="id"/>
	Employee Name : <sf:input path="name"/>
	Annual Salary : <sf:input path="salary"/>
	<sf:button value="Add Employee" />
</sf:form>
</body>
</html>