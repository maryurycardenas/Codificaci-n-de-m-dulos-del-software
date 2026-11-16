<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Formulario de cliente</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<body>
  <%@ include file="/WEB-INF/encabezado.jspf" %>
  <h2><c:out value="${cliente.id == 0 ? 'Nuevo cliente' : 'Editar cliente'}"/></h2>

  <c:if test="${not empty error}">
    <div class="error"><c:out value="${error}"/></div>
  </c:if>

  <!-- Formulario HTML enviado por POST al servlet -->
  <form action="${pageContext.request.contextPath}/clientes" method="post">
    <input type="hidden" name="accion" value="guardar">
    <input type="hidden" name="id" value="${cliente.id}">

    <label for="nombre">Nombre</label>
    <input type="text" id="nombre" name="nombre" value="<c:out value='${cliente.nombre}'/>" required>

    <label for="correo">Correo electrónico</label>
    <input type="email" id="correo" name="correo" value="<c:out value='${cliente.correo}'/>" required>

    <label for="telefono">Teléfono</label>
    <input type="text" id="telefono" name="telefono" value="<c:out value='${cliente.telefono}'/>"
           pattern="\d{7,10}" title="Entre 7 y 10 dígitos" required>

    <br><br>
    <button class="btn" type="submit">Guardar</button>
    <a class="btn btn-rojo" href="${pageContext.request.contextPath}/clientes">Cancelar</a>
  </form>
</body>
</html>
