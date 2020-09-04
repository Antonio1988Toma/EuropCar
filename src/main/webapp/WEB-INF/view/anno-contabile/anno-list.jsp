<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Anno Contabile List</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<spring:url value="/menu/principale" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Gestione Anno Contabile</h2>
			<br>
			<spring:url value="/anno-contabile/anno-add" var="addUrl"  />
			<a class="btn btn-success" href="${addUrl}" role="button">Genera Anno Contabile</a>
			<br><br>
			<c:choose>
				<c:when test="${listAnni.size()>0 }">
					<table class="table table-striped table-bordered mydatatable" style="width: 100%">
						<thead>
							<tr>
								<th>Descrizione</th>
								<th>Data Inizio</th>
								<th>Data Fine</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listAnni}" var="currentAnno">
								<tr>
									<td>${currentAnno.descrizione}</td>				
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${currentAnno.dataInizio}" /></td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${currentAnno.dataFine}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					Non ci sono Anni Contabili
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