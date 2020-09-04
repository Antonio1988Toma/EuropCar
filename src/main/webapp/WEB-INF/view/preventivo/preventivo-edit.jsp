<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Preventivo Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/preventivo/search/${chiaveFornitore}" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${preventivoForm.id>0}">
					<h2>Modifica Preventivo</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuovo Preventivo</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/preventivo/save" var="saveUrl"  />
			
			<form:form modelAttribute="preventivoForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label><b>Fornitore : </b></label>
						<form:hidden path="fornitore" value="${Fornitore.id}" />
						<label>${Fornitore.ragioneSociale}</label>
						<span style="color:red"><form:errors path="fornitore" /></span>
					</div>
					<div class="form-group">
						<label>Codice</label>
						<form:input path="codice" cssClass="form-control" />
						<span style="color:red"><form:errors path="codice" /></span>
					</div>
					<div class="form-group">
						<label>Preventivo</label>
						<form:input path="nomePreventivo" cssClass="form-control" />
						<span style="color:red"><form:errors path="nomePreventivo" /></span>
					</div>
					<div class="form-group">
						<label>Data</label>
						<form:input path="data" cssClass="form-control" />
					</div>
				<div class="form-group">
					<label>Importo</label>
					<form:input type="number" min="0" step=".01" path="importo" cssClass="form-control" />
					<span style="color: red"><form:errors path="importo" /></span>
				</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>