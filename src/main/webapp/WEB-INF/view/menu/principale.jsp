<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>MenuPrincipale</title>
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
				window.location.href = scelta[i].value;
			}
		}
	</script>
</head>
<body>
	<spring:url value="/anno-contabile" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
	<div class="container">
		<h1 align="center">EuropCar</h1>
		<br>
		<h4 align="center">${AnnoContabile}</h4>
		<br>
		<DIV align="center">
			<table>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-archivio" checked="checked"></td>
					<td>Archivio</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-budget"></td>
					<td>Budget</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-preventivo"></td>
					<td>Preventivo</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-ordine-acquisto"></td>
					<td>Ordine d'Acquisto</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-fattura-passiva"></td>
					<td>Fatture Passive</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottomenu-anno-contabile"></td>
					<td>Anno Contabile</td>
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