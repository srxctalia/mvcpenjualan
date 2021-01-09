<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Karyawan</title>
</head>
<body>
<div align="left">
		<h3 style="color: gray;">Welcome, ${userID}!</h3>
	</div>

	<h2 style="text-align: center;">Table Data Penduduk</h2>
	
	<form action="${pageContext.request.contextPath}/karyawan/pagekaryawan">
		<input type="text" name=cari style="width:30%; height: 30px; background-color: #f1f1f1;"/>
		<input type=submit value="Cari" style="width: 60px; height: 37px; background-color: lightgrey;" >
	</form><br><br>

	<table>
	<tr>
		<th>Kode</th>
		<th>Nama</th>
		<th>Password</th>
		<th>Username</th>
		<th>Action</th>
	</tr>

	<tr>
	<c:forEach var="listKaryawan" items="${karyawan}">
		<tr>
			<td>${listKaryawan.kodeKaryawan}</td>
			<td>${listKaryawan.namaKaryawan}</td>
			<td>${listKaryawan.username}</td>
			<td>${listKaryawan.password}</td>
			<td style="width:18%">
				<input type="button" value="Edit Data" style="background-color:MediumSeaGreen;" onclick="location.href='${pageContext.request.contextPath}/karyawan/findone/${listKaryawan.kodeKaryawan}';" />
				<input type="button" value="Delete Data" style="background-color:Tomato;" onclick="location.href='${pageContext.request.contextPath}/karyawan/delete/${listKaryawan.kodeKaryawan}';" />
			</td>
		</tr>
	</c:forEach>
<%-- 	<c:forEach var="karyawan" items="${seorang}"> --%>
		<tr>
			<td>${seorang.kodeKaryawan}</td>
			<td>${seorang.namaKaryawan}</td>
			<td>${seorang.username}</td>
			<td>${seorang.password}</td>
			<td style="width:18%">
				<input type="button" value="Edit Data" style="background-color:MediumSeaGreen;" onclick="location.href='${pageContext.request.contextPath}/karyawan/findone/${karyawan.kodeKaryawan}';" />
				<input type="button" value="Delete Data" style="background-color:Tomato;" onclick="location.href='${pageContext.request.contextPath}/karyawan/delete/${karyawan.kodeKaryawan}';" />
			</td>
		</tr>
<%-- 	</c:forEach> --%>
	</table>
	<input type="button" value="Tambah Data" style="background-color:DodgerBlue; width:100%;" onclick="location.href='${pageContext.request.contextPath}/karyawan/add';" /><br>

	<c:forEach var="i" begin="1" end="${total}">
		<a href="${pageContext.request.contextPath}/karyawan/pagekaryawan?page=${i}&cari=${param.cari}">${i}</a>&nbsp <%--buat spasi--%>
	</c:forEach>
	<input type="button" value="Logout" onclick="location.href='${pageContext.request.contextPath}/karyawan/login';" />
	
</body>
</html>