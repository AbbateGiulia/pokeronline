<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<%@ include file="../header.jsp" %>

<div class="container">


		<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty utenteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${utenteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>
   
      
    <div class="page-header">
	  <h3>Pagina di Update</h3>
	</div>
	
	<c:set var= "utente" value="${requestScope.utenteAttribute}"/>
	   
      	<form class="form-horizontal" action="${pageContext.request.contextPath}/users/ExecuteUpdateUtenteServlet" method="post">
      	
      	<input class="form-control" type="hidden" id="utenteId" name="utenteId" value="${utente.id}" >
      	
      	<input class="form-control" type="hidden" id="stato" name="stato" value="${utente.stato}" >
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nome">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nome" name="nome" value="${utente.nome}" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognome">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognome" name="cognome" value="${utente.cognome}">
			 	</div>
  			</div>
			
			<div class="form-group">
      			<label class="control-label col-sm-2" for="username">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="username" name="username" value="${utente.username}">
			 	</div>
  			</div>
  			 <c:if test="${utente.stato == 'CREATO'}">	
  			  <div class="form-group form-check">
						  Ruolo
						  <c:forEach items="${requestScope.listaRuoli}" var="ruolo"> <br>						  
					    <input value="${ruolo.id}" id="${ruolo.id}" name="ruolo" type="checkbox" 
					    	<c:forEach items="${utente.ruoli}" var="ruoloUtente">
					    	
					    	${ruoloUtente.id eq ruolo.id ? 'checked' : ''}
					    	
					    	</c:forEach>
					    	
					    	>
						    <label class="form-check-label" for="${ruolo.id}"> ${ruolo.descrizione} </label>					   
						    </c:forEach>
					  </div>					
				 </c:if>
						  
  				
  				
  				
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effetua Aggiornamento</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>