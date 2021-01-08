<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Provinsi</title>
</head>
<body>
<div align="left">
		<h3 style="color: gray;">Welcome, ${userID}!</h3>
	</div>

	<h2 style="text-align: center;">Table Data Provinsi</h2>
	
	<form action="${pageContext.request.contextPath}/provinsi/pageProvinsi">
		<input type="text" name=cari style="width:30%; height: 30px; background-color: #f1f1f1;"/>
		<input type=submit value="Cari" style="width: 60px; height: 37px; background-color: lightgrey;" >
	</form><br><br>

	<table>
	<tr>
		<th>Kode Provinsi</th>
		<th>Nama Provinsi</th>
		<th>Action</th>
	</tr>

	<tr>
	<c:forEach var="listProvinsi" items="${provinsi}">
		<tr>
			<td>${listProvinsi.kode}</td>
			<td>${listProvinsi.nama}</td>
			<td style="width:18%">
				<input type="button" value="Edit Data" style="background-color:MediumSeaGreen;" onclick="location.href='${pageContext.request.contextPath}/karyawan/detail/${listProvinsi.kode}';" />
				<input type="button" value="Delete Data" style="background-color:Tomato;" onclick="location.href='${pageContext.request.contextPath}/karyawan/delete/${listProvinsi.kode}';" />
			</td>
		</tr>
	</c:forEach>	
	</table>
	<input type="button" value="Tambah Data" style="background-color:DodgerBlue; width:100%;" onclick="location.href='${pageContext.request.contextPath}/provinsi/add';" /><br>

	<c:forEach var="i" begin="1" end="${total}">
		<a href="${pageContext.request.contextPath}/provinsi/pageProvinsi?page=${i}&cari=${param.cari}">${i}</a>&nbsp <%--buat spasi--%>
	</c:forEach>
	<input type="button" value="Logout" onclick="location.href='${pageContext.request.contextPath}/user/login';" />
	
</body>
</html>