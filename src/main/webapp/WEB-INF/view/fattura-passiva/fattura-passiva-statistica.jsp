<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Statistica</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
</head>
<body>
	<spring:url value="/menu/sottomenu-fattura-passiva" var="backUrl"  />
	<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>

	<div class="container">
		<div align="center">
			<h2>Statistica</h2>
			<br>		
			<spring:url value="/fattura-passiva/statistica-spesa" var="statUrl"  />
				<form method="post" action="${statUrl}" name="cercaForm">
					<table>
						<tr>
							<td>
								<select name="listaSotto" id="listaSotto" onchange="caricaDati(this.value)">
									<option value="">-- Seleziona Sottocategoria --</option>
									<c:forEach items="${listSottocategorie}" var="currentSottocategoria">
										<c:choose>
											<c:when test="${currentSottocategoria.id == chiaveSottocategoria}">
												<option value="${currentSottocategoria.id}" selected>${currentSottocategoria.sottocategoria} / ${currentSottocategoria.area.nomeArea}</option>
											</c:when>
											<c:otherwise>
												<option value="${currentSottocategoria.id}">${currentSottocategoria.sottocategoria} / ${currentSottocategoria.area.nomeArea}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td>
								<select name="listaSpese" id="listaSpese">
										<option value="">-- Seleziona SpesaInvestimento --</option>
										<c:forEach items="${listSpese}" var="currentSpesa">
											<c:choose>
												<c:when test="${currentSpesa.id == chiaveSpesa}">
													<option value="${currentSpesa.id}" selected>${currentSpesa.nomeSpesaInvestimento}</option>
												</c:when>
												<c:otherwise>
													<option value="${currentSpesa.id}">${currentSpesa.nomeSpesaInvestimento}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select>
							</td>
							<td><b>Dal : </b><input type="text" name="dataDal" id="dataDal" /></td>
							<td><b>Al : </b><input type="text" name="dataAl" id="dataAl" /></td>
						</tr>
						<tr>
							<td colspan="4"	align="center">
								<button type="submit" class="btn btn-primary" onclick="Conferma()">Cerca</button>
							</td>
						</tr>
					</table>
				</form>
				<script type="text/javascript">
					function caricaDati(chiaveSottocategoria) {
						if(chiaveSottocategoria!="")
							window.location.href = "/fattura-passiva/statistica-sottocategoria/" + chiaveSottocategoria;
					}
					

					function Conferma() {
						var frm = document.forms['cercaForm'];
						if(frm.dataDal.value == '' || frm.dataAl.value == '' || frm.listaSpese.value == '' || frm.listaSotto == ''){
							alert('Tutti i campi sono obbligatori !');
						}	
						else{
							frm.submit();
						}
					}
				</script>
			<c:choose>
				<c:when test="${chiaveSpesa != null }">
					<table>
						<tr>
							<td><b>Sottocategoria</b></td>
							<td><b>Spesa Investimento</b></td>
							<td></td>
						</tr>
						<tr>
							<td>${Sottocategoria}</td>
							<td>${SpesaInvestimento}</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2"><b>Data</b></td>
							<td></td>
						</tr>
						<tr>
							<td><b>Dal </b>${Dal}</td>
							<td><b>Al </b>${Al}</td>
							<td></td>
						</tr>
						<tr>
							<td><b>Totale Ordinato</b></td>
							<td><b>Totale Fatturato</b></td>
							<td><b>Differenza</b></td>
						</tr>
						<tr>
							<td>${TotOrdinato}</td>
							<td>${TotFatturato}</td>
							<td>${Differenza}</td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</div>
	</div>
	
</body>
</html>