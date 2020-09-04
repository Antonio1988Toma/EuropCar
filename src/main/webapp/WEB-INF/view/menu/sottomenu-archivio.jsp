<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>SottomenuArchivio</title>
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
				window.location.href = "sottomenu-archivio/" + scelta[i].value;
			}
		}
	</script>
</head>
<body>
<spring:url value="/menu/principale" var="backUrl"  />
<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container"> 
		<h2 align="center">Archivio</h2>
		<br>
		<DIV align="center">
			<table>
				<tr>
					<td><INPUT type="radio" name="scelta" value="area" checked="checked"></td>
					<td>Archivio Area</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="sottocategoria"></td>
					<td>Archivio Sottocategoria</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="fornitore"></td>
					<td>Archivio Fornitore</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="progetto"></td>
					<td>Archivio Progetto</td>
				</tr>
				<tr>
					<td><INPUT type="radio" name="scelta" value="aliquotaIva"></td>
					<td>Archivio Aliquota IVA</td>
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