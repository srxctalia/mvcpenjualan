<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Kota</title>
</head>
<body>

<h1>Data Kota </h1>
	<input type="button" value="Tambah Kota"
	onclick="location.href='${pageContext.request.contextPath}/kota/add';">
	<h3>Wellcome Back, ${datauser}</h3>
	<form action="${pageContext.request.contextPath}/kota/page-kota">
			Cari : <input type="text" name=cari>
			<input type="submit" value="cari">
			${keterangan}
	</form>
	<br>
	<table>
	  <tr>
	    <th>Kode Kota</th>
	    <th>Nama Kota</th>
	    <th>Kode Provinsi</th>
	    <th>Action</th>
	  </tr>
		<c:forEach items="${barang}" var="b">
			<tr>
				<td>${b.kodeBarang}</td>
				<td>${b.namaBarang}</td>
				<td>${b.stokBarang}</td>
				<td>${b.kodeSupplier}</td>
				<td> <input type="button" value="Edit"
				onclick="location.href='${pageContext.request.contextPath}/barang/detail/${b.kodeBarang}';">
				<input type="button" value="Delete"
				onclick="location.href='${pageContext.request.contextPath}/barang/delete/${b.kodeBarang}';">
				</td> 
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<c:forEach var="i" begin="1" end="${total}">
		<a href="${pageContext.request.contextPath}
		/barang/page-barang?page=${i}&cari=${param.cari}">${i}
		</a>&nbsp 
	</c:forEach>
	<br>
	<br>
	<input type="button" value="Logout"
	onclick="location.href='${pageContext.request.contextPath}/user/login';">
	<input type="button" value="Back"
	onclick="location.href='${pageContext.request.contextPath}/barang/page-barang';">
	${penjelasan}

</body>
</html>