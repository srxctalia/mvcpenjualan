<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">   
<!-- UNTUK ICON -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<!-- BOOTSTRAP 4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Supplier</title>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/dashboard/main">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-hand-holding-usd"></i>
                </div>
                <div class="sidebar-brand-text mx-3">MVC Penjualan<sup>5</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/dashboard/main">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-sitemap fa-cog"></i>
                    <span>Masters</span>
                </a>
                <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">List Master:</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/customer/all">Customer</a>
                        <a class="collapse-item active" href="${pageContext.request.contextPath}/supplier/all">Supplier</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/barang/page-barang">Barang</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/provinsi/pageprovinsi">Provinsi</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/kota/page-kota">Kota</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/karyawan/pagekaryawan">Karyawan</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-tags"></i>
                    <span>Transaksi</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="${pageContext.request.contextPath}/transaksi/all">List Transaksi</a>
                    </div>
                </div>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
        </ul>
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
        	
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>