<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Clientes</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<body>
  <%@ include file="/WEB-INF/encabezado.jspf" %>

  <c:choose>
    <c:when test="${param.msg == 'creado'}"><div class="msg">Cliente creado correctamente.</div></c:when>
    <c:when test="${param.msg == 'actualizado'}"><div class="msg">Cliente actualizado correctamente.</div></c:when>
    <c:when test="${param.msg == 'eliminado'}"><div class="msg">Cliente eliminado.</div></c:when>
    <c:when test="${param.msg == 'noencontrado'}"><div class="error">El cliente no existe.</div></c:when>
  </c:choose>

  <!-- Formulario con método GET: búsqueda por parámetro -->
  <form action="${pageContext.request.contextPath}/clientes" method="get">
    <input type="hidden" name="accion" value="listar">
    <input type="text" name="q" value="<c:out value='${q}'/>" placeholder="Buscar por nombre o correo">
    <button class="btn" type="submit">Buscar</button>
    <a class="btn" href="${pageContext.request.contextPath}/clientes?accion=nuevo">+ Nuevo cliente</a>
  </form>
  <br>

  <table>
    <tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Teléfono</th><th>Acciones</th></tr>
    <c:forEach var="cli" items="${clientes}">
      <tr>
        <td>${cli.id}</td>
        <td><c:out value="${cli.nombre}"/></td>
        <td><c:out value="${cli.correo}"/></td>
        <td><c:out value="${cli.telefono}"/></td>
        <td>
          <a class="btn" href="${pageContext.request.contextPath}/clientes?accion=editar&id=${cli.id}">Editar</a>
          <!-- Eliminar modifica datos: se hace por POST -->
          <form class="inline" action="${pageContext.request.contextPath}/clientes" method="post"
                onsubmit="return confirm('¿Eliminar este cliente?');">
            <input type="hidden" name="accion" value="eliminar">
            <input type="hidden" name="id" value="${cli.id}">
            <button class="btn btn-rojo" type="submit">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty clientes}">
      <tr><td colspan="5">No hay clientes para mostrar.</td></tr>
    </c:if>
  </table>
</body>
</html>
