<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registrazione</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/utente/utente-login" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Registrazione</h2>
			<br>
			<spring:url value="/utente/registrazione-save" var="registraUrl"  />
			
			<form:form modelAttribute="registrazioneForm" method="post" action="${registraUrl}" cssClass="form">
					<div class="form-group">
						<label>Admin</label>
						<form:input path="admin" cssClass="form-control" />
						<span style="color:red"><form:errors path="admin" /></span>
					</div>
					<div class="form-group">
						<label>Username</label>
						<form:input path="username" cssClass="form-control" />
						<span style="color:red"><form:errors path="username" /></span>
					</div>
					<div class="form-group">
						<label>Password</label>
						<form:password path="password"  cssClass="form-control" />
						<span style="color:red"><form:errors path="password" /></span>
					</div>
					<div class="form-group">
						<label>Venditore</label>
						<form:select  path="venditore" cssClass="form-control">
						    <form:option value=""> --Seleziona Venditore--</form:option>
						    <c:forEach items="${listVenditori}" var="currentVenditore">
						    	<c:choose>
						    		<c:when test="${currentVenditore.id == chiaveVenditore}">
						    			<form:option value="${currentVenditore}" selected="true">${currentVenditore.cognome} ${currentVenditore.nome}</form:option>
						    		</c:when>
						    		<c:otherwise>
						    			<form:option value="${currentVenditore}">${currentVenditore.cognome} ${currentVenditore.nome}</form:option>
						    		</c:otherwise>
						    	</c:choose>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="venditore" /></span> 
					    <spring:url value="/utente/venditore-list" var="tipoUrl"  />
					    <br>
						<a class="btn btn-primary" href="${tipoUrl}" role="button">Venditore</a>
					</div>
				<button type="submit" class="btn btn-success">Registra</button>
			</form:form>	
		</div>
	</div>
</body>
</html>