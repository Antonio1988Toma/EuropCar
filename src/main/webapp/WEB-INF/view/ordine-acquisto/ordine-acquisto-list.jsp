<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Inserimento degli Ordini</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-ordine-acquisto" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Menu</a>

	<div class="container">
		<div align="center">
			<h2>Inserimento degli Ordini</h2>	
			<br>
			<spring:url value="/ordine-acquisto/save" var="saveUrl"  />
			<form:form modelAttribute="listForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
<%-- 				<form:hidden path="importo" value="${Totale}" /> --%>
					<div class="form-group">
						<label>Descrizione</label>
						<form:input path="nomeOrdineAcquisto" cssClass="form-control" value="${OrdineAcquisto.nomeOrdineAcquisto}"/>
						<span style="color:red"><form:errors path="nomeOrdineAcquisto" /></span>
					</div>
					<div class="form-group">
						<label>Numero</label>
						<form:input path="numero" cssClass="form-control" value="${OrdineAcquisto.numero}"/>
						<span style="color:red"><form:errors path="numero" /></span>
					</div>
					<div class="form-group">
						<label>Data</label>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${OrdineAcquisto.data}" var="dateString"/>
						<form:input path="data" cssClass="form-control" value="${dateString}"/>
					</div>
					<div class="form-group">
						<label>Fornitore</label>
						<form:select  path="fornitore" cssClass="form-control">
						    <form:option value=""> --Seleziona Fornitore--</form:option>
						    <c:forEach items="${listFornitori}" var="currentFornitore">
						    		<c:choose>
										<c:when test="${OrdineAcquisto.fornitore.id == currentFornitore.id}">
											<form:option value="${currentFornitore}" selected="true">${currentFornitore.ragioneSociale}</form:option>
										</c:when>
									<c:otherwise>
										<form:option value="${currentFornitore}">${currentFornitore.ragioneSociale}</form:option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					    </form:select>
					</div>
				<spring:url value="/ordine-acquisto/add/" var="dettaglioUrl"  />
				<a class="btn btn-primary" href="${dettaglioUrl}" role="button">Nuova Spesa</a>
				<br><br>
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Spesa Investimeto</th>
										<th>Progetto</th>
										<th>Sottocategoria</th>
										<th>Importo</th>
										<th>Quantita'</th>
										<th>Importo Totale</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listDettagli}" var="currentDettaglio" varStatus="loop">
										<tr>
											<td>${currentDettaglio.spesaInvestimento.nomeSpesaInvestimento}</td>
											<td>${currentDettaglio.progetto.nomeProgetto}</td>
											<td>${currentDettaglio.spesaInvestimento.sottocategoria.sottocategoria}</td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.importo}" /></td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.quantita}" /></td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.importo * currentDettaglio.quantita}" /></td>
											<td>
												<c:choose>
													<c:when test="${chiaveFornitore != null}">
														<spring:url value="/ordine-acquisto/update/${loop.index}" var="updateOrdineUrl"  />
														<a class="btn btn-warning" href="${updateOrdineUrl}" role="button">Modifica</a>
													</c:when>
												</c:choose>
												<spring:url value="/ordine-acquisto/delete/${loop.index}" var="deleteUrl"  />
												<a class="btn btn-danger" href="${deleteUrl}" role="button">Elimina</a>
											</td>
										</tr>
									</c:forEach>
										<tr>
											<td colspan="5" align="right"><b>Totale : </b></td>
											<td colspan="2" align="left">${Totale}</td>
										</tr>
								</tbody>
							</table>
							<button type="submit" class="btn btn-success">Salva</button>
			</form:form>
		</div>
	</div>
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/webjars/popper.js/1.14.7/dist/umd/popper.min.js"></script>
	<script src="/js/scrolltable.js"></script>
</body>
</html>