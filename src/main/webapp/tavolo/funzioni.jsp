<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gioco</title>
	
	 <style type="text/css">
    	body {
		  padding-top: 0;
		}	
    </style>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

	<%@ include file="../header.jsp"%>

 	<div class="container">
 	
 	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			</div>

 		<div class="row justify-content-center">
 			<div class="col-sm-6 text-center">
				<div class="jumbotron jumbotron-fluid">
					 <h1 class="display-8">Compra Credito</h1>
				       <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareShopCreditoServlet" role="button">  Shop &raquo;</a> </p>
		        	  
			     </div>
		    </div>
		
		   
		  <c:if test="${sessionScope.userInfo.tavolo == null}">
	      <div class="col-sm-6 text-center">
		    <div class="jumbotron jumbotron-fluid">			
			        <h1 class="display-8">Cerca tavolo</h1>
			        <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareSeacrhPartitaServlet" role="button">Search &raquo;</a></p> 
		    </div>
    	  </div>
    </c:if>
    
		<c:if test="${sessionScope.userInfo.tavolo != null}">
	     <div class="col-sm-6 text-center">
	    	<div class="jumbotron jumbotron-fluid">	    		
			        <h1 class="display-8">Go to last game</h1>
			        <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/users/PrepareSearchUtenteServlet" role="button">Torna al gioco &raquo;</a></p>	     		      
	    	</div>
	     </div>
    </c:if>
    
   	</div>
    
   </div>



	 <%@ include file="../footer.jsp" %>


</body>
</html>