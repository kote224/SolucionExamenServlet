<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<form action="ListadoConsola" method="post">
		<input type="submit" value="ver listado">
	</form>

	<table border="1">
		<thead>
			<tr>
				<td>Nombre</td>
				<td>Empresa</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="consola" items="${consola}">
				<tr>
					<td><c:out value="${consola.nombre}" /></td>
					<td><c:out value="${consola.nomEMpresa}" /></td>
					<td><a href="/DeleteConsolas?id=${consola.nombre}">borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tbody>
			<a href="/ConsolasOrdenadas}"></a><intput type="button"></intput></a>
			
		</tbody>
	</table>

	<select>
		<c:forEach var="consolas" items="${consola}">
			<option value="${consola.nombre}">${consola.nombre}</option>
		</c:forEach>
	</select>

</body>
</html>