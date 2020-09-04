<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>AliquotaIva Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/aliquotaIva/list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${aliquotaIvaForm.id>0}">
					<h2>Modifica Aliquota IVA</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuova Aliquota IVA</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/aliquotaIva/save" var="saveUrl"  />
			
			<form:form modelAttribute="aliquotaIvaForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Aliquota Iva</label>
						<form:input path="aliquota" cssClass="form-control" />
						<span style="color: red"><form:errors path="aliquota" /></span>
					</div>
					<div class="form-group">
						<label>Descrizione</label>
						<form:input path="descrizione" cssClass="form-control" />
						<span style="color: red"><form:errors path="descrizione" /></span>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>