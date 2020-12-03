<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PLAY</title>
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
		
		    <div class='card-header'>
		    
		    
		    
		        <h5>You're playing now!</h5> 
		    </div>
		    	
		    <div class='card-body'>
		    <form class="form-horizontal" action="${pageContext.request.contextPath}/ExecuteGiocaTavoloServlet" method="post">
		     <div class="form-group">
		     
		     Credito attuale ${requestScope.utenteAttribute.creditoAccumulato}
		     
		     <input class="form-control" type="hidden" id="idUtente" name="idUtente" value="${requestScope.utenteAttribute.id}">
		     <input class="form-control" type="hidden" id="idTavolo" name="idTavolo" value="${requestScope.tavoloAttribute.id}">
      			
      			<div class="alert alert-success ${risultatoPositivo!=null?'':'d-none'}"  role="alert">
						${risultatoPositivo}
				</div>
				<div class="alert alert-danger ${risultatoNegativo!=null?'':'d-none'}"  role="alert">
						${risultatoNegativo}
				</div>
				
  			</div>
		         <div class="col-sm-4">
		          <button type="submit" class="btn btn-primary btn-md">Gioca</button>
		    	<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ExecuteLasciaTavoloServlet">Lascia</a>
		    	</div>
		   	</form>
		    </div>
		    
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>