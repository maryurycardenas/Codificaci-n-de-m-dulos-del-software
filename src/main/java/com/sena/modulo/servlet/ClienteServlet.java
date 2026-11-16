package com.sena.modulo.servlet;

import com.sena.modulo.dao.ClienteDAO;
import com.sena.modulo.model.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controlador del módulo Clientes.
 * GET  -> consultas (listar, buscar, mostrar formulario nuevo/editar).
 * POST -> operaciones que modifican datos (guardar, eliminar).
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/clientes"})
public class ClienteServlet extends HttpServlet {

    private final ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                request.setAttribute("cliente", new Cliente());
                request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                break;
            case "editar":
                Integer id = parsearId(request.getParameter("id"));
                Cliente c = (id == null) ? null : dao.buscarPorId(id);
                if (c == null) {
                    response.sendRedirect(request.getContextPath() + "/clientes?msg=noencontrado");
                    return;
                }
                request.setAttribute("cliente", c);
                request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                break;
            default: // listar / buscar
                String q = request.getParameter("q");
                request.setAttribute("clientes", dao.listar(q));
                request.setAttribute("q", q == null ? "" : q);
                request.getRequestDispatcher("/listar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        String base = request.getContextPath() + "/clientes";

        if ("eliminar".equals(accion)) {
            Integer id = parsearId(request.getParameter("id"));
            boolean ok = id != null && dao.eliminar(id);
            response.sendRedirect(base + "?msg=" + (ok ? "eliminado" : "noencontrado"));
            return;
        }

        if ("guardar".equals(accion)) {
            Cliente c = new Cliente();
            Integer id = parsearId(request.getParameter("id"));
            c.setId(id == null ? 0 : id);
            c.setNombre(valor(request, "nombre"));
            c.setCorreo(valor(request, "correo"));
            c.setTelefono(valor(request, "telefono"));

            String error = validar(c);
            if (error != null) { // vuelve al formulario conservando lo digitado
                request.setAttribute("error", error);
                request.setAttribute("cliente", c);
                request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                return;
            }

            if (c.getId() == 0) {
                dao.crear(c);
                response.sendRedirect(base + "?msg=creado");
            } else {
                boolean ok = dao.actualizar(c);
                response.sendRedirect(base + "?msg=" + (ok ? "actualizado" : "noencontrado"));
            }
            return;
        }

        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
    }

    private String validar(Cliente c) {
        if (c.getNombre().isEmpty()) return "El nombre es obligatorio.";
        if (!c.getCorreo().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) return "El correo no tiene un formato válido.";
        if (!c.getTelefono().matches("^\\d{7,10}$")) return "El teléfono debe tener entre 7 y 10 dígitos.";
        return null;
    }

    private String valor(HttpServletRequest r, String nombre) {
        String v = r.getParameter(nombre);
        return v == null ? "" : v.trim();
    }

    private Integer parsearId(String s) {
        try { return Integer.valueOf(s); } catch (NumberFormatException e) { return null; }
    }
}
