<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="library.jsp" />
<title>Data Supplier</title>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="navbarSupplier.jsp" />
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
                        <h1 class="h3 mb-0 text-gray-800">Data Supplier</h1>
                    </div>
                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
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
<p class="mb-4">Data Supplier yang terdaftar tersedia seperti pada table dibawah.</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<form action="${pageContext.request.contextPath}/supplier/all"
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" name="cari" class="form-control bg-white border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="submit" value="cari" onclick="location.href='${pageContext.request.contextPath}/supplier/all';">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
						</form></h6>
						
                        </div>
                        <div class="card-body">
                            <div class="float-sm-right mb-3">
                            <input type="button" value="Tambah Supplier" class="btn btn-primary mr-2"
							onclick="location.href='${pageContext.request.contextPath}/supplier/add';">
							</div>
							<div class="d-sm-flex align-items-center justify-content-between mb-1r">
	                        <h1 class="h6 mb-0 text-gray-600">${keterangan}</h1>
	                   	</div>
	                   	
                        <div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					    <thead>
					        <tr>
							<th>Kode</th>
							<th>Nama</th>
							<th>No Telp</th>
							<th>Alamat</th>
							<th>Kota</th>
							<th>Email</th>
							<th>Action</th>
					        </tr>
					    </thead>
    					<tbody>
						<c:forEach items="${supplier}" var="s">
							  <tr>
								<td>${s.kodeSupplier}</td>
								<td>${s.namaSupplier}</td>
								<td>${s.telpSupplier} </td>
								<td>${s.alamatSupplier}</td>
								<td>${s.namaKota} </td>
								<td>${s.emailSupplier} </td>
								<td>
									<input type="button" class="btn btn-warning btn-sm" value="Edit" onclick="location.href='${pageContext.request.contextPath}/supplier/edit/${s.kodeSupplier}';">
									<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/supplier/delete/${s.kodeSupplier}';">
								</td>
							  </tr>
						</c:forEach>
    					</tbody>
					</table>
					</div>
					<ul class="pagination justify-content-center">
					<c:forEach var="i" begin="1" end="${total}" >
						<li class="page-item">
						<a href="${pageContext.request.contextPath}
						/supplier/all?page=${i}&cari=${param.cari}" class="page-link" href="#" >${i}
						</a></li>
					</c:forEach>
					</ul>
					    ${penjelasan}
					</div>
                </div>
        	</div>
        	
		<jsp:include page="footer.jsp" />
</body>
</html>