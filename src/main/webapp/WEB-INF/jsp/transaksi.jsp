<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Penjualan</title>
</head>
<body>
	<div align="right">
	<h3>Welcome, ${usr}</h3>
</div>
<h2 align="center" class="judul">Data Penjualan</h2><br>
<div class="container">
	<h3>${cek}</h3>
	<div class="row">
		<div align="left">
			<input type="button" class="btn btn-primary" value="Tambah" 
			onclick="location.href='${pageContext.request.contextPath}/transaksi/add';">
		</div>
		<div align="right">
			<form action="${pageContext.request.contextPath}/transaksi/all">
				<input type="text" name=cari class="form-control">
				<input class="btn btn-info" type="submit" value="cari">
			</form>
		</div>
	</div>
	<br>
	<table class="table table-hover table-responsive">
		<thead>
		  <tr class="table-success">
		    <th>No Nota</th>
		    <th>Tanggal Transaksi</th>
		    <th>Harga Total</th>
		    <th>Customer</th>
		    <th>Karyawan</th>
		    <th>Action</th>
		  </tr>
		</thead>
	<c:forEach items="${transaksi}" var="t">
		  <tr>
		    <td>${t.noNota}</td>
		    <td><fmt:formatDate pattern="dd MMMM yyyy" value="${t.tanggalTransaksi}"/></td>
		    <td>${t.hargaTotal}</td>
		    <td>${t.namaCustomer}</td>
		    <td>${t.namaKaryawan} </td>
		    <td>
		    	<input type="button" class="btn btn-warning btn-sm" value="Edit" onclick="location.href='${pageContext.request.contextPath}/transaksi/edit/${t.noNota}';">
		    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/transaksi/delete/${t.noNota}';">
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
			    		<li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/transaksi/all?page=${i}&cari=${param.cari}">${i}</a></li>
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
	
</body>
</html>