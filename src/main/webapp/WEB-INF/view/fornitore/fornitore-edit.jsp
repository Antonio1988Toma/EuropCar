<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Fornitore Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/fornitore/list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${fornitoreForm.id>0}">
					<h2>Modifica Fornitore</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuovo Fornitore</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/fornitore/save" var="saveUrl"  />
			
			<form:form modelAttribute="fornitoreForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Ragione Sociale</label>
						<form:input path="ragioneSociale" cssClass="form-control" />
						<span style="color: red"><form:errors path="ragioneSociale" /></span>
					</div>
					<div class="form-group">
						<label>Indirizzo</label>
						<form:input path="indirizzo" cssClass="form-control" />
						<span style="color: red"><form:errors path="indirizzo" /></span>
					</div>
					<div class="form-group">
						<label>Citta</label>
						<form:input path="citta" cssClass="form-control" />
						<span style="color: red"><form:errors path="citta" /></span>
					</div>
					<div class="form-group">
						<label>CAP</label>
						<form:input path="cap" cssClass="form-control" />
						<span style="color: red"><form:errors path="cap" /></span>
					</div>
					<div class="form-group">
						<label>Provincia</label>
						<form:input path="provincia" cssClass="form-control" />
						<span style="color: red"><form:errors path="provincia" /></span>
					</div>
					<div class="form-group">
						<label>Partita IVA</label>
						<form:input path="partitaIva" cssClass="form-control" />
						<span style="color: red"><form:errors path="partitaIva" /></span>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>