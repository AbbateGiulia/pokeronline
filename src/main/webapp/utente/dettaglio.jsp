
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza Utente</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

  <%@ include file="../header.jsp" %>
  
	<div class="container">

      
    <div class="page-header">
	  <h3>Pagina visualizzazione Utente</h3>
	</div>
	
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">		    	
		    	<c:set var= "utente" value="${requestScope.utenteAttribute}"/>
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9"><c:out value="${utente.nome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9"><c:out value="${utente.cognome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username:</dt>
				  <dd class="col-sm-9"><c:out value="${utente.username}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9"><c:out value="${utente.stato}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruolo:</dt>
				 
				  <dd class="col-sm-9">
				  	<c:forEach items="${utente.ruoli}" var="ruolo">
				  		<c:out value="${ruolo.descrizione}"/>
				  	</c:forEach>
				  </dd>
		    	</dl>
		    	
	    		    			    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/users/PrepareSearchUtenteServlet"
		        	class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back to search
		        </a>
		    </div>
		</div>	
		    
		</div>	
	
	
	
	<!-- /.container -->
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>