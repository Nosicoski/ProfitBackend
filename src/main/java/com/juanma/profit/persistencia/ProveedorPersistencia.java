package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Proveedor;
import com.juanma.profit.entidad.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorPersistencia {

    private static final String URL = "jdbc:postgresql://localhost:5432/Profit_DB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "lolateamo123";

    public static void agregarProveedor(Proveedor proveedor) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // Insertar proveedor (sin RETURNING, usamos getGeneratedKeys)
            String sqlProveedor = "INSERT INTO proveedores (nombre, apellido, email) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProveedor, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, proveedor.getNombre());
                pstmt.setString(2, proveedor.getApellido());
                pstmt.setString(3, proveedor.getEmail());

                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas == 0) {
                    throw new SQLException("Error al insertar proveedor, no se creó ninguna fila.");
                }

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        proveedor.setId(rs.getInt(1));
                    } else {
                        throw new SQLException("Error al obtener el ID generado del proveedor.");
                    }
                }
            }

            // Insertar productos asociados
            for (Producto producto : proveedor.getProductos()) {
                producto.setProveedor(proveedor.getNombre());
                ProductoPersistencia.agregarProducto(producto);
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();

        String sql = "SELECT * FROM proveedores";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setEmail(rs.getString("email"));

                // Obtener productos del proveedor
                List<Producto> productos = ProductoPersistencia.obtenerPorProveedor(p.getNombre());
                p.setProductos(productos);

                proveedores.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    public static void eliminarProveedor(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM proveedores WHERE id = ?")) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
