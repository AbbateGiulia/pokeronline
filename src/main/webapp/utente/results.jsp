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
					<th>Nome</th>
					<th>Cognome</th>
					<th>Username</th>
					<th>Stato</th>					
					<th>Ruoli</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaUtentiAttribute }" var="utenteItem">
					<tr>
						<td>${utenteItem.nome }</td>
						<td>${utenteItem.cognome }</td>
						<td>${utenteItem.username }</td>
						<td>${utenteItem.stato }</td>
						<td>
							<a
							href="${pageContext.request.contextPath}/users/ExecuteVisualizzaUtenteServlet?idUtente=${utenteItem.id }"
							class="btn btn-outline-success">Dettaglio</a> 
							<a
							href="${pageContext.request.contextPath}/users/PrepareUpdateUtenteServlet?idUtente=${utenteItem.id }"
							class="btn btn-outline-success">Modifica</a>
							 <a
							href="${pageContext.request.contextPath}/users/ExecuteModificaStatoServlet?idUtente=${utenteItem.id }"
							class="btn btn-outline-success"> 
							<c:if test="${utenteItem.stato == 'DISABILITATO' || utenteItem.stato == 'CREATO' }">Abilita</c:if>
							<c:if test="${utenteItem.stato == 'ATTIVO' }">Disabilita</c:if>
							</a>
						</td>
					</tr>
				</c:forEach>			

			</tbody>
			
			</table>

	</div>
	
	<%@ include file="../footer.jsp" %>
</body>
</html>