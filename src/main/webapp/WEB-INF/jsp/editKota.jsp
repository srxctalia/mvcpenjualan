<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="library.jsp" />
<title>Edit Kota</title>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="navbarKota.jsp" />
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
                        <h1 class="h3 mb-0 text-gray-800">Edit Kota</h1>
                    </div>
                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${usr}</span>
                                <img class="img-profile rounded-circle"
                                    src="${pageContext.request.contextPath}/static/css/undraw_profile.svg">
                                    <i class="fas fas fa-stream fa-sm fa-fw ml-2 text-gray-400"></i>
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                               <a class="dropdown-item" href="#">
                                	<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    ${level}
                                </a>
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
<p class="mb-4">Berikut adalah data pada form berikut, update data yang diperlukan.</p>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
						<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">	
									   
						<div class="container">

	<f:form action="${pageContext.request.contextPath}/kota/save" modelAttribute="dto">
		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Kode Kota</h1>
				<f:input type="text" path="kodeKota" class="form-control" readonly="true"/>
	  		</div>
	  		<div class="mb-3">
	    		<h1 class="h6 mb-2 text-gray-900">Nama Kota</h1>
				<f:input type="text" class="form-control" path="namaKota"/>
				<f:errors path="namaKota" class="h7 mb-0 text-danger"/>
	  		</div>
	  		<div class="mb-3">
			      <h1 class="h6 mb-2 text-gray-900">Provinsi</h1>
			      <f:select path="kodeProvinsi" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
			        <c:forEach items="${provinsi}" var="p">
				    <f:option value="${p.kodeProvinsi}" label="${p.namaProvinsi}"/>
			      	</c:forEach>			      
			      </f:select>	
	  		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<input type="button" class="btn btn-secondary" value="Cancel" 
		onclick="location.href='${pageContext.request.contextPath}/kota/page-kota';"><br><br>
	</f:form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>