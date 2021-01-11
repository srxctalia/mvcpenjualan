<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Edit Supplier</title>

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
            <li class="nav-item active">
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
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-sitemap fa-cog"></i>
                    <span>Masters</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">List Master:</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/customer/all">Customer</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/supplier/all">Supplier</a>
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
                        <h1 class="h3 mb-0 text-gray-800">Edit Supplier</h1>
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
<p class="mb-4">Berikut adalah data pada form berikut, update data yang diperlukan.</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<div class="container">

	<f:form action="${pageContext.request.contextPath}/supplier/save" modelAttribute="dto">
		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Kode Supplier</h1>
				<f:input type="text" path="kodeSupplier" class="form-control" readonly="true"/>
				<f:errors path="kodeSupplier" class="h7 mb-0 text-gray-600"/>
	  		</div>
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Nama Supplier</h1>
				<f:input type="text" class="form-control" path="namaSupplier" />
				<f:errors path="namaSupplier" class="h7 mb-0 text-gray-600"/>
	  		</div>
			<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Email</h1>
				<f:input type="email" class="form-control" path="emailSupplier"/>
				<f:errors path="emailSupplier" class="h7 mb-0 text-gray-600"/>
	  		</div>
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">No Telepon</h1>
				<f:input type="text" class="form-control" path="telpSupplier" />
				<f:errors path="telpSupplier" class="h7 mb-0 text-gray-600"/>
	  		</div>	
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Alamat</h1>
				<f:textarea class="form-control" path="alamatSupplier"/>
				<f:errors path="alamatSupplier" class="h7 mb-0 text-gray-600"/>
	  		</div>			
	  		<div class="mb-3">
			      <h1 class="h6 mb-2 text-gray-900">Kota</h1>
			      <f:select path="kodeKota" class="custom-select mr-sm-2" >
			        <option disabled selected value> -- select an option -- </option>
			        <c:forEach items="${kota}" var="k">
				    <f:option value="${k.kodeKota}" label="${k.namaKota }"/>
			      	</c:forEach>
			      </f:select>
			      <f:errors path="kodeKota" class="h7 mb-0 text-gray-600"/>		
	  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/supplier/all';"><br><br>
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