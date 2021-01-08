<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Kota</title>
</head>
<body>

<h1>Data Kota </h1>
	<br>
	<h4>Wellcome Back, ${datauser}</h4>
	<form action="${pageContext.request.contextPath}/kota/page-kota">
			Cari : <input type="text" name=cari>
			<input type="submit" value="cari" class="btn btn-secondary">
			<input type="button" value="Tambah Kota" class="btn btn-secondary"
			onclick="location.href='${pageContext.request.contextPath}/kota/add';">
			${keterangan}
	</form>
	<br>
	<table>
	  <tr>
	    <th>Kode Kota</th>
	    <th>Nama Kota</th>
	    <th>Nama Provinsi</th>
	    <th>Action</th>
	  </tr>
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
	</table>
	
	<br>
	<c:forEach var="i" begin="1" end="${total}">
		<a href="${pageContext.request.contextPath}
		/kota/page-kota?page=${i}&cari=${param.cari}">${i}
		</a>&nbsp 
	</c:forEach>
	<br>
	<br>
	<input type="button" value="Logout" class="btn btn-secondary"
	onclick="location.href='${pageContext.request.contextPath}/user/login';">
	<input type="button" value="Back" class="btn btn-secondary"
	onclick="location.href='${pageContext.request.contextPath}/kota/page-kota';">
	${penjelasan}

</body>
</html>