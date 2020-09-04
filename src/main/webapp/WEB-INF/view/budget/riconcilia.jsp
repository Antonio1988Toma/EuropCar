<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Definizione List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-budget" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Riconciliazione del Budget</h2>
			<br>
			<spring:url value="/budget/riconcilia" var="riconciliaUrl"  />
			<form method="post" action="${riconciliaUrl}">
				<table>
					<tr>
						<td><b>Dal : </b></td>
						<td><input type="text" name="dataDal" id="dataDal" /></td>
					</tr>
					<tr>
						<td><b>Al : </b></td>
						<td><input type="text" name="dataAl" id="dataAl" /></td>
					</tr>
				</table>
				<button type="submit" class="btn btn-success" onclick="return alert('Riconciliazione Effettuata!')">Riconcilia</button>
			</form>	
			<br>
		</div>
	</div>

</body>
</html>