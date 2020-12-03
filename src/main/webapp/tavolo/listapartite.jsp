<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati Ricerca</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<%@ include file="../header.jsp"%>


	<div class="container">
	
	<%-- alert conferma --%>
		<div class="alert alert-success ${messaggioConferma!=null?'':'d-none' }" role="alert">
			${messaggioConferma }
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			</div>


		<div class="page-header">
			<h3>Pagina dei Risultati</h3>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Denominazione</th>
					<th>Data Creazione</th>
					<th>Esperienza</th>
					<th>Puntata Min</th>					
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaTavoliAttribute }" var="tavoloItem">
					<tr>
						<td>${tavoloItem.denominazione }</td>
						<td>${tavoloItem.dataCreazione }</td>
						<td>${tavoloItem.esperienzaMinima }</td>
						<td>${tavoloItem.creditoMinimo }</td>
						<td>												
							<a
							href="${pageContext.request.contextPath}/PrepareGiocaTavoloServlet?idTavolo=${tavoloItem.id }"
							class="btn btn-info">Gioca</a>					
						</td>
					</tr>
				</c:forEach>			

			</tbody>
			
			</table>

	</div>
</body>
</html>