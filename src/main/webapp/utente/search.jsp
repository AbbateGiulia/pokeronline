<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

 <%@ include file="../header.jsp" %>

<div class="container">

<%-- alert conferma --%>
		<div class="alert alert-success ${messaggioConferma!=null?'':'d-none' }" role="alert">
			${messaggioConferma }
		</div>
		

      
    <div class="page-header">
	  <h3>Pagina di Ricerca</h3>
	</div>
	
	<c:set var= "utente" value="${requestScope.utenteAttribute}"/>
	   
      	<form class="form-horizontal" action="${pageContext.request.contextPath}/users/ExecuteSearchUtenteServlet" method="post">
      	
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nome">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nome" name="nome" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognome">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognome" name="cognome" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="username">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="username" name="username" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataRegistrazione">Data Registrazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataRegistrazione" name="dataRegistrazione" >
			 	</div>
  			</div>
  			
  			
						<div class="form-group">
							<label class="control-label col-sm-2">Ruolo </label>
								<div class="col-sm-4">
									<select name="ruolo" id="ruolo" class="form-control" id="exampleFormControlSelect1">
										<option selected value="">Nessun Ruolo</option>
										<c:forEach items="${requestScope.listaRuoli}" var="ruolo">
										<option value="${ruolo.id}">
										<c:out value="${ruolo.descrizione}" />
										</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
				
				
					
						<div class="form-group">
							<label class="control-label col-sm-2">Stato </label>
								<div class="col-sm-4">
									<select name="stato" id="stato" class="form-control" id="exampleFormControlSelect1">
										<option selected value="">Nessuno Stato</option>
										<c:forEach items="${requestScope.listaStati}" var="stato">
										<option value="${stato}">
										<c:out value="${stato}" />
										</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
				
  				
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-success btn-md">Effetua Ricerca</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->

<%@ include file="../footer.jsp" %>

</body>
</html>