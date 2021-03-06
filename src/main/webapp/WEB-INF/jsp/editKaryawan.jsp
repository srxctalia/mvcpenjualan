<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="library.jsp" />
<!-- Style for show password -->
<style type="text/css">
.field-icon {
  float: right;
  margin-right: 20px;
  margin-left: -25px;
  margin-top: -25px;
  position: relative;
  z-index: 2;
}
</style>
<title>Edit Data Karyawan</title>
</head>
<body id="page-top">

 <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="navbarKaryawan.jsp" />
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    
                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-1r">
                        <h1 class="h3 mb-0 text-gray-800">Edit Karyawan</h1>
                    </div>
                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${usr}</span>
                                <img class="img-profile rounded-circle"
                                    src="${pageContext.request.contextPath}/static/css/undraw_profile.svg">
                                    <i class="fas fas fa-stream fa-sm fa-fw ml-2 text-gray-400"></i>
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                               <a class="dropdown-item" href="#">
                                	<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    ${level}
                                </a>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->
                
			<div class="container-fluid">
			<!-- Page Heading -->
			<p class="mb-4">Berikut adalah data pada form berikut, update data yang diperlukan.</p>

				<!-- DataTables Example -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<div class="container">
							<c:set var="cek"  value="${stat}"/>
							<c:if test="${cek == 1}">	 
								<div class="alert alert-danger alert-dismissible fade show">
								    <button type="button" class="close" data-dismiss="alert">&times;</button>
								    <h1 class="h6 mb-0 text-gray-600">${formatPassword}</h1>
							  	</div>
		  					</c:if>
						
							<f:form action="${pageContext.request.contextPath}/karyawan/save" modelAttribute="dto">
								<div class="mb-3">
							    		<h1 class="h6 mb-2 text-gray-900">Kode Karyawan</h1>
										<f:input type="text" path="kodeKaryawan" class="form-control" readonly="true" oninput="let p=this.selectionStart;this.value=this.value.toUpperCase();this.setSelectionRange(p, p);"/>
										<f:errors path="kodeKaryawan" class="h7 mb-0 text-danger"/>
							  		</div>
							  		<div class="mb-3">
							    		<h1 class="h6 mb-2 text-gray-900">Nama Karyawan</h1>
										<f:input type="text" class="form-control" path="namaKaryawan"/>
										<f:errors path="namaKaryawan" class="h7 mb-0 text-danger"/>
							  		</div>
							  		<div class="mb-3">
							    		<h1 class="h6 mb-2 text-gray-900">Username</h1>
										<f:input type="text" class="form-control" path="username"/>
										<f:errors path="username" class="h7 mb-0 text-danger"/>
							  		</div>
							  		<div class="mb-3">
							    		<h1 class="h6 mb-2 text-gray-900">Password</h1>
										<f:input type="password" class="form-control" path="password"/>
										<span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
										<f:errors path="password" class="h7 mb-0 text-danger"/>
							  		</div>
							  		<div class="mb-3">
							    		<h1 class="h6 mb-2 text-gray-900">Confirm Password</h1>
										<input type="password" class="form-control" placeholder="Konfirmasi password" id="confirm_password" required="true"/>
						  				<span toggle="#confirm_password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
						  			</div>
							  		<script type="text/javascript">
								  		var password = document.getElementById("password")
									  	  , confirm_password = document.getElementById("confirm_password");
			
									  	function validatePassword(){
									  	  if(password.value != confirm_password.value) {
									  	    confirm_password.setCustomValidity("Passwords Don't Match");
									  	  } else {
									  	    confirm_password.setCustomValidity('');
									  	  }
									  	}
			
									  	password.onchange = validatePassword;
									  	confirm_password.onkeyup = validatePassword;
							  		</script>
							  		<div class="mb-3">
									      <h1 class="h6 mb-2 text-gray-900">Level</h1>
									      <f:select path="level" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
									        <option value="1">Admin</option>
	    									<option value="2">Staff</option>			      
									      </f:select>	
							  		</div>
								<input type="submit" value="Save" class="btn btn-primary">
								<input type="button" class="btn btn-secondary" value="Cancel" 
								onclick="location.href='${pageContext.request.contextPath}/karyawan/pagekaryawan';"><br><br>
							</f:form>
						</div>

					  <!-- Optional JavaScript; choose one of the two! -->
					
					    <!-- Option 1: Bootstrap Bundle with Popper -->
					    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
					
					    <!-- Option 2: Separate Popper and Bootstrap JS -->
					    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
					    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>											
                        </div>
                </div>
        	</div>
        	
		<jsp:include page="footer.jsp" />
		
		<!-- JS for Show Password -->
		<script type="text/javascript">
			$(".toggle-password").click(function() {

			  $(this).toggleClass("fa-eye fa-eye-slash");
			  var input = $($(this).attr("toggle"));
			  if (input.attr("type") == "password") {
			    input.attr("type", "text");
			  } else {
			    input.attr("type", "password");
			  }
			});
		</script>
</body>
</html>