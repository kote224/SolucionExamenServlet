<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de datos</title>
</head>
<body>
	<form action="InsertEmpresa" method="post">
		<span>id:</span> 
		<input type="text" name="id"><br/>
		<span>nombre:</span> 
		<input type="text" name="nombre"> <br/>
		<span>fecha:</span> 
		<input type="string" name="fecha"><br/>
		<input type="submit">
	</form>
	</body>
</html>