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
			<h2>Definizione del Budget</h2>
			<br>
			<table>
				<tr>
					<td> Area
						<select name="listaAree" onchange="caricaLista(this.value)">
							<option value="" >-- Seleziona Area --</option>
							<c:forEach items="${listAree}" var="currentArea">
								<c:choose>
									<c:when test="${currentArea.id == chiaveArea}">
										<option value="${currentArea.id}" selected = "selected">${currentArea.nomeArea}</option>
									</c:when>
									<c:otherwise>
										<option value="${currentArea.id}">${currentArea.nomeArea}</option>
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
				function caricaLista(chiaveArea){
					if(chiaveArea!="")
						window.location.href = "/budget/search/" + chiaveArea;
				}
			</script>	
			
			<c:choose>
				<c:when test="${chiaveArea != null}">
					<c:choose>
						<c:when test="${listSottocategorie.size()>0 }">
							<table class="table table-striped table-bordered mydatatable" style="width: 100%">
								<thead>
									<tr>
										<th>Codice</th>
										<th>Sottocategoria</th>
										<th>Budget</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listSottocategorie}" var="currentSottocategoria">
										<tr>
											<td>${currentSottocategoria.codice}</td>
											<td>${currentSottocategoria.sottocategoria}</td>
											<td>${currentSottocategoria.budget}</td>
											<td>
												<spring:url value="/budget/definisci/${currentSottocategoria.id}" var="updateUrl"  />
												<a class="btn btn-warning" href="${updateUrl}" role="button">Definizione</a>
											</td>
										</tr>
									</c:forEach>
										<tr>
											<td colspan="2" align="right"><b>Totale : </b></td>
											<td colspan="2" align="left">${Totale}</td>
										</tr>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							Non ci sono Sottocategorie per quest'Area
						</c:otherwise>
					</c:choose>
					<br>
					<spring:url value="/budget/sottocategoria/${chiaveArea}" var="sottocategoriaUrl"  />
					<a class="btn btn-primary" href="${sottocategoriaUrl}" role="button">Sottocategoria</a>
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