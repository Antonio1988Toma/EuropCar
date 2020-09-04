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
	<spring:url value="/fattura-passiva/ricerca" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Ricerca Per Fornitore</h2>
			<br>
			<table>
				<tr>
					<td> Fornitore 
						<select name="listaFornitori" onchange="caricaLista(this.value)">
							<option value="">-- Seleziona Fornitore --</option>
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
					if(chiaveFornitore!="")
						window.location.href = "/fattura-passiva/ricerca-fornitore/" + chiaveFornitore;
				}
			</script>	
			<br>
			<c:choose>
				<c:when test="${chiaveFornitore != null }">
					<c:choose>
						<c:when test="${listFattureFornitore.size()>0 }">										
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Data Fattura</th>
										<th>Numero Fattura</th>
										<th>Importo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listFattureFornitore}" var="currentFattura">
										<tr>
											<td>${currentFattura[0]}</td>
											<td>${currentFattura[1]}</td>	
											<td>${currentFattura[2]}</td> 
										</tr>
									</c:forEach>
<%-- 									<c:forEach items="${listFattureFornitore}" var="currentFattura"> --%>
<!-- 										<tr> -->
<%-- 											<td><fmt:formatDate pattern="dd/MM/yyyy" value="${currentFattura.data}" /></td> --%>
<%-- 											<td>${currentFattura.numero}</td>	 --%>
<%-- 											<td>${currentFattura.importo}</td> --%> 
<!-- 										</tr> -->
<%-- 									</c:forEach> --%>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							Non ci sono Fatture
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