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
	
	<c:set var= "tavolo" value="${requestScope.tavoloAttribute}"/>
	   
      	<form class="form-horizontal" action="${pageContext.request.contextPath}/special/ExecuteSearchTavoloServlet" method="post">
      	
      	<input class="form-control" type="hidden" id="userId" name="userId" value="${tavolo.idCreatore}" >
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="denominazione">Denominazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="denominazione" name="denominazione" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataCreazione">Data:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataCreazione" name="dataCreazione" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="puntataMinima">Puntata Minima:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" id="puntataMinima" name="puntataMinima" >
			 	</div>
  			</div>
  				
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-success btn-md">Effetua Ricerca</button>
		        <a href="${pageContext.request.contextPath}/special/PrepareInsertTavoloServlet" class="btn btn-success btn-md">Inserisci Nuovo Tavolo</a>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->

<%@ include file="../footer.jsp" %>

</body>
</html>