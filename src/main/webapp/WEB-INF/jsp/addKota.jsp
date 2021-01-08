<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Kota</title>
</head>
<body>

<div class="container">
	<h2>Add Kota</h2>
	<div class="alert alert-danger" role="alert">
  		${valid}
	</div>
	<f:form action="${pageContext.request.contextPath}/kota/save" modelAttribute="dto">
		<div class="mb-3">
	    		<label for="exampleInputEmail1" class="form-label">Kode Kota</label>
				<f:input type="text" path="kodeKota" class="form-control" placeholder="Masukan kode kota"/>
				<f:errors path="kodeKota" />
	  		</div>
	  		<div class="mb-3">
	    		<label for="exampleInputPassword1" class="form-label">Nama Kota</label>
				<f:input type="text" class="form-control" path="namaKota" placeholder="Masukan nama kota"/>
				<f:errors path="namaKota"/>
	  		</div>
	  		<div class="mb-3">
			      <label class="mr-sm-2" for="inlineFormCustomSelect">Provinsi</label>
			      <f:select path="kodeProvinsi" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
			        <f:option value="NONE" label="--- Select ---"/>
			        <f:options items="${provinsi}"/>			      
			      </f:select>
	  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/kota/page-kota';"><br><br>
	</f:form>
</div>

  <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
  

</body>
</html>