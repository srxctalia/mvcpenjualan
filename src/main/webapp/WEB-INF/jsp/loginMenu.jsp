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

	<h2>Login Form</h2>
	
	<f:form modelAttribute="loginKaryawan" action="${pageContext.request.contextPath}/karyawan/id">
		  <div class="container">
		    <h5 id="salah" style="color: Tomato">${error}</h5>
		    <label for="uname"><b>Username : </b></label>
		    <f:input path="username" type="text" id="uname" name="uname" required="true" placeholder="Enter Username"/>
			<f:errors path="username"/>
		    <label for="psw"><b>Password : </b></label>
		    <f:input path="password" type="text" id="psw" name="psw" required="true" placeholder="Enter Password"/>
		    <f:errors path="password"/>    
		    <button type="submit">Login</button>
		  </div>
		
		  <div class="container" style="background-color:#f1f1f1">
	    <button type="button" class="cancelbtn">Cancel</button>
	  </div>
	</f:form>
	${validasi}

</body>
</html>