<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home Page</title>
	
	 <style type="text/css">
    	body {
		  padding-top: 0;
		}	
    </style>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

	<%@ include file="header.jsp"%>

 	<div class="container">
 	
 	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			</div>

 		<div class="row justify-content-center">
 		<c:if test="${sessionScope.userInfo.isAdmin() || sessionScope.userInfo.isSpecial()}">
 			<div class="col-sm-6 text-center">
				<div class="jumbotron jumbotron-fluid">
					 <h1 class="display-8">Gestione Tavolo</h1>
				       <p><a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/special/PrepareSearchTavoloServlet" role="button">Vai alla Gestione &raquo;</a></p>
		        	  
			     </div>
		    </div>
		 </c:if>
		   
		  
	      <div class="col-sm-6 text-center">
		    <div class="jumbotron jumbotron-fluid">			
			        <h1 class="display-8">Play Management</h1>
			        <p><a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/PrepareFunzioniTavoloServlet" role="button">Vai al Gioco &raquo;</a></p> 
		    </div>
    	  </div>
    
		<c:if test="${sessionScope.userInfo.isAdmin()}">
	     <div class="col-sm-6 text-center">
	    	<div class="jumbotron jumbotron-fluid">	    		
			        <h1 class="display-8">Gestione Amministrazione</h1>
			        <p><a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/users/PrepareSearchUtenteServlet" role="button">Vai alla Gestione &raquo;</a></p>	     		      
	    	</div>
	     </div>
    </c:if>
    
   	</div>
    
   </div>



	 <%@ include file="footer.jsp" %>


</body>
</html>