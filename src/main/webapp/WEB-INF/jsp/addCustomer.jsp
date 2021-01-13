<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="library.jsp" />
<title>Add Customer</title>

</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="navbarCustomer.jsp" />
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    
                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-1r">
                        <h1 class="h3 mb-0 text-gray-800">Add Customer</h1>
                    </div>
                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
                                <img class="img-profile rounded-circle"
                                    src="${pageContext.request.contextPath}/static/css/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->
<div class="container-fluid">
<!-- Page Heading -->
<p class="mb-4">Silahkan isi data pada form berikut</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<div class="container">
						<c:set var="cek"  value="${stat}"/>  <!-- CHEK SINI AMA CTL ADD NYA -->
					<c:if test="${cek == 1}">	 
					<div class="alert alert-danger alert-dismissible fade show">
				    <button type="button" class="close " data-dismiss="alert">&times;</button>
				    <h1 class="h6 mb-0 text-gray-600">${validasi}</h1>
				  	</div>
  					</c:if>
	<f:form action="${pageContext.request.contextPath}/customer/save" modelAttribute="dto">
		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Kode Customer</h1>
				<f:input type="text" path="kodeCustomer" class="form-control" placeholder="Masukan kode customer${kodeTerakhir}" oninput="let p=this.selectionStart;this.value=this.value.toUpperCase();this.setSelectionRange(p, p);"/>
				<f:errors path="kodeCustomer" class="h7 mb-0 text-gray-600"/>
	  		</div>
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Nama Customer</h1>
				<f:input type="text" class="form-control" path="namaCustomer" placeholder="Masukan nama customer"/>
				<f:errors path="namaCustomer" class="h7 mb-0 text-gray-600"/>
	  		</div>
			<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Email</h1>
				<f:input type="email" class="form-control" path="emailCustomer" placeholder="Masukan email"/>
				<f:errors path="emailCustomer" class="h7 mb-0 text-gray-600"/>
	  		</div>
	  		<div class="mb-3">
	  			<h1 class="h6 mb-2 text-gray-900">Jenis Kelamin</h1>
	  			<f:select path="jenisKelamin"  class="custom-select mr-sm-2">
	  				<option disabled selected value> -- select an option -- </option>
		  			<f:option value="male" label="Laki-laki"/>
		  			<f:option value="female" label="Perempuan"/>
	  			</f:select>
				<f:errors path="jenisKelamin" class="h7 mb-0 text-gray-600"/>
	  		</div>			
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Alamat</h1>
				<f:textarea class="form-control" path="alamatCustomer" placeholder="Masukan Alamat" rows="3"/>
				<f:errors path="alamatCustomer" class="h7 mb-0 text-gray-600"/>
	  		</div>			
	  		<div class="mb-3">
			      <h1 class="h6 mb-2 text-gray-900">Kota</h1>
			      <f:select path="kodeKota" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
			      <option disabled selected value> -- select an option -- </option>
			        <c:forEach items="${kota}" var="k">
				        <f:option value="${k.kodeKota}" label="${k.namaKota }"/>
			      	</c:forEach>
			      </f:select>
			      <f:errors path="kodeKota" class="h7 mb-0 text-gray-600"/>		
	  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/customer/all';"><br><br>
	</f:form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>