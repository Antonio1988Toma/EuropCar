<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Definizione List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-budget" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Spesa di Investimento</h2>
			<br>
			<table>
				<tr>
					<td> Area
						<select name="listaSottocategoria" onchange="caricaLista(this.value)">
							<option value="">-- Seleziona Sottocategoria --</option>
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
<!-- 					<td> -->
<%-- 						<spring:url value="/budget/search/" var="searchUrl"  /> --%>
<%-- 						<a class="btn btn-success" href="${searchUrl}"  role="button">Cerca</a> --%>
<!-- 					</td> -->
				</tr>
			</table>
			<script type="text/javascript" >
				function caricaLista(chiaveSottocategoria){
					if(chiaveSottocategoria!="")
						window.location.href = "/budget/search-spesa/" + chiaveSottocategoria;
				}
			</script>	
			<br>
			<c:choose>
				<c:when test="${chiaveSottocategoria != null }">
					<spring:url value="/budget/add-spesa/${chiaveSottocategoria}" var="addSpesaUrl"  />
					<a class="btn btn-primary" href="${addSpesaUrl}" role="button">Nuova</a>
					<br>
					<c:choose>
						<c:when test="${listSpeseInvestimento.size()>0 }">										
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Spesa Investimento</th>
										<th>Sottocategoria/Area</th>
										<th>Progetto</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listSpeseInvestimento}" var="currentSpesa">
										<tr>
											<td>${currentSpesa.nomeSpesaInvestimento}</td>
											<td>${currentSpesa.sottocategoria.sottocategoria}/${currentSpesa.sottocategoria.area.nomeArea}</td>
											<td>${currentSpesa.progetto.nomeProgetto}</td>
											<td>
												<spring:url value="/budget/update-spesa/${currentSpesa.id}" var="updateSpesaUrl"  />
												<a class="btn btn-warning" href="${updateSpesaUrl}" role="button">Modifica</a>
											</td>
											<td>
												<spring:url value="/budget/delete-spesa/${currentSpesa.id}" var="deleteSpesaUrl"  />
												<a class="btn btn-danger" href="${deleteSpesaUrl}" role="button" onclick="return confirm('Eliminare la Spesa selezionata?')">Elimina</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							Non ci sono Spese di Investimento
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