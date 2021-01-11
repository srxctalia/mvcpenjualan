<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Add Detail Transaksi</title>

</head>
<body>
<div class="container">
	<h2>Add Detail Transaksi</h2>
	<f:form action="${pageContext.request.contextPath}/transaksi/saveDetail" modelAttribute="dtoD">
		<div class="mb-3">
	    		<label for="exampleInputEmail1" class="form-label">Kode Detail</label>
				<f:input type="text" path="kodeDetail" class="form-control"/>
				<div class="alert alert-danger" role="alert">
					<f:errors path="kodeDetail"  />
					<p>${validasi}</p>
				</div>
	  		</div>
	  		<div class="mb-3">
			      <label class="mr-sm-2" for="inlineFormCustomSelect">Barang</label>
			      <f:select path="kodeBarang" class="custom-select mr-sm-2" id="inlineFormCustomSelect" placeholder="Pilih Kota">
			        <c:forEach items="${barang}" var="b">
				        <f:option value="${b.kodeBarang}" label="${b.namaBarang}"></f:option>			      
			      	</c:forEach>			      
			      </f:select>
	  		</div>
	  		<div class="mb-3">
	    		<label for="exampleInputPassword1" class="form-label">Qty</label>
				<f:input type="text" class="form-control" path="qty" placeholder="Masukan qty"/>
				<f:errors path="qty"/>
	  		</div>
	  		<div class="mb-3">
	    		<label for="exampleInputPassword1" class="form-label">Diskon</label>
				<f:textarea class="form-control" path="diskon" placeholder="Masukan diskon" rows="3"/>
				<f:errors path="diskon"/>
	  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/transaksi/add';"><br><br>
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