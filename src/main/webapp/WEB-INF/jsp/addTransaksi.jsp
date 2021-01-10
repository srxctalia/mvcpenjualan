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

<title>Add Transaksi</title>

</head>
<body>
<div class="container">
	<h2>Add Customer</h2>
	<f:form action="${pageContext.request.contextPath}/transaksi/save" modelAttribute="dtoH">
		<div class="mb-3">
    		<label for="exampleInputEmail1" class="form-label">No Nota</label>
			<f:input type="text" path="noNota" class="form-control"/>
			<div class="alert alert-danger" role="alert">
				<f:errors path="noNota" />
				<p>${validasi}</p>
			</div>
  		</div>
  		<div class="mb-3">
    		<label for="exampleInputPassword1" class="form-label">Tanggal Transaksi</label>
			<f:input type="date" class="form-control" path="tanggalTransaksi"/>
			<f:errors path="tanggalTransaksi"/>
  		</div>
  		<div class="mb-3">
    		<label for="exampleInputPassword1" class="form-label">Customer</label>
				<f:select path="kodeCustomer" class="custom-select mr-sm-2" id="inlineFormCustomSelect" placeholder="Pilih Kota">
			        <c:forEach items="${customer}" var="c">
				        <f:option value="${c.kodeCustomer}" label="${c.namaCustomer}"></f:option>			      
			      	</c:forEach>			      
		      	</f:select>
				<f:errors path="kodeCustomer"/>
  		</div>
  		<div class="mb-3">
  			<label for="exampleInputPassword1" class="form-label">Karyawan</label>
  			<f:input type="text" class="form-control" path="kodeKaryawan" />
			<f:errors path="kodeKaryawan"/>
  		</div>
  		<div class="mb-3">
  			<h4>Detail</h4>
  			<div class="row">
  				<div class="col">
					<input type="button" class="btn btn-primary" value="Tambah" 
						onclick="location.href='${pageContext.request.contextPath}/transaksi/addDetail';">
  				</div>
			</div>
  		</div>
  		<div class="mb-3">
			<table class="table table-hover table-responsive">
		<thead>
		  <tr class="table-success">
		    <th>Kode Detail</th>
		    <th>Nama Barang</th>
		    <th>Harga Satuan</th>
		    <th>Qty</th>
		    <th>Diskon</th>
		    <th>Sub Total</th>
		    <th>Action</th>
		  </tr>
		</thead>
	<c:forEach items="${dtoD}" var="d">
		  <tr>
		    <td>${d.kodeDetail}</td>
		    <td>${d.namaBarang}</td>
		    <td>${d.hargaSatuan}</td>
		    <td>${d.qty}</td>
		    <td>${d.diskon} </td>
		    <td>${d.subtotal} </td>
		    <td>
		    	<input type="button" class="btn btn-warning btn-sm" value="Edit" onclick="location.href='${pageContext.request.contextPath}/transaksi/editDetail/${d.kodeDetail}';">
		    	<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="location.href='${pageContext.request.contextPath}/transaksi/deleteDetail/${d.kodeDetail}';">
		    </td>
		  </tr>
	</c:forEach>
	</table>
  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/customer/all';"><br><br>
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