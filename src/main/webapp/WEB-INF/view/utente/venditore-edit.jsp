<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Venditore</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/utente/utente-registrazione" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${venditoreForm.id>0}">
					<h2>Modifica Venditore</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuovo Venditore</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/utente/venditore-save" var="registraUrl"  />
			
			<form:form modelAttribute="venditoreForm" method="post" action="${registraUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Cognome</label>
						<form:input path="cognome" cssClass="form-control" />
						<span style="color:red"><form:errors path="cognome" /></span>
					</div>
					<div class="form-group">
						<label>Nome</label>
						<form:input path="nome" cssClass="form-control" />
						<span style="color:red"><form:errors path="nome" /></span>
					</div>
					<div class="form-group">
						<label>Indirizzo</label>
						<form:input path="indirizzo" cssClass="form-control" />
						<span style="color:red"><form:errors path="indirizzo" /></span>
					</div>
					<div class="form-group">
						<label>Telefono</label>
						<form:input path="numeroTelefono" cssClass="form-control" />
						<span style="color:red"><form:errors path="numeroTelefono" /></span>
					</div>
					<div class="form-group">
						<label>Tipo Venditore</label>
						<form:select  path="tipoVenditore" cssClass="form-control">
						    <form:option value=""> --Seleziona Tipo Venditore--</form:option>
						    <c:forEach items="${listTipi}" var="currentTipiVenditore">
						    	<c:choose>
						    		<c:when test="${currentTipiVenditore.id == chiaveTipo}">
						    			<form:option value="${currentTipiVenditore}" selected="true">${currentTipiVenditore.tipoVenditore}</form:option>
						    		</c:when>
						    		<c:otherwise>
						    			<form:option value="${currentTipiVenditore}">${currentTipiVenditore.tipoVenditore}</form:option>
						    		</c:otherwise>
						    	</c:choose>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="tipoVenditore" /></span> 
					    <spring:url value="/utente/tipo-venditore-list" var="tipoUrl"  />
					    <br>
						<a class="btn btn-primary" href="${tipoUrl}" role="button">Tipo Venditore</a>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
</body>
</html>