<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Spesa Investimento Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/budget/search-spesa/${chiaveSottocategoria}" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<c:choose>
				<c:when test="${spesaForm.id>0}">
					<h2>Modifica Spesa di Investimento</h2>
				</c:when>
				<c:otherwise>
					<h2>Nuova Spesa di Investimento</h2>
				</c:otherwise>
			</c:choose>
			<br>
			<spring:url value="/budget/save-spesa" var="saveUrl"  />
			
			<form:form modelAttribute="spesaForm" method="post" action="${saveUrl}" cssClass="form">
				<form:hidden path="id" />
					<div class="form-group">
						<label><b>Sottocategoria/Area : </b></label>
						<form:hidden path="sottocategoria" value="${Sottocategoria.id}" />
						<label>${Sottocategoria.sottocategoria}/${Sottocategoria.area.nomeArea}</label>
						<span style="color:red"><form:errors path="sottocategoria" /></span>
					</div>
					<div class="form-group">
						<label>Progetto</label>
						<form:select  path="progetto" cssClass="form-control">
						    <form:option value=""> --Seleziona Progetto--</form:option>
						    <c:forEach items="${listProgetti}" var="currentProgetto">
								<form:option value="${currentProgetto}">${currentProgetto.nomeProgetto}</form:option>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="progetto" /></span>
					</div>
					<div class="form-group">
						<label>Spesa Investimento</label>
						<form:input path="nomeSpesaInvestimento" cssClass="form-control" />
						<span style="color:red"><form:errors path="nomeSpesaInvestimento" /></span>
					</div>
				<button type="submit" class="btn btn-success">Salva</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>