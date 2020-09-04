<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Area Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/budget/search/${chiaveArea}" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Definizione del Budget SottoCategoria</h2>
			<br>
			<table>
				<tr>
					<td><b>Area : </b></td>
					<td>${definizioneForm.area.nomeArea}</td>
				</tr>
				<tr>
					<td><b>Sottocategoria : </b></td>
					<td>${definizioneForm.sottocategoria}</td>
				</tr>
			</table>
			<br>
			<spring:url value="/budget/update" var="saveUrl"  />
			
			<form:form modelAttribute="definizioneForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
				<div class="form-group">
					<label>Budget</label>
					<form:input type="number" min="0" step=".01" path="budget" cssClass="form-control" />
					<span style="color: red"><form:errors path="budget" /></span>
				</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
</body>
</html>