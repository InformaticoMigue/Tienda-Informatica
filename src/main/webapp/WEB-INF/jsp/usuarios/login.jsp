<%--
  Created by IntelliJ IDEA.
  User: Propietario
  Date: 16/11/2023
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<style>
    <%@ include file="/WEB-INF/jsp/fragmentos/estilos.jspf" %>
</style>

<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<div class="form-group w-25 m-0 m-auto shadow-lg p-3 mb-5 bg-white rounded mt-5 ">
    <form action="${pageContext.request.contextPath}/tienda/login" method="post">

        <h3 class="pb-3 ">FORMULARIO</h3>
        <label>Username</label>
            <input name="username" type="text" class="form-control" placeholder="Enter username">
        <label>Password</label>
            <input name="password" type="password" class="form-control" placeholder="Enter password">
        <input type="submit" class="form-control w-25 mt-3 ">
    </form>
</div>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
