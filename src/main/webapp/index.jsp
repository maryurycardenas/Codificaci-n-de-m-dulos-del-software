<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Inicio - Módulo Clientes</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<body>
  <h1>Módulo de Gestión de Clientes</h1>
  <p>Aplicación web con Servlets y JSP. Fecha actual (expresión JSP): <%= LocalDate.now() %></p>
  <p><a class="btn" href="${pageContext.request.contextPath}/clientes">Ir al listado de clientes</a></p>
</body>
</html>
