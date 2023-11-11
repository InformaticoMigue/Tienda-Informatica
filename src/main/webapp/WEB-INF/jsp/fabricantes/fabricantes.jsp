<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Fabricante"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Optional" %>
<%@ page import="org.iesbelen.dao.FabricanteDAO" %>
<%@ page import="org.iesbelen.dto.FabricanteDTO" %>
<%@ include file="../../../boostrap.jspf" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fabricantes</title>
</head>
<body>
<style>
	.clearfix::after {
		content: "";
		display: block;
		clear: both;
	}
	<%@ include file="/WEB-INF/jsp/fragmentos/estilos.jspf" %>

</style>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

	<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Fabricantes</h1>
				<form action="${pageContext.request.contextPath}/tienda/fabricantes" method="get">
				Ordenar Por: <select name="ordenar-por">
					<option value="nombre">nombre</option>
					<option value="codigo">codigo</option>
				</select>
				Modo de ordenacion: <select name="modo-ordenar">
					<option value="asc">asc</option>
					<option value="desc">desc</option>
				</select>
					<input type="submit" value="Ordenar">
			</form>

			</div>
			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
				<div style="position: absolute; left: 39%; top : 39%;">
					
					<form action="${pageContext.request.contextPath}/tienda/fabricantes/crear">
						<input type="submit" value="Crear">
					</form>
				</div>
				
			</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
		<div class="clearfix" style="display: flex;flex-flow: row wrap;">
			<div style="margin-right: 10%">Código</div>
			<div style="margin-right: 20%">Nombre</div>
			<div style="margin-right: 6%">Cantidad de productos</div>
			<div>Acción</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
	<% 
        if (request.getAttribute("listaFabricantes") != null) {
            List<FabricanteDTO> listaFabricante = (List<FabricanteDTO>)request.getAttribute("listaFabricantes");
            for (FabricanteDTO fabricante : listaFabricante) {
    %>

		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 15%"><%= fabricante.getIdFabricante()%></div>
			<div style="float: left;width: 33%"><%= fabricante.getNombre()%></div>
			<div style="float: left; width: 15%"><%= fabricante.getCantProductos()%></div>

			<div style="float: none;width: auto;overflow: hidden;">
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/<%= fabricante.getIdFabricante()%>" style="display: inline;">
    				<input type="submit" value="Ver Detalle" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/editar/<%= fabricante.getIdFabricante()%>" style="display: inline;">
    				<input type="submit" value="Editar" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/borrar/" method="post" style="display: inline;">
					<input type="hidden" name="__method__" value="delete"/>
					<input type="hidden" name="codigo" value="<%= fabricante.getIdFabricante()%>"/>
    				<input type="submit" value="Eliminar" />
				</form>
			</div>
		</div>

	<% 
            }
        } else { 
    %>
		No hay registros de fabricante
	<% } %>
	</div>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>