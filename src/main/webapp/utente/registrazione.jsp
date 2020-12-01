<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Nuovo Utente</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
	
	<!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
	
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


		

		<div class="page-header">
			<h3>Registrazione Utente</h3>
		</div>
		
		<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty utenteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${utenteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		<form class="form-horizontal" action="ExecuteRegistrazioneUtenteServlet"
			method="post" id="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="nome">Nome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="nome"
						name="nome" value="${utenteAttribute.nome}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cognome">Cognome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="cognome"
						name="cognome" value="${utenteAttribute.cognome}">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="username">Username:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="username"
						name="username" value="${utenteAttribute.cognome}">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password:</label>
				<div class="col-sm-4">
					<input class="form-control" type="password" id="password"
						name="password" value="${utenteAttribute.cognome}">
				</div>
			</div>
					


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md">Effettua
						Inserimento</button>
				</div>
			</div>
			
			
  			<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
			<script>
			$(document).ready(function () {

				$("#form").validate({
				    rules: {
				        nome: {				            
				            required: true
				        },
				        cognome: {					        	
				            required: true
				        },
				        password: {   				        	
				            required: true
				        },
				        username: {   
				            required: true
				        }
				        
				    },
				   messages:{
					   nome: "nome obbligatorio",
					   cognome:"cognome obbligatorio",
					   password:"pass obbligatorio",
					   username:"user obbligatorio"
					  				  
				   },
				   submitHandler: function(form){
					   form.submit();
				    }
				});
				});
				</script>
			
			
		</form>

	</div>
	<!-- /.container -->

<%@ include file="../footer.jsp" %>

</body>


</html>