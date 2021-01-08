<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Kota</title>
</head>
<body>
<br>
<!-- Begin Page Content -->
<div class="container-fluid">

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Data Kota</h1>
<p class="mb-4">Data Kota tersedia seperti pada table dibawah.</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">				   
        				<form action="${pageContext.request.contextPath}/kota/page-kota">
						Cari : <input type="text" name=cari>
						<input type="submit" value="cari" class="btn btn-secondary" >
						<input type="button" value="Tambah Kota" class="btn btn-secondary"
						onclick="location.href='${pageContext.request.contextPath}/kota/add';">
						${keterangan}
						</form></h6>
                        </div>
                        <div class="card-body">
                        <div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					    <thead>
					        <tr>
						    <th>Kode Kota</th>
						    <th>Nama Kota</th>
						    <th>Nama Provinsi</th>
						    <th>Action</th>
					        </tr>
					    </thead>
    					<tbody>
						<c:forEach items="${kota}" var="k">
						<tr>
						<td>${k.kodeKota}</td>
						<td>${k.namaKota}</td>
						<td>${k.namaProvinsi}</td>
						<td> <input type="button" value="Edit" class="btn btn-secondary"
						onclick="location.href='${pageContext.request.contextPath}/kota/detail/${k.kodeKota}';">
						<input type="button" value="Delete" class="btn btn-secondary"
						onclick="location.href='${pageContext.request.contextPath}/kota/delete/${k.kodeKota}';">
						</td> 
						</tr>
						</c:forEach>
    					</tbody>
					</table>
					</div>
					<ul class="pagination">
					<c:forEach var="i" begin="1" end="${total}" >
						<li class="page-item">
						<a href="${pageContext.request.contextPath}
						/kota/page-kota?page=${i}&cari=${param.cari}" class="page-link" href="#" >${i}
						</a></li>
					</c:forEach>
					</ul>
					    ${penjelasan}
					</div>
                	</div>
                		<input type="button" value="Logout" class="btn btn-secondary"
						onclick="location.href='${pageContext.request.contextPath}/user/login';">
						<input type="button" value="Back" class="btn btn-secondary"
						onclick="location.href='${pageContext.request.contextPath}/kota/page-kota';">
						<br>
            	
        	</div>

</body>
</html>