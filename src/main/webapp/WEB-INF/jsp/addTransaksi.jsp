<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Add Transaksi</title>

<<<<<<< HEAD
</head>
<body>
<div class="container">
	<h2>Add Transaksi</h2>
	<f:form action="${pageContext.request.contextPath}/transaksi/save" modelAttribute="dtoH">
		<div class="mb-3">
    		<label for="exampleInputEmail1" class="form-label">No Nota</label>
			<f:input type="text" path="noNota" class="form-control"/>
				<c:if test="${validasi == ''}">
					<div class="alert alert-danger error-box" role="alert">
					<f:errors path="noNota" />
					<p>${validasi}</p>
					</div>
				</c:if>
  		</div>
  		<div class="mb-3">
    		<label for="exampleInputPassword1" class="form-label">Tanggal Transaksi</label>
			<f:input type="date" class="form-control" path="tanggalTransaksi" readonly="true"/>
			<f:errors path="tanggalTransaksi"/>
  		</div>
  		<div class="mb-3">
    		<label for="exampleInputPassword1" class="form-label">Customer</label>
				<f:select path="kodeCustomer" class="custom-select mr-sm-2" id="inlineFormCustomSelect" placeholder="Pilih Kota">
			        <c:forEach items="${customer}" var="c">
				        <f:option value="${c.kodeCustomer}" label="${c.namaCustomer}"></f:option>			      
			      	</c:forEach>			      
		      	</f:select>
				<f:errors path="kodeCustomer"/>
  		</div>
  		<div class="mb-3">
  			<label for="exampleInputPassword1" class="form-label">Karyawan</label>
  			<f:input type="text" class="form-control" path="kodeKaryawan" readonly="true"/>
			<f:errors path="kodeKaryawan"/>
  		</div>
  		<div class="mb-3">
  			<label for="exampleInputPassword1" class="form-label">Global Diskon%</label>
  			<f:input type="text" class="form-control" path="globalDiskon"/>
			<f:errors path="globalDiskon"/>
  		</div>
  		<div class="mb-3">
  			<h4>Detail</h4>
  			<div class="row">
  				<div class="col">
					<input type="button" class="btn btn-primary" value="Tambah" 
						onclick="javascript:addDetail();">
  				</div>
			</div>
  		</div>
  		<div class="mb-3">
			<table class="table table-hover table-responsive">
		<thead>
		  <tr class="table-success">
		    <th>Kode Detail</th>
		    <th>Nama Barang</th>
		    <th>Harga Satuan</th>
		    <th>Qty</th>
		    <th>Diskon</th>
		    <th>Sub Total</th>
		    <th>Action</th>
		  </tr>
		</thead>
	<c:forEach items="${dtoH.detailTransaksi}" var="d">
		  <tr>
		    <td>${d.kodeDetail}</td>
		    <td>${d.namaBarang}</td>
		    <td>${d.hargaSatuan}</td>
		    <td>${d.qty}</td>
		    <td>${d.diskon} </td>
		    <td>${d.subtotal} </td>
		    <td>
		    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/transaksi/deleteDetail/${d.kodeDetail}';">
		    </td>
		  </tr>
	</c:forEach>
	</table>
  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/transaksi/all';"><br><br>
	</f:form>
</div>
	
	<script language="javascript">
		function addDetail(){
			document.forms[0].action = '${pageContext.request.contextPath}/transaksi/addDetail';
			document.forms[0].submit();
		}
	</script>

  <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
=======
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
								    <h1 class="h6 mb-0 text-gray-600">${error}</h1>
							  	</div>
		  					</c:if>
		  					
							<f:form action="${pageContext.request.contextPath}/transaksi/save" modelAttribute="dtoH">
								<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">No Nota</h1>
									<f:input type="text" path="noNota" class="form-control" placeholder="Masukan No Nota${kodeTerakhir}" oninput="let p=this.selectionStart;this.value=this.value.toUpperCase();this.setSelectionRange(p, p);"/>
									<f:errors path="noNota" class="h7 mb-0 text-gray-600"/>
						  		</div>
					  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Tanggal Transaksi</h1>
									<f:input type="date" class="form-control" path="tanggalTransaksi" placeholder="Masukan tanggal transaksi" readonly="true" />
									<f:errors path="tanggalTransaksi" class="h7 mb-0 text-gray-600"/>
						  		</div>
							  		
							  	<div class="mb-3">
								      <h1 class="h6 mb-2 text-gray-900">Customer</h1>
								      <f:select path="kodeCustomer" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
								        <option disabled selected value> -- select an option -- </option>
								        <c:forEach items="${customer}" var="c">
									        <f:option value="${c.kodeCustomer}" label="${c.namaCustomer}"></f:option>			      
								      	</c:forEach>
								      </f:select>
								      <f:errors path="kodeCustomer" class="h7 mb-0 text-gray-600"/>		
						  		</div>
							  	
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Karyawan</h1>
									<f:input type="text" class="form-control" path="kodeKaryawan" placeholder="Masukan karyawan" readonly="true" />
									<f:errors path="kodeKaryawan" class="h7 mb-0 text-gray-600"/>
						  		</div>
						  		
						  		<div class="mb-3">
						    		<h1 class="h6 mb-2 text-gray-900">Global Diskon (%)</h1>
									<f:input type="text" class="form-control" path="globalDiskon" placeholder="Masukan Global Diskon"/>
						  			<f:errors path="globalDiskon" class="h7 mb-0 text-gray-600"/>
						  		</div>
						  		
<!-- 								<input type="submit" value="Save" class="btn btn-primary"> -->
<!-- 								<input type="button" class="btn btn-secondary" value="Cancel"  -->
<%-- 								onclick="location.href='${pageContext.request.contextPath}/transaksi/all';"><br><br> --%>
								
								
						  		<div class="card-body">
		                        	<h3 style="text-align:center">Detail</h3>
		                            <div class="float-sm-right mb-3">
		                            <input type="button" value="Tambah Detail" class="btn btn-primary mr-2"
									onclick="javascript:addDetail();">
									</div>
			                   	</div>
						  		
						  		<!-- Detail table area -->
						  		<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								<thead>
								  <tr class="table-success">
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
									    <td>${d.subtotal} </td>
									    <td>
									    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/transaksi/deleteDetail/${d.kodeDetail}';">
									    </td>
									  </tr>
								</c:forEach>
								</tbody>
								</table>
						  		</div>
								<input type="submit" value="Save" class="btn btn-primary">
								<input type="button" class="btn btn-secondary" value="Cancel" 
								onclick="location.href='${pageContext.request.contextPath}/transaksi/all';"><br><br>
														
							</f:form>
						</div>
						
						<script language="javascript">
							function addDetail(){
								document.forms[0].action = '${pageContext.request.contextPath}/transaksi/addDetail';
								document.forms[0].submit();
							}
						</script>		

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
>>>>>>> 3babd9707daef98aba1152bf3b9bb91dbda035e7

</body>
</html>