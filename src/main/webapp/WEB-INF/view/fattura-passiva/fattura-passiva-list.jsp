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
	<title>Inserimento delle Fatture</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-fattura-passiva" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Menu</a>

	<div class="container">
		<div align="center">
			<h2>Inserimento delle Fatture</h2>	
			<br>
			<spring:url value="/fattura-passiva/save" var="saveUrl"  />
			<form:form modelAttribute="listForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
				<form:hidden path="pagato" />
					<div class="form-group">
						<label>Numero</label>
						<form:input path="numero" cssClass="form-control" value="${FatturaPassiva.numero}"/>
						<span style="color:red"><form:errors path="numero" /></span>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label>Descrizione</label> -->
<%-- 						<form:input path="descrizione" cssClass="form-control" value="${FatturaPassiva.descrizione}"/> --%>
<%-- 						<span style="color:red"><form:errors path="descrizione" /></span> --%>
<!-- 					</div> -->
					<div class="form-group">
						<label>Data</label>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${FatturaPassiva.data}" var="dateString"/>
						<form:input path="data" cssClass="form-control" value="${dateString}"/>
					</div>
					<div class="form-group">
						<label>Fornitore</label>
						<form:select  path="fornitore" cssClass="form-control">
						    <form:option value=""> --Seleziona Fornitore--</form:option>
						    <c:forEach items="${listFornitori}" var="currentFornitore">
						    		<c:choose>
										<c:when test="${FatturaPassiva.fornitore.id == currentFornitore.id}">
											<form:option value="${currentFornitore}" selected="true">${currentFornitore.ragioneSociale}</form:option>
										</c:when>
									<c:otherwise>
										<form:option value="${currentFornitore}">${currentFornitore.ragioneSociale}</form:option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					    </form:select>
					</div>
				<spring:url value="/fattura-passiva/add/" var="dettaglioUrl"  />
				<a class="btn btn-primary" href="${dettaglioUrl}" role="button">Nuovo Dettaglio</a>
				<c:choose>
					<c:when test="${chiaveFornitore != null}">
						<spring:url value="/fattura-passiva/pagamento/" var="pagamentoUrl" />
						<a class="btn btn-dark" href="${pagamentoUrl}" role="button">Pagamento</a>
					</c:when>
				</c:choose>
				<br><br>
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Dettaglio Fattura</th>
										<th>Num Preventivo</th>
										<th>Importo</th>
										<th>Imponibile</th>
										<th>IVA</th>
										<th>Importo IVA</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listDettagli}" var="currentDettaglio" varStatus="loop">
										<tr>
											<td>${currentDettaglio.dettaglioFattura}</td>
											<td>${currentDettaglio.preventivo.codice}</td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.importo}" /></td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.imponibile}" /></td>
											<td>${currentDettaglio.aliquotaIva.descrizione}</td>
											<td><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${currentDettaglio.importo - currentDettaglio.imponibile}" /></td>
											<td>
												<c:choose>
													<c:when test="${chiaveFornitore != null}">
														<spring:url value="/fattura-passiva/update/${loop.index}" var="updateFatturaUrl"  />
														<a class="btn btn-warning" href="${updateFatturaUrl}" role="button">Modifica</a>
													</c:when>
												</c:choose>
												<spring:url value="/fattura-passiva/delete/${loop.index}" var="deleteUrl"  />
												<a class="btn btn-danger" href="${deleteUrl}" role="button">Elimina</a>
											</td>
										</tr>
									</c:forEach>
										<tr>
											<td colspan="7" align="left"><b>Dettaglio </b></td>
										</tr>
										<tr>
											<td colspan="7" align="left"><b>Totale Importo : </b><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${TotaleImporto}" /></td>
										</tr>
										<tr>
											<td colspan="7" align="left"><b>Totale Imponibile : </b><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${TotaleImponibile}" /></td>
										</tr>
										<tr>
											<td colspan="7" align="left"><b>Totale IVA  : </b><fmt:formatNumber type="number" pattern="###.##" maxFractionDigits="2" value="${TotaleIVA}" /></td>
										</tr>
								</tbody>
							</table>
							<button type="submit" class="btn btn-success">Salva</button>
							<c:choose>
								<c:when test="${chiaveFornitore != null}">
									<spring:url value="/fattura-passiva/annulla/" var="annullaUrl" />
									<a class="btn btn-dark" href="${annullaUrl}" role="button">Annulla</a>
								</c:when>
							</c:choose>
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