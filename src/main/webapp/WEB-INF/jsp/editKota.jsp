<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Kota</title>
</head>
<body>

	<h1>Edit Kota </h1>
	<f:form action="${pageContext.request.contextPath}/kota/save"
	modelAttribute="dto"> 
		<table>
			<tr>
				<td>Kode Kota</td>
				<td>:</td>
				<td><f:input path="kodeKota" readonly="true"/></td>
			</tr>
			<tr>
				<td>Nama Kota</td>
				<td>:</td>
				<td><f:input path="namaKota" /></td>
				<td><f:errors path="namaKota" /></td>
			</tr>
			<tr>
				<td>Kode Provinsi</td>
				<td>:</td>
				<td><f:input path="kodeProvinsi" /></td>
				<td><f:errors path="kodeProvinsi" /></td>
			</tr>
			<tr>
			<td><input type="submit" value="Simpan">
			<input type="button" value="Batal"
			onclick="location.href='${pageContext.request.contextPath}/kota/page-kota';">
			</td>
			</tr>
		</table>
	</f:form>

</body>
</html>