<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<%@ include file="../header.jsp" %>

<div class="container">


		<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>
   
      
    <div class="page-header">
	  <h3>Pagina di Inserimento</h3>
	</div>
	
	<c:set var= "tavolo" value="${requestScope.tavoloAttribute}"/>
	   
      	<form class="form-horizontal" action="${pageContext.request.contextPath}/special/ExecuteInsertTavoloServlet" method="post">
      	
      	<input class="form-control" type="text" id="userId" name="userId" value="${tavolo.idCreatore}" >
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="denominazione">Denominazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="denominazione" name="denominazione" value="${tavolo.denominazione}" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="esperienzaMinima">Esperienza minima:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" id="esperienzaMinima" name="esperienzaMinima" value="${tavolo.esperienzaMinima}">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="puntataMinima">Puntata Minima:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" id="puntataMinima" name="puntataMinima" value="${tavolo.creditoMinimo}" >
			 	</div>
  			</div>
  				
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effetua Inserimento</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>