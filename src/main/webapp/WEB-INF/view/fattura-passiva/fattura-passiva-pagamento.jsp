<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Pagamento Edit</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/fattura-passiva/update-fattura/${chiaveFattura}" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Pagamento Fattura</h2>
			<br>
			<spring:url value="/fattura-passiva/save-pagamento" var="pagamentoUrl"  />
			
			<form:form modelAttribute="pagamentoForm" method="post" action="${pagamentoUrl}" cssClass="form">
				<form:hidden path="id" />
				<form:hidden path="numero" />
				<form:hidden path="descrizione" />
				<form:hidden path="data" />
				<form:hidden path="fornitore" />
				<form:hidden path="pagato"/>
					<div class="form-group">
						<label><b>Importo da Pagare : </b>${DaPagare}</label>
					</div>
				<button type="submit" class="btn btn-success">Conferma</button>
			</form:form>	
		</div>
	</div>
	
</body>
</html>