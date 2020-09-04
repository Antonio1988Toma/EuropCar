<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<div class="container">
		<div align="center">
			<h2>Login</h2>
			<br>
			<spring:url value="/utente/login" var="loginUrl"  />
			<form:form modelAttribute="loginForm" method="post" action="${loginUrl}" cssClass="form">
				<c:choose>
					<c:when test="${Accesso == 'Errato'}">
						<span style="color:red">Dati di Accesso Errati !!!!</span> 
					</c:when>
				</c:choose>
				<form:hidden path="id" />
					<div class="form-group">
						<label>Username</label>
						<form:input path="username" cssClass="form-control" />
						<span style="color: red"><form:errors path="username" /></span>
					</div>
					<div class="form-group">
						<label>Password</label>
						<form:password path="password" cssClass="form-control" />
						<span style="color: red"><form:errors path="password" /></span>
					</div>
					<div class="form-group">
						<label>Venditore</label>
						<form:select  path="venditore" cssClass="form-control">
						    <form:option value=""> --Seleziona Venditore--</form:option>
						    <c:forEach items="${listVenditori}" var="currentVenditore">
						    	<form:option value="${currentVenditore}">${currentVenditore.cognome} ${currentVenditore.nome}</form:option>
							</c:forEach>
					    </form:select>
					    <span style="color:red"><form:errors path="venditore" /></span> 
					    <spring:url value="/utente/venditore-list" var="tipoUrl"  />
					</div>
				<button type="submit" class="btn btn-success">Login</button>
				<spring:url value="/utente/utente-registrazione" var="registraUrl"  />
				<a class="btn btn-primary" href="${registraUrl}" role="button">Registrazione</a>
			</form:form>	
		</div>
	</div>
	
</body>
</html>