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
	<c:choose>
		<c:when test="${chiaveFattura == null }">
			<spring:url value="/fattura-passiva/registrazione" var="backUrl"  />
		</c:when>
		<c:otherwise>
			<spring:url value="/fattura-passiva/update-fattura/${chiaveFattura}" var="backUrl"  />
		</c:otherwise>
	</c:choose>
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${chiaveFattura != null}">
					<h2>Modifica Dettaglio Fattura</h2>
				</c:when>
				<c:otherwise>
					<h2>Inserimento Dettaglio Fattura</h2>
				</c:otherwise>
			</c:choose>
			<br>
			
			<c:choose>
				<c:when test="${chiaveFattura == null}">
					<spring:url value="/fattura-passiva/list" var="listUrl"  />
				</c:when>
				<c:otherwise>
					<spring:url value="/fattura-passiva/update-fattura/${chiaveFattura}" var="listUrl"  />
				</c:otherwise>
			</c:choose>
			
			<form:form modelAttribute="dettaglioForm" method="post" action="${listUrl}" cssClass="form">
				<form:hidden path="id" />
				<form:hidden path="imponibile" />
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${chiaveFattura != null}"> --%>
<%-- 						<form:hidden path="fattura" value="${chiaveFattura}" /> --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
					<div class="form-group">
						<label>Dettaglio Fattura</label>
						<form:input path="dettaglioFattura" cssClass="form-control" />
						<span style="color:red"><form:errors path="dettaglioFattura" /></span>
					</div>
					<div class="form-group">
						<label>Importo</label>
						<form:input type="number" min="0" step=".01" path="importo" cssClass="form-control" />
						<span style="color: red"><form:errors path="importo" /></span>
					</div>
					<div class="form-group">
						<label>Aliquota IVA</label>
						<form:select  path="aliquotaIva" cssClass="form-control">
						    <form:option value=""> --Seleziona Aliquota IVA--</form:option>
						    <c:forEach items="${listAliquote}" var="currentAliquota">
								<c:choose>
									<c:when test="${currentAliquota.id == Aliquota}">
										<form:option value="${currentAliquota}" selected="true">${currentAliquota.descrizione}</form:option>
									</c:when>
									<c:otherwise>
										<form:option value="${currentAliquota}">${currentAliquota.descrizione}</form:option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="aliquotaIva" /></span> 
					</div>
					<div class="form-group">
						<label>Preventivo</label>
						<form:select  path="preventivo" cssClass="form-control">
						    <form:option value=""> --Seleziona Preventivo--</form:option>
						    <c:forEach items="${listPreventivi}" var="currentPreventivo">
							    <c:choose>
									<c:when test="${currentPreventivo.id == Preventivo}">
										<form:option value="${currentPreventivo}" selected="true">${currentPreventivo.nomePreventivo}</form:option>
									</c:when>
									<c:otherwise>
										<form:option value="${currentPreventivo}">${currentPreventivo.nomePreventivo}</form:option>
									</c:otherwise>
								</c:choose>	
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="preventivo" /></span> 
					</div>
					<div class="form-group">
						<label>Spesa di Investimento</label>
						<form:select  path="spesaInvestimento" cssClass="form-control">
						    <form:option value=""> --Seleziona Spesa di Investimento--</form:option>
						    <c:forEach items="${listSpese}" var="currentSpesa">
						    	<c:choose>
									<c:when test="${currentSpesa.id == Spesa}">
										<form:option value="${currentSpesa}" selected="true">${currentSpesa.nomeSpesaInvestimento}</form:option>
									</c:when>
									<c:otherwise>
										<form:option value="${currentSpesa}">${currentSpesa.nomeSpesaInvestimento}</form:option>
									</c:otherwise>
								</c:choose>	
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="spesaInvestimento" /></span> 
					</div>
				<button type="submit" class="btn btn-primary">Conferma</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>