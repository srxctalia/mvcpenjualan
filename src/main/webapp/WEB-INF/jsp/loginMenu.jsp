<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Menu</title>
</head>
<body>

	<h2>Login Menu</h2>
	<f:form action="${pageContext.request.contextPath}/user/check"
	modelAttribute="dto">
		<table>
			<tr>
				<td>User ID</td>
				<td>:</td>
				<td><f:input path="username" /></td>
				<td><f:errors path="username" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td>:</td>
				<td><input type="password" name="password" value=""/></td>
				<td><f:errors path="password" /></td>
			</tr>
			<tr>
			<td><input type="submit" value="Masuk"></td>
			</tr>
		</table>
	</f:form>
	${validasi}

</body>
</html>