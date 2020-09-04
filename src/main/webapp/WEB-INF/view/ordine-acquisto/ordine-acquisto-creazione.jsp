<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ordine Acquisto Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<c:choose>
		<c:when test="${chiaveFornitore == null }">
			<spring:url value="/menu/sottomenu-ordine-acquisto" var="backUrl"  />
		</c:when>
		<c:otherwise>
			<spring:url value="/fattura-passiva/search-fattura/${chiaveFornitore}" var="backUrl"  />
		</c:otherwise>
	</c:choose>
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Inserimento Ordine</h2>
			<br>
			<spring:url value="/ordine-acquisto/dettaglio" var="dettaglioUrl"  />
			
			<form:form modelAttribute="ordineForm" method="get" action="${dettaglioUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Descrizione</label>
						<form:input path="nomeOrdineAcquisto" cssClass="form-control" />
						<span style="color:red"><form:errors path="nomeOrdineAcquisto" /></span>
					</div>
					<div class="form-group">
						<label>Numero</label>
						<form:input path="numero" cssClass="form-control" />
						<span style="color:red"><form:errors path="numero" /></span>
					</div>
					<div class="form-group">
						<label>Data</label>
						<form:input path="data" cssClass="form-control" />
						<span style="color:red"><form:errors path="data" /></span> 
					</div>
					<div class="form-group">
						<label>Fornitore</label>
						<form:select  path="fornitore" cssClass="form-control">
						    <form:option value=""> --Seleziona Fornitore--</form:option>
						    <c:forEach items="${listFornitori}" var="currentFornitore">
						    	<c:choose>
						    		<c:when test="${currentFornitore.id == chiaveFornitore}">
						    			<form:option value="${currentFornitore}" selected="true">${currentFornitore.ragioneSociale}</form:option>
						    		</c:when>
						    		<c:otherwise>
						    			<form:option value="${currentFornitore}">${currentFornitore.ragioneSociale}</form:option>
						    		</c:otherwise>
						    	</c:choose>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="fornitore" /></span> 
					</div>
				<button type="submit" class="btn btn-primary">Nuova Spesa</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>