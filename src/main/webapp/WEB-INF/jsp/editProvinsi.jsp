<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Data Provinsi</title>
</head>
<body>
	<h3>Edit Data Provinsi</h3>
	
	<div>
		<f:form modelAttribute="dto" action="${pageContext.request.contextPath}/provinsi/save">
			<label for="kode">Kode : </label><br>
			<f:input path="kodeProvinsi" readonly="true" type="text" id="kode" name="kode" style="background-color:LightGray;"/><br> <%-- path ini diambil dr propertinya --%>
			
			<label for="nama">Nama : <f:errors path="nama" style="color: Tomato"></f:errors></label><br>
			<f:input path="namaProvinsi" type="text" id="nama" name="nama" placeholder="Input Nama.."/><br>
			
		    <input type="submit" value="Simpan" /> <%-- kl submit gabisa pake onClick harus pake action di form --%>
		    <input type="button" value="Batal" style="background-color:#ff6347;" onclick="location.href='${pageContext.request.contextPath}/provinsi/pageProvinsi';" /> <%-- balik lagi ke penduduk all kl batal --%>
	  </f:form>
	</div>
</body>
</html>