<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/static/login/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/select2/select2.min.css">	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login Menu</title>
</head>
<body>
<div class="limiter">
		<div class="container-login100" style="background-image: url('${pageContext.request.contextPath}/static/login/images/bg-01.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-50 p-b-30">
				<f:form class="login100-form validate-form" action="${pageContext.request.contextPath}/karyawan/id" modelAttribute="loginKaryawan">
					<span class="login100-form-title p-b-25">
						Login
					</span>

					<c:set var="cek"  value="${stat}"/>
					<c:if test="${cek == 1}">	 
						<div class="alert alert-danger alert-dismissible fade show">
						    <button type="button" class="close" data-dismiss="alert">&times;</button>
						    <h1 class="h6 mb-0 text-gray-600">${error}</h1>
					  	</div>
  					</c:if>

					<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is required">
						<span class="label-input100">Username</span>
						<f:input class="input100" type="text" name="username" placeholder="Type your username" 
						path="username" required="true" id='username'/>
						<span class="focus-input100" data-symbol="&#xf206;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<span class="label-input100">Password</span>
						<f:input class="input100" type="password" name="pass" placeholder="Type your password"
						path="password" required="true" id="myInput"/>
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					
					<div class="text-left p-l-15 p-t-17 p-b-29">
					<input type="checkbox" onclick="myFunction()">&nbsp;&nbsp;&nbsp; Show Password
					</div>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" type="submit" value="Login">
								Login
							</button>
						</div>
					</div>
					
					<div class="txt1 text-center p-t-30 p-b-15">
						<span>
							MVC Penjualan Kelompok 5 
						</span>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	
	<script>
		function myFunction() {
		  var x = document.getElementById("myInput");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
		}
	</script>

</body>
</html>

