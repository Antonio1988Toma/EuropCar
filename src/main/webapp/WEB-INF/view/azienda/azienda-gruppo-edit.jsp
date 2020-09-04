<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gruppo Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/azienda/azienda-gruppo-list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${gruppoForm.id>0}">
					<h2>Modifica Gruppo</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuovo Gruppo</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/azienda/azienda-gruppo-save" var="saveUrl"  />
			
			<form:form modelAttribute="gruppoForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Codice</label>
						<form:input path="codice" cssClass="form-control" />
						<span style="color: red"><form:errors path="codice" /></span>
					</div>
					<div class="form-group">
						<label>Gruppo</label>
						<form:input path="nomeGruppo" cssClass="form-control" />
						<span style="color: red"><form:errors path="nomeGruppo" /></span>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>