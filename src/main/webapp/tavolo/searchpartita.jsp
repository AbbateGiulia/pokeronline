<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Nuovo Abitante</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("assets/img/favicons/anim_16x16.gif") right center no-repeat;
}
</style>
	<style type="text/css">
			.error {
			  color: #a94442;
			  background-color: #f2dede;
			  border-color: #ebccd1;
			  padding:1px 20px 1px 20px;
			}
		</style>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

	<%@ include file="../header.jsp"%>

		<div class="container">

		
		

		<div class="page-header">
			<h3>Cerca partita</h3>
		</div>
		
		<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty partitaErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${partitaErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		<form class="form-horizontal" action="${pageContext.request.contextPath}/ExecuteSearchPartitaServlet"
			method="post" id="form">
			
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
				<label class="control-label col-sm-2" for="utenteInputId">Giocatore:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="utenteInputId"
						name="utenteInput" >
					<input type="hidden" name="utenteId" id="utenteId">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="creatoreInputId">Creatore:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="creatoreInputId"
						name="creatoreInput" >
					<input type="hidden" name="creatoreId" id="creatoreId">
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success btn-md">Effettua
						Inserimento</button>
				</div>
			</div>
			
			<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
			<script>
				$( "#utenteInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "SearchUtenteAjaxServlet?filter=uno",
					            datatype: "json",
					            data: {
					                term: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            },
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#utenteInputId").val(ui.item.label)
				        return false;
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#utenteId').val(ui.item.value);
				    	console.log($('#utenteId').val())
				        return false;
				    }
				});
			</script>
			
			<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
			<script>
				$( "#creatoreInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "SearchUtenteAjaxServlet?filter=due",
					            datatype: "json",
					            data: {
					                term: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            },
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#creatoreInputId").val(ui.item.label)
				        return false;
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#creatoreId').val(ui.item.value);
				    	console.log($('#creatoreId').val())
				        return false;
				    }
				});
			</script>
			
			
			
		</form>

	</div>
	<!-- /.container -->

<%@ include file="../footer.jsp" %>

</body>


</html>