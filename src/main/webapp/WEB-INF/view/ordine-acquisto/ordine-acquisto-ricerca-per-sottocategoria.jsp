<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>RicercaPerSottocategoria</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/ordine-acquisto/ricerca" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Ricerca Per Sottocategoria</h2>
			<br>
			<table>
				<tr>
					<td> Sottocategoria 
						<select name="listSottocategorie" onchange="caricaLista(this.value)">
							<option value="" selected = "selected">-- Seleziona Sottocategoria/Area --</option>
							<c:choose>
								<c:when test="${chiaveSottocategoria == 0}">
									<option value="0" selected = "selected">TUTTE</option>
								</c:when>
								<c:otherwise>
									<option value="0">TUTTE</option>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${listSottocategorie}" var="currentSottocategoria">
								<c:choose>
									<c:when test="${currentSottocategoria.id == chiaveSottocategoria}">
										<option value="${currentSottocategoria.id}" selected = "selected">${currentSottocategoria.sottocategoria} / ${currentSottocategoria.area.nomeArea}</option>
									</c:when>
									<c:otherwise>
										<option value="${currentSottocategoria.id}">${currentSottocategoria.sottocategoria} / ${currentSottocategoria.area.nomeArea}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<script type="text/javascript" >
				function caricaLista(chiaveSottocategoria){
					if(chiaveSottocategoria!="")
						window.location.href = "/ordine-acquisto/ricerca-sottocategoria/" + chiaveSottocategoria;
				}
			</script>	
			<br>
			<c:choose>
				<c:when test="${chiaveSottocategoria != null }">
					<c:choose>
						<c:when test="${listOrdiniSottocategoria.size()>0 }">										
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Fornitore</th>
										<th>Ordine di Acquisto</th>
										<th>Spesa Investimento</th>
										<th>Fornitore</th>
										<th>Progetto</th>
										<th>Importo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listOrdiniSottocategoria}" var="currentOrdine">
										<tr>
											<td>${currentOrdine[5]}</td>
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
							Non ci sono Ordini per questa sottocategoria
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