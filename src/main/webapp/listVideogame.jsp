cv<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de usuario</title>
</head>
<body>
	<form action="ListadoVideojuego" method="post">
		<input type="submit" value="ver listado">
	</form>

	<table border="1">
		<thead>
			<tr>
				<td>Nombre</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="empresa" items="${empresa}">
				<tr>
					<td><c:out value="${empresa.nombre}" /></td>
					<td><a href="/delete?id=${empresa.nombre}">borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
		
		
		<thead>
			<tr>
				<td>Fecha</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="empresa" items="${empresa}">
				<tr>
					<td><c:out value="${empresa.fecha}" /></td>
					<td><a href="/delete?id=${empresa.fecha}">borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<select>
		<c:forEach var="pais" items="${empresa}">
			<option value="${empresa.fecha}">${empresa.fecha}</option>
		</c:forEach>
	</select>

</body>
</html>