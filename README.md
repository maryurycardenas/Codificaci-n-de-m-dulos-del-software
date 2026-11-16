# Módulo de Gestión de Clientes (GA7-220501096-AA2-EV02)

**Aprendiz:** Maryury Cárdenas Ortega  
**Ficha:** 3336248  
**Programa:** Análisis y desarrollo de software (SENA)  
**Correo:** maryurycardenas733@gmail.com  
**Repositorio:** https://github.com/maryurycardenas/Codificaci-n-de-m-dulos-del-software.git

Módulo web con **Servlets + JSP + JSTL** (Java 11, Maven, Tomcat 9).

## Cumplimiento de la lista de chequeo
| Indicador | Dónde se evidencia |
|---|---|
| Formularios HTML con servlets | `formulario.jsp` y `listar.jsp` envían a `ClienteServlet` (`/clientes`) |
| Métodos GET y POST | GET: listar, buscar, abrir formulario nuevo/editar. POST: guardar y eliminar (`doGet` / `doPost`) |
| Elementos de JSP | Directivas `page`, `taglib`, `include` (`encabezado.jspf`), expresión en `index.jsp`, EL y JSTL (`c:forEach`, `c:choose`, `c:if`, `c:out`) |
| Versionamiento | Git (historial de commits y `ENLACE_REPOSITORIO.txt`) |

## Ejecutar
1. `mvn clean package`
2. Copiar `target/modulo-clientes.war` a `TOMCAT_HOME/webapps/` (Tomcat 9), o ejecutarlo desde NetBeans/Eclipse/IntelliJ con Tomcat 9.
3. Abrir `http://localhost:8080/modulo-clientes/`

## Pruebas manuales (agregue sus capturas de pantalla)
| # | Caso | Resultado esperado |
|---|---|---|
| 1 | Abrir `/clientes` | Muestra 2 clientes de ejemplo |
| 2 | Buscar "ana" (GET `?q=ana`) | Solo aparece Ana Gómez |
| 3 | Nuevo cliente válido (POST) | Redirige al listado con mensaje "creado" |
| 4 | Nuevo cliente con correo inválido | Vuelve al formulario con error y conserva los datos |
| 5 | Editar cliente (GET `?accion=editar&id=1`, luego POST) | Datos actualizados |
| 6 | Eliminar cliente (POST) | Desaparece del listado |
| 7 | `?accion=editar&id=999` | Mensaje "El cliente no existe" |

## Flujo Git
```
git remote add origin https://github.com/maryurycardenas/Codificaci-n-de-m-dulos-del-software.git
git push -u origin main
```
