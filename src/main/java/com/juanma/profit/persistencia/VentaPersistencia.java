package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Producto;
import com.juanma.profit.entidad.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VentaPersistencia {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/Profit_DB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "lolateamo123";

    public static void agregarVenta(Venta venta) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // Insertar venta principal
            String sqlVenta = "INSERT INTO ventas (importe, fecha) VALUES (?, ?) RETURNING id";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setDouble(1, Double.parseDouble(venta.getImporte().replace("$ ", "")));
                pstmt.setTimestamp(2, new Timestamp(venta.getFecha().getTime()));
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    venta.setId(rs.getInt(1));
                }
            }

            // Insertar productos relacionados
            String sqlProductos = "INSERT INTO venta_productos (venta_id, producto_codigo, cantidad) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProductos)) {
                for (Producto producto : venta.getProductos()) {
                    pstmt.setInt(1, venta.getId());
                    pstmt.setString(2, producto.getCodigo());
                    pstmt.setInt(3, producto.getCantidad());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Venta> obtenerTodas() {
        List<Venta> ventas = new ArrayList<>();
        
        String sql = """
            SELECT v.id, v.importe, v.fecha, 
                   jsonb_agg(jsonb_build_object(
                       'codigo', vp.producto_codigo,
                       'cantidad', vp.cantidad
                   )) AS productos
            FROM ventas v
            LEFT JOIN venta_productos vp ON v.id = vp.venta_id
            GROUP BY v.id""";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setImporte("$ " + rs.getDouble("importe"));
                venta.setFecha(rs.getTimestamp("fecha"));
                
                // Obtener productos
                String productosJson = rs.getString("productos");
                List<Producto> productos = parseProductosFromJson(productosJson);
                venta.setProductos(productos);
                
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    private static List<Producto> parseProductosFromJson(String json) {
        List<Producto> productos = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) new JSONParser().parse(json);
            for (Object obj : array) {
                JSONObject productoJson = (JSONObject) obj;
                String codigo = (String) productoJson.get("codigo");
                int cantidad = ((Long) productoJson.get("cantidad")).intValue();
                
                Producto producto = ProductoPersistencia.obtenerPorCodigo(codigo);
                if (producto != null) {
                    producto.setCantidad(cantidad);
                    productos.add(producto);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static void eliminarVenta(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // Eliminar relaciones primero
            try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM venta_productos WHERE venta_id = ?")) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            // Eliminar venta
            try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM ventas WHERE id = ?")) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void agregarVenta(Venta venta, Map<String, Integer> cantidades) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
        conn.setAutoCommit(false);
    String importeLimpio = venta.getImporte()
                .replace("$ ", "")  
                .replace(",", "."); 

        // Insertar venta principal
       String sqlVenta = "INSERT INTO ventas (importe, fecha) VALUES (?, ?) RETURNING id";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, Double.parseDouble(importeLimpio)); // Usar el valor convertido
            pstmt.setTimestamp(2, new Timestamp(venta.getFecha().getTime()));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                venta.setId(rs.getInt(1));
            }
        }

        // Insertar productos con cantidades
     String sqlProductos = "INSERT INTO venta_productos (venta_id, producto_codigo, cantidad) VALUES (?, ?, ?)";
try (PreparedStatement pstmt = conn.prepareStatement(sqlProductos)) {
    for (Producto producto : venta.getProductos()) {
        pstmt.setInt(1, venta.getId());
        pstmt.setString(2, producto.getCodigo());
        pstmt.setInt(3, producto.getCantidad()); // ¡Este valor debe estar definido!
        pstmt.addBatch();
    }
    pstmt.executeBatch();
}

        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}