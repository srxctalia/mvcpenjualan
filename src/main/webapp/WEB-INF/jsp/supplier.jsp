<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<!-- My CSS -->
	<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">  -->

<title>Data Supplier</title>
</head>
<body>
<div align="right">
	<h3>Welcome, ${username}</h3>
</div>
<h2 align="center" class="judul">Data Supplier</h2><br>
<div class="container">
	<div class="row">
		<div align="left">
			<input type="button" class="btn btn-primary" value="Tambah" 
			onclick="location.href='${pageContext.request.contextPath}/supplier/add';">
		</div>
		<div align="right">
			<form action="${pageContext.request.contextPath}/supplier/all">
				<input type="text" name=cari class="form-control">
				<input class="btn btn-info" type="submit" value="cari">
			</form>
		</div>
	</div>
	<br>
	<table class="table table-hover table-responsive">
		<thead>
		  <tr class="table-success">
		    <th>Kode</th>
		    <th>Nama</th>
		    <th>No Telp</th>
		    <th>Alamat</th>
		    <th>Kota</th>
		    <th>Email</th>
		    <th>Action</th>
		  </tr>
		</thead>
	<c:forEach items="${supplier}" var="s">
		  <tr>
		    <td>${s.kodeSupplier}</td>
		    <td>${s.namaSupplier}</td>
		    <td>${s.telpSupplier} </td>
		    <td>${s.alamatSupplier}</td>
		    <td>${s.namaKota} </td>
		    <td>${s.emailSupplier} </td>
		    <td>
		    	<input type="button" class="btn btn-warning btn-sm" value="Edit" onclick="location.href='${pageContext.request.contextPath}/supplier/edit/${c.kodeSupplier}';">
		    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/supplier/delete/${c.kodeSupplier}';">
		    </td>
		  </tr>
	</c:forEach>
	</table>
	<div class="mx-auto" style="width: 200px;">
			<nav aria-label="Page navigation example">
		  		<ul class="pagination">
			    	<li class="page-item">
			      		<a class="page-link" href="#" aria-label="Previous">
			        		<span aria-hidden="true">&laquo;</span>
			      		</a>
			    	</li>
					<c:forEach var="i" begin="1" end="${total}">
			    		<li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/supplier/all?page=${i}&cari=${param.cari}">${i}</a></li>
					</c:forEach>
			    	<li class="page-item">
			      		<a class="page-link" href="#" aria-label="Next">
			        		<span aria-hidden="true">&raquo;</span>
			      		</a>
			    	</li>
		  		</ul>
			</nav>
	</div>
</div>
	<!-- <a href="${ pageContext.request.contextPath }/penduduk/pagependuduk?page=${i}&cari=${param.cari}">${i}</a>&nbsp  -->

 <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
  
</body>
</html>