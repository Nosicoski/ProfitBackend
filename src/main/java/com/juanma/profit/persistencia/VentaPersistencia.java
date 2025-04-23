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

  public static void agregarVenta(Venta venta, Map<String, Integer> cantidades) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
        conn.setAutoCommit(false);

        String sqlVenta = "INSERT INTO ventas (codigo, nombre_producto, cantidad_vendida, importe, fecha) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlVenta)) {
            for (Producto producto : venta.getProductos()) {
                String codigo = producto.getCodigo();
                String nombreProducto = producto.getNombre();
                int cantidadVendida = cantidades.get(codigo); // Obtenemos la cantidad real
                double importeTotal = producto.getPrecioVenta() * cantidadVendida;

                pstmt.setString(1, codigo);
                pstmt.setString(2, nombreProducto);
                pstmt.setInt(3, cantidadVendida);
                pstmt.setDouble(4, importeTotal);
                pstmt.setTimestamp(5, new Timestamp(venta.getFecha().getTime()));
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

    String sql = "SELECT * FROM ventas";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Venta venta = new Venta();
            venta.setId(rs.getInt("id"));
            venta.setImporte("$ " + String.format("%.2f", rs.getDouble("importe")));
            venta.setFecha(rs.getTimestamp("fecha"));

            Producto producto = new Producto();
            producto.setCodigo(rs.getString("codigo"));
            producto.setNombre(rs.getString("nombre_producto"));
            producto.setCantidad(rs.getInt("cantidad_vendida"));
            producto.setPrecioVenta(rs.getDouble("importe") / producto.getCantidad()); // cálculo estimado

            List<Producto> productos = new ArrayList<>();
            productos.add(producto);
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
   
}
