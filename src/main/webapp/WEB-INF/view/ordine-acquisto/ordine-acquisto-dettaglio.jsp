<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dettaglio Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<c:choose>
		<c:when test="${chiaveOrdine == null }">
			<spring:url value="/ordine-acquisto/creazione" var="backUrl"  />
		</c:when>
		<c:otherwise>
			<spring:url value="/ordine-acquisto/update-ordine/${chiaveOrdine}" var="backUrl"  />
		</c:otherwise>
	</c:choose>
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${chiaveOrdine != null}">
					<h2>Modifica Dettaglio Ordine</h2>
				</c:when>
				<c:otherwise>
					<h2>Inserimento Dettaglio Ordine</h2>
				</c:otherwise>
			</c:choose>
			<br>
			
			<c:choose>
				<c:when test="${chiaveOrdine == null}">
					<spring:url value="/ordine-acquisto/list" var="listUrl"  />
				</c:when>
				<c:otherwise>
					<spring:url value="/ordine-acquisto/update-ordine/${chiaveOrdine}" var="listUrl"  />
				</c:otherwise>
			</c:choose>
			
			<form:form modelAttribute="dettaglioForm" method="post" action="${listUrl}" cssClass="form">
				<form:hidden path="id" />
				<form:select path="spesaInvestimento" cssClass="form-control" onchange="caricaDati(this.value)">
					<form:option value=""> --Seleziona Spesa Investimento--</form:option>
					<c:forEach items="${listSpese}" var="currentSpesa">
						<c:choose>
							<c:when test="${currentSpesa.id == chiaveSpesa}">
								<form:option value="${currentSpesa}" selected="true">${currentSpesa.nomeSpesaInvestimento}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${currentSpesa}">${currentSpesa.nomeSpesaInvestimento}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<span style="color:red"><form:errors path="spesaInvestimento" /></span>
				<script type="text/javascript">
					function caricaDati(chiaveSpesa) {
						window.location.href = "/ordine-acquisto/dettaglio/" + chiaveSpesa;
					}
				</script>
				<c:choose>
					<c:when test="${chiaveSpesa != null}">
						<form:hidden path="progetto" value="${IdProgetto}"/>
						<label><b>Progetto : </b>${Progetto}</label>
						<label><b>Sottocategoria : </b>${Sottocategoria}</label>
					</c:when>
				</c:choose>
				<div class="form-group">
					<label>Importo</label>
					<form:input type="number" min="0" step=".01" path="importo" cssClass="form-control" />
					<span style="color: red"><form:errors path="importo" /></span>
				</div>
				<div class="form-group">
					<label>Quantita</label>
					<form:input type="number" min="0" step=".01" path="quantita" cssClass="form-control" />
					<span style="color: red"><form:errors path="quantita" /></span>
				</div>
				<button type="submit" class="btn btn-primary">Conferma</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>