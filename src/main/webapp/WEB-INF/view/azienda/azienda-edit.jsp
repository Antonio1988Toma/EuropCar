<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Azienda Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/azienda/list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${aziendaForm.id>0}">
					<h2>Modifica Azienda</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuova Azienda</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/azienda/save" var="saveUrl"  />
			
			<form:form modelAttribute="aziendaForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Contract ID</label>
						<form:input path="contractId" cssClass="form-control" />
						<span style="color: red"><form:errors path="contractId" /></span>
					</div>
					<div class="form-group">
						<label>RagioneSociale</label>
						<form:input path="ragioneSociale" cssClass="form-control" />
						<span style="color: red"><form:errors path="ragioneSociale" /></span>
					</div>
					<div class="form-group">
						<label>Indirizzo</label>
						<form:input path="indirizzo" cssClass="form-control" />
						<span style="color: red"><form:errors path="indirizzo" /></span>
					</div>
					<div class="form-group">
						<label>Gruppo</label>
						<form:select  path="gruppo" cssClass="form-control">
						    <form:option value=""> --Seleziona Gruppo--</form:option>
						    <c:forEach items="${listGruppi}" var="currentGruppo">
								<form:option value="${currentGruppo}">${currentGruppo.nomeGruppo}</form:option>
							</c:forEach>
					    </form:select>
					    <br>
						<spring:url value="/azienda/azienda-gruppo-list" var="gruppoUrl"  />
						<a class="btn btn-primary" href="${gruppoUrl}" role="button">Gruppo</a>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>