<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Tipo Venditore List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/utente/venditore-list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Gestione Tipo Venditore</h2>
			<br>
			<spring:url value="/utente/tipo-venditore-add" var="addUrl"  />
			<a class="btn btn-success" href="${addUrl}" role="button">Nuovo</a>
			<br><br>
			<c:choose>
				<c:when test="${listTipi.size()>0 }">
					<table class="table table-striped table-bordered mydatatable" style="width: 100%">
						<thead>
							<tr>
								<th>Tipo Venditore</th>
								<th colspan="2"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTipi}" var="currentTipo">
								<tr>
									<td>${currentTipo.tipoVenditore}</td>				
									<td>
										<spring:url value="/utente/tipo-venditore-update/${currentTipo.id}" var="updateUrl"  />
										<a class="btn btn-warning" href="${updateUrl}" role="button">Modifica</a>
									</td>
									<td>
										<spring:url value="/utente/tipo-venditore-delete/${currentTipo.id}" var="deleteUrl"  />
										<a class="btn btn-danger" href="${deleteUrl}" role="button" onclick="return confirm('Eliminare il Tipo selezionato?')">Elimina</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					Non ci sono Tipi di Venditore
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