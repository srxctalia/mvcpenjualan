<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Edit Transaksi</title>

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
            <li class="nav-item active">
                <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-tags"></i>
                    <span>Transaksi</span>
                </a>
                <div id="collapseUtilities" class="collapse show" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item active" href="${pageContext.request.contextPath}/transaksi/all">List Transaksi</a>
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
                        <h1 class="h3 mb-0 text-gray-800">Edit Transaksi</h1>
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
			<p class="mb-4">Silahkan isi data pada form berikut</p>

				<!-- DataTables Example -->  
						<div class="container">
							<c:set var="cek"  value="${stat}"/>  <!-- CHEK SINI AMA CTL ADD NYA -->
							<c:if test="${cek == 1}">	 
								<div class="alert alert-danger alert-dismissible fade show">
								    <button type="button" class="close" data-dismiss="alert">&times;</button>
								    <h1 class="h6 mb-0 text-gray-600">${error}</h1>
							  	</div>
		  					</c:if>
		  					
							<f:form action="${pageContext.request.contextPath}/transaksi/save" modelAttribute="dtoH">
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
						<h1 class="h5 mb-0 text-gray-800">Header Transaksi</h1></h6>
                        </div>
                        <div class="card-body">
                        <div class="row">
                        	<div class="col col-md-4">
                        		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">No Nota</h1>
									<f:input type="text" path="noNota" readonly="true" class="form-control" placeholder="Masukan No Nota${kodeTerakhir}" oninput="let p=this.selectionStart;this.value=this.value.toUpperCase();this.setSelectionRange(p, p);"/>
									<f:errors path="noNota" class="h7 mb-0 text-danger"/>
						  		</div>
						  		<div class="mb-3">
								      <h1 class="h6 mb-2 text-gray-900">Customer</h1>
								      <f:select path="kodeCustomer" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
								        <option disabled selected value> -- select an option -- </option>
								        <c:forEach items="${customer}" var="c">
									        <f:option value="${c.kodeCustomer}" label="${c.namaCustomer}"></f:option>			      
								      	</c:forEach>
								      </f:select>
								      <f:errors path="kodeCustomer" class="h7 mb-0 text-danger"/>		
						  		</div>
                        	</div>
                        	<div class="col col-md-4">
                        		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Tanggal Transaksi</h1>
									<f:input type="date" class="form-control" path="tanggalTransaksi" placeholder="Masukan tanggal transaksi" readonly="true" />
									<f:errors path="tanggalTransaksi" class="h7 mb-0 text-danger"/>
						  		</div>
						  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Karyawan</h1>
									<f:input type="text" class="form-control" path="namaKaryawan" placeholder="Masukan karyawan" readonly="true" />
									<f:errors path="namaKaryawan" class="h7 mb-0 text-danger"/>
						  		</div>
                        	</div>
                        </div>
                </div> 
        	</div>
        	
        				<div class="card shadow mb-4">
						<div class="card-header py-3">
						<div class="float-sm-right">						
						<input type="button" value="Tambah Detail" class="btn btn-primary"
						onclick="javascript:addDetail();">
						</div>
						<div class="d-sm-flex align-items-center justify-content-between">
	                    <h1 class="h5 mt-2 text-gray-800">Detail Transaksi</h1>
	                   	</div>
                        </div>
        	                <div class="card-body">
                            <div class="mb-3">
						  		<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								<thead>
								  <tr>
								    <th>Kode Detail</th>
								    <th>Nama Barang</th>
								    <th>Harga Satuan</th>
								    <th>Qty</th>
								    <th>Diskon</th>
								    <th>Sub Total</th>
								    <th>Action</th>
								  </tr>
								</thead>
								<tbody>
								<c:forEach items="${dtoH.detailTransaksi}" var="d">
									  <tr style="font-weight:normal">
									    <td>${d.kodeDetail}</td>
									    <td>${d.namaBarang}</td>
									    <td>${d.hargaSatuan}</td>
									    <td>${d.qty}</td>
									    <td>${d.diskon} </td>
									    <td><a>Rp. </a><fmt:formatNumber value = "${d.subtotal}" groupingUsed="true" /></td>
									    <td>
									    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/transaksi/deleteDetail/${d.kodeDetail}';">
									    </td>
									  </tr>
								</c:forEach>
								</tbody>
								</table>
						  		</div>
					</div>
                </div>
                </div>
                
        				<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
						<h1 class="h5 mb-0 text-gray-800">Result</h1></h6>	
                        </div>
        	                <div class="card-body"> 
        	                <div class="row mx-5 my-3">
	        	                <div class="col col-md-5">
	                            	<div class="mb-3">
							    	<h1 class="h6 mb-2 text-gray-900">Global Diskon (%)</h1>
									<f:input type="text" class="form-control" path="globalDiskon" id="gdiskon" 
									onchange="ttl.value= ${total}-${total}*gdiskon.value/100"/>
							  		<f:errors path="globalDiskon" class="h7 mb-0 text-danger"/>
									</div>
	        	                </div>
	        	                 <div class="col col-md-5">
									<div class="mb-3">
									<h1 class="h6 mb-2 text-gray-900">Total Harga</h1>
									<f:input class="form-control" type="number" id="ttl" path="hargaTotal" readonly="true"/> 
									</div>
	        	                </div>
        	                </div>    	                	
					</div>
                </div>
                <div class="mb-3">
								<input type="submit" value="Save" class="btn btn-primary">
								<input type="button" class="btn btn-secondary" value="Cancel" 
								onclick="location.href='${pageContext.request.contextPath}/transaksi/all';">
								</div>
                </div>                
		</f:form>        	
		<jsp:include page="footer.jsp" />
		<script language="javascript">
			function addDetail(){
				document.forms[0].action = '${pageContext.request.contextPath}/transaksi/addDetail';
				document.forms[0].submit();
			}
		</script>	
</body>
</html>