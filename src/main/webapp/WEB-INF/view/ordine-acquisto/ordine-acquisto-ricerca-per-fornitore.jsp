<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RicercaPerFornitore</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/ordine-acquisto/ricerca" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Ricerca Per Fornitore</h2>
			<br>
			<table>
				<tr>
					<td> Fornitore 
						<select name="listaFornitori" onchange="caricaLista(this.value)">
							<option value="" selected = "selected">-- Seleziona Fornitore --</option>
							<c:choose>
								<c:when test="${chiaveFornitore == 0}">
									<option value="0" selected = "selected">TUTTI</option>
								</c:when>
								<c:otherwise>
									<option value="0">TUTTI</option>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${listFornitori}" var="currentFornitore">
								<c:choose>
									<c:when test="${currentFornitore.id == chiaveFornitore}">
										<option value="${currentFornitore.id}" selected = "selected">${currentFornitore.ragioneSociale}</option>
									</c:when>
									<c:otherwise>
										<option value="${currentFornitore.id}">${currentFornitore.ragioneSociale}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<script type="text/javascript" >
				function caricaLista(chiaveFornitore){
					window.location.href = "/ordine-acquisto/ricerca-fornitore/" + chiaveFornitore;
				}
			</script>	
			<br>
			<c:choose>
				<c:when test="${chiaveFornitore != null }">
					<c:choose>
						<c:when test="${listOrdiniFornitore.size()>0 }">										
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<c:choose>
											<c:when test="${chiaveFornitore == 0}">
												<th>Fornitore</th>
											</c:when>
										</c:choose>
										<th>Ordine di Acquisto</th>
										<th>Descrizione</th>
										<th>Sottocategoria</th>
										<th>Progetto</th>
										<th>Importo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listOrdiniFornitore}" var="currentOrdine">
										<tr>
											<c:choose>
												<c:when test="${chiaveFornitore == 0}">
													<td>${currentOrdine[5]}</td>
												</c:when>
											</c:choose>
											<td>${currentOrdine[0]}</td>
											<td>${currentOrdine[1]}</td>	
											<td>${currentOrdine[2]}</td> 
											<td>${currentOrdine[3]}</td> 
											<td>${currentOrdine[4]}</td> 
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							Non ci sono Ordini
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
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