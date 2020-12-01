<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conferma Delete</title>
</head>
<body>
	
	<%@ include file="../header.jsp"%>
	
	<main role="main" class="container">

	
		<div class='card'>
		<c:set var= "tavolo" value="${requestScope.tavoloAttribute}"/>
		    <div class='card-header'>
		    
		        <h5>Sei sicuro di voler eliminare il tavolo ${tavolo.denominazione} ?</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class=" btn btn-danger" href="${pageContext.request.contextPath}/special/ExecuteDeleteTavoloServlet?idTavolo=${tavolo.id}">Delete</a>
		    	<a class="btn btn-primary" href="${pageContext.request.contextPath}/special/PrepareSearchTavoloServlet">Back to search</a>
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>