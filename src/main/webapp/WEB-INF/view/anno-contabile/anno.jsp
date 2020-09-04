<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Anno Contabile</title>
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function caricaMenu(idAnno) {
			if (idAnno != "")
				window.location.href = "/anno-contabile/vai-al-menu/" + idAnno;
		}
	</script>
</head>
<body>
<%-- 	<spring:url value="/menu/pricipale" var="backUrl"  /> --%>
<%-- 	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a> --%>

	<div class="container">
		<div align="center">
			<h2>Seleziona Anno Contabile</h2>
			<br>
			<select name="listAnni" onchange="caricaMenu(this.value)">
				<option value="" selected = "selected">-- Seleziona Anno Contabile --</option>
					<c:forEach items="${listAnni}" var="currentAnno">
						<option value="${currentAnno.id}">${currentAnno.descrizione}</option>
					</c:forEach>
			</select>
			<br><br>
<%-- 			<spring:url value="/anno-contabile/vai-al-menu" var="avantiUrl"  /> --%>
<%-- 			<a class="btn btn-primary" href="${avantiUrl}" role="button">Avanti</a>	 --%>
		</div>
	</div>
	
</body>
</html>