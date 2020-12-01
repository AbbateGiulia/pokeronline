
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza Tavolo</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

  <%@ include file="../header.jsp" %>
  
	<div class="container">

      
    <div class="page-header">
	  <h3>Pagina visualizzazione Tavolo</h3>
	</div>
	
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">		    	
		    	<c:set var= "tavolo" value="${requestScope.tavoloAttribute}"/>
				  <dt class="col-sm-3 text-right">Denominazione:</dt>
				  <dd class="col-sm-9"><c:out value="${tavolo.denominazione}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Credito Minimo:</dt>
				  <dd class="col-sm-9"><c:out value="${tavolo.creditoMinimo}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Esperienza Minima:</dt>
				  <dd class="col-sm-9"><c:out value="${tavolo.esperienzaMinima}"/></dd>
		    	</dl>	
		    	
		    		    			    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/special/PrepareSearchTavoloServlet"
		        	class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back to search
		        </a>
		    </div>
		</div>	
		    
		</div>	
	
	
	
	</div><!-- /.container -->
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>