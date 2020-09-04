<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>SottomenuOrdineAcquisto</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	<script type="text/javascript">
		function sceltaMenu(){
			var scelta = document.getElementsByName("scelta");
			var i = 0;
			while(i<scelta.length && !scelta[i].checked){
				i++;
			}
			if(i<scelta.length){
				window.location.href = "sottomenu-ordine-acquisto/" + scelta[i].value;
			}
		}
	</script>
</head>
<body>
<spring:url value="/menu/principale" var="backUrl"  />
<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container"> 
		<h2 align="center">Ordine di Acquisto</h2>
		<br>
		<DIV align="center">
			<table>
				<tr>
					<td><INPUT type="radio" name="scelta" value="creazione" checked="checked"></td>
					<td>Creazione</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="gestione"></td>
					<td>Gestione</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="ricerca"></td>
					<td>Ricerca</td>
				</tr>
			</table>
			<br>
			<DIV>
				<INPUT type="button" value="Avanti" onclick="sceltaMenu()" class="btn btn-primary">
			</DIV>	
		</DIV>
	</div> 
</body>
</html>