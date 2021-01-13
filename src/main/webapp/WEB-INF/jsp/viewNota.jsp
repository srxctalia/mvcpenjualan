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
<title>View</title>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
  		<jsp:include page="navbarTransaksi.jsp" />
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
                        <h1 class="h3 mb-0 text-gray-800">View : ${dtoH.noNota}</h1>
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
<p class="mb-4">Data Transaksi tersedia seperti pada table dibawah.</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
						<h1 class="h5 mb-0 text-gray-800">Header Transaksi</h1></h6>
                        </div>
                        
                        <div class="card-body">
                            <div class="mb-3">
                        <div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					    <thead>
					        <tr>
							<th>No Nota</th>
							<th>Tanggal Transaksi</th>
							<th>Harga Total</th>
							<th>Customer</th>
							<th>Karyawan</th>
					        </tr>
					    </thead>
    					<tbody>
							  <tr>
								<td>${dtoH.noNota}</td>
								<td><fmt:formatDate pattern="dd MMMM yyyy" value="${dtoH.tanggalTransaksi}"/></td>
								<td>${dtoH.hargaTotal}</td>
								<td>${dtoH.namaCustomer}</td>
								<td>${dtoH.namaKaryawan} </td>
							  </tr>
    					</tbody>
					</table>
					</div>
					</div>
                </div> 
        	</div>
        	
        				<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
						<h1 class="h5 mb-0 text-gray-800">Detail Transaksi</h1></h6>			   
                        </div>
        	                <div class="card-body">
                            <div class="mb-3">
                        <div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					    <thead>
					        <tr>
							<th>Kode Detail</th>
							<th>Quantity</th>
							<th>SubTotal</th>
							<th>Diskon</th>
							<th>Harga Satuan</th>
							<th>Barang</th>
					        </tr>
					    </thead>
    					<tbody>
							<c:forEach items="${det}" var="d">
							  <tr>
								<td>${d.kodeDetail}</td>
								<td>${d.qty}</td>
								<td>${d.subtotal}</td>
								<td>${d.diskon}</td>
								<td>${d.hargaSatuan}</td>
								<td>${d.namaBarang}</td>
							  </tr>
							  </c:forEach>
    					</tbody>
					</table>
					</div>
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
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/karyawan/login">Logout</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>