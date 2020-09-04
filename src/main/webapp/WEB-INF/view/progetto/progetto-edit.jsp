<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Progetto Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/progetto/list" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${progettoForm.id>0}">
					<h2>Modifica Progetto</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuovo Progetto</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/progetto/save" var="saveUrl"  />
			
			<form:form modelAttribute="progettoForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label>Progetto</label>
						<form:input path="nomeProgetto" cssClass="form-control" />
						<span style="color: red"><form:errors path="nomeProgetto" /></span>
					</div>
					<div class="form-group">
						<label>Codice</label>
						<form:input path="codice" cssClass="form-control" />
						<span style="color: red"><form:errors path="codice" /></span>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>