<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Progetto List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/sottomenu-archivio" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Gestione Progetto</h2>
			<br>
			<spring:url value="/progetto/add" var="addUrl"  />
			<a class="btn btn-success" href="${addUrl}" role="button">Nuovo</a>
			<br><br>
			<c:choose>
				<c:when test="${list.size()>0 }">
					<table class="table table-striped table-bordered mydatatable" style="width: 100%">
						<thead>
							<tr>
								<th>Codice</th>
								<th>Progetto</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="currentProgetto">
								<tr>
									<td>${currentProgetto.codice}</td>
									<td>${currentProgetto.nomeProgetto}</td>
									<td>
										<spring:url value="/progetto/update/${currentProgetto.id }" var="updateUrl"  />
										<a class="btn btn-warning" href="${updateUrl}" role="button">Modifica</a>
									</td>
									<td>
										<spring:url value="/progetto/delete/${currentProgetto.id }" var="deleteUrl"  />
										<a class="btn btn-danger" href="${deleteUrl}" role="button" onclick="return confirm('Eliminare il Progetto selezionato?')">Elimina</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					Non ci sono Progetti
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