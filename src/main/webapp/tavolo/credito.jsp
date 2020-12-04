<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SHOP</title>
</head>
<body>
	
	<%@ include file="../header.jsp"%>
	
	<main role="main" class="container">

	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
	</div>
			
			<div class="alert alert-success ${successMessage!=null?'':'d-none' }" role="alert">
			${successMessage}
			</div>
			
		<div class='card'>
			<c:set var= "utente" value="${sessionScope.userInfo}"/>
		    	<div class='card-header'>
		    
		        	<h5>Acquista credito</h5> 
		    	</div>
		    	
		    <div class='card-body'>
		    	<form class="form-horizontal" action="${pageContext.request.contextPath}/ExecuteShopCreditoServlet" method="post">
		    	
		     		<div class="form-group">		     
		     			<input class="form-control" type="hidden" id="idUtente" name="idUtente" value="${utente.id}">	
			    		<div class="col-sm-4">
							<input class="form-control" type="number" id="credito" name="credito" value="${requestScope.credito}">
					 	</div>
  					</div>
  					
			         <div class="col-sm-4">
			          	<button type="submit" class="btn btn-success btn-md">Shop</button>
			    		<a class="btn btn-secondary" href="${pageContext.request.contextPath}/tavolo/funzioni.jsp">Back</a>
			    	</div>
		   		</form>
		    </div>
		    
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>