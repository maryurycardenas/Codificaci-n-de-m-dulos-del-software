package com.sena.modulo.dao;

import com.sena.modulo.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Capa de acceso a datos en memoria (para que el módulo corra sin base de datos).
 * Para la siguiente evidencia se puede reemplazar por JDBC/MySQL conservando los mismos métodos.
 */
public class ClienteDAO {
    private static final List<Cliente> DATOS = new CopyOnWriteArrayList<>();
    private static final AtomicInteger SECUENCIA = new AtomicInteger(0);

    static {
        DATOS.add(new Cliente(SECUENCIA.incrementAndGet(), "Ana Gómez", "ana@correo.com", "3001234567"));
        DATOS.add(new Cliente(SECUENCIA.incrementAndGet(), "Luis Pérez", "luis@correo.com", "3109876543"));
    }

    public List<Cliente> listar(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return new ArrayList<>(DATOS);
        }
        String f = filtro.trim().toLowerCase();
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : DATOS) {
            if (c.getNombre().toLowerCase().contains(f) || c.getCorreo().toLowerCase().contains(f)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : DATOS) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void crear(Cliente c) {
        c.setId(SECUENCIA.incrementAndGet());
        DATOS.add(c);
    }

    public boolean actualizar(Cliente nuevo) {
        Cliente actual = buscarPorId(nuevo.getId());
        if (actual == null) return false;
        actual.setNombre(nuevo.getNombre());
        actual.setCorreo(nuevo.getCorreo());
        actual.setTelefono(nuevo.getTelefono());
        return true;
    }

    public boolean eliminar(int id) {
        Cliente c = buscarPorId(id);
        return c != null && DATOS.remove(c);
    }
}
