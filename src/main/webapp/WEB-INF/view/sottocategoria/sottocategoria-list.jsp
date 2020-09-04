<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sottocategoria List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-archivio" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Gestione Sottocategoria</h2>
			<br>
			<spring:url value="/sottocategoria/add" var="addUrl"  />
			<a class="btn btn-success" href="${addUrl}" role="button">Nuova</a>
			<br><br>
			<c:choose>
				<c:when test="${list.size()>0 }">
					<table class="table table-striped table-bordered mydatatable" style="width: 100%">
						<thead>
							<tr>
								<th>Codice</th>
								<th>Sottocategoria</th>
								<th>Area</th>
								<th>Anno Contabile</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="currentSottocategoria">
								<tr>
									<td>${currentSottocategoria.codice}</td>
									<td>${currentSottocategoria.sottocategoria}</td>
									<td>${currentSottocategoria.area.nomeArea}</td>
									<td>${currentSottocategoria.area.annoContabile.descrizione}</td>
									<td>
										<spring:url value="/sottocategoria/update/${currentSottocategoria.id }" var="updateUrl" /> 
										<a class="btn btn-warning"href="${updateUrl}" role="button">Modifica</a>
									</td>
									<td>
										<spring:url value="/sottocategoria/delete/${currentSottocategoria.id }" var="deleteUrl" /> 
										<a class="btn btn-danger" href="${deleteUrl}" role="button" onclick="return confirm('Eliminare la Sottocategoria selezionata?')">Elimina</a>
									</td>
								</tr>
							</c:forEach>
							<c:choose>
								<c:when test="${chiaveArea != null}">
									<tr>
										<td colspan="5" align="center">
										<spring:url value="/sottocategoria/budget/${chiaveArea}" var="budgetUrl" />
											<a class="btn btn-secondary" href="${budgetUrl}" role="button">Budget</a></td>
									</tr>
								</c:when>
							</c:choose>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					Non ci sono Sottocategorie
				</c:otherwise>
			</c:choose>
			<br>		
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