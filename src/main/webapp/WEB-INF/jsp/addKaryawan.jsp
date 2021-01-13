<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="library.jsp" />
<title>Tambah Data Karyawan</title>
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
                        <h1 class="h3 mb-0 text-gray-800">Add Karyawan</h1>
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
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
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
			<p class="mb-4">Silahkan isi data pada form berikut</p>

				<!-- DataTables Example -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<div class="container">
							<c:set var="cek"  value="${stat}"/>  <!-- CHEK SINI AMA CTL ADD NYA -->
							<c:if test="${cek == 1}">	 
								<div class="alert alert-danger alert-dismissible fade show">
								    <button type="button" class="close" data-dismiss="alert">&times;</button>
								    <h1 class="h6 mb-0 text-gray-600">${valid}</h1>
								    <h1 class="h6 mb-0 text-gray-600">${formatPassword}</h1>
							  	</div>
		  					</c:if>
		  					
							<f:form action="${pageContext.request.contextPath}/karyawan/save" modelAttribute="dto">
								<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Kode Karyawan</h1>
									<f:input type="text" path="kodeKaryawan" class="form-control" placeholder="Masukan kode Karyawan${kodeTerakhir}" oninput="let p=this.selectionStart;this.value=this.value.toUpperCase();this.setSelectionRange(p, p);"/>
									<f:errors path="kodeKaryawan" class="h7 mb-0 text-gray-600"/>
						  		</div>
					  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Nama Karyawan</h1>
									<f:input type="text" class="form-control" path="namaKaryawan" placeholder="Masukan nama Karyawan"/>
									<f:errors path="namaKaryawan" class="h7 mb-0 text-gray-600"/>
						  		</div>
							  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Username</h1>
									<f:input type="text" class="form-control" path="username" placeholder="Masukan username"/>
									<f:errors path="username" class="h7 mb-0 text-gray-600"/>
						  		</div>
						  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Password</h1>
									<f:input type="text" class="form-control" path="password" placeholder="Masukan password"/>
						  			<f:errors path="password" class="h7 mb-0 text-gray-600"/>
						  		</div>
						  		
						  		<div class="mb-3">
								      <h1 class="h6 mb-2 text-gray-900">Level</h1>
								      <f:select path="level" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
								        <option disabled selected value> -- select an option -- </option>
								        <option value="1">Admin</option>
    									<option value="2">Staff</option>
								      </f:select>
								      <f:errors path="level" class="h7 mb-0 text-gray-600"/>		
						  		</div>
						  		
								<input type="submit" value="Save" class="btn btn-primary">
								<input type="button" class="btn btn-secondary" value="Cancel" 
								onclick="location.href='${pageContext.request.contextPath}/karyawan/pagekaryawan';"><br><br>
							</f:form>
						</div>
					<jsp:include page="footer.jsp" />
</body>
</html>