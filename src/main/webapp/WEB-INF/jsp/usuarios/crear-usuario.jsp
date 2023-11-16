<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Fabricante"%>
<%@page import="java.util.Optional"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Usuario</title>
<style>
.clearfix::after {
	content: "";
	display: block;
	clear: both;
}

</style>
</head>
<body>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
	<form action="${pageContext.request.contextPath}/tienda/usuarios/crear/" method="post">
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Crear Producto</h1>
			</div>
			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
				
				<div style="position: absolute; left: 39%; top : 39%;">								
					<input type="submit" value="Crear"/>					
				</div>
				
			</div>
		</div>
		
		<div class="clearfix">
			<hr/>
		</div>
		
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 50%">
				Usuario
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<input name="usuario" />
			</div> 
		</div>

		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 50%">
				Contraseña
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<input name="contrasena" />
			</div>
		</div>

		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 50%">
				Rol
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<select name="rol">
					<option value="Cliente">Cliente</option>
					<option value="Vendedor">Vendedor</option>
					<option value="Administrador">Administrador</option>
				</select>
			</div>
		</div>
	</form>
</div>

</body>
</html>