package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class ProductoPersistencia {
  
    private static final String URL = "jdbc:postgresql://localhost:5432/Profit_DB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "lolateamo123";
    
   
 private static final String CREATE_TABLE = """
    CREATE TABLE IF NOT EXISTS productos (
        codigo VARCHAR(50) PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        proveedor VARCHAR(100),
        precio_compra DECIMAL(10, 2) NOT NULL,
        precio_venta DECIMAL(10, 2) NOT NULL,
        categoria VARCHAR(50) NOT NULL,
        cantidad INT NOT NULL  
    )""";
    
    private static final String INSERT_PRODUCTO = """
    INSERT INTO productos (nombre, codigo, proveedor, precio_compra, precio_venta, categoria, cantidad)
    VALUES (?, ?, ?, ?, ?, ?, ?)"""; 
    
    private static final String SELECT_ALL = "SELECT * FROM productos";
    private static final String DELETE_PRODUCTO = "DELETE FROM productos WHERE codigo = ?";
    
    static {
        inicializarBaseDatos();
    }
    
    private static void inicializarBaseDatos() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void agregarProducto(Producto producto) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_PRODUCTO)) {
            
            establecerParametrosProducto(pstmt, producto);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }
    
    public static List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            
            while (rs.next()) {
                productos.add(mapearResultSetAProducto(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        
        return productos;
    }
    
    public static void eliminarProducto(String codigo) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCTO)) {
            
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }
    
    
    private static void establecerParametrosProducto(PreparedStatement pstmt, Producto producto) 
        throws SQLException {
        pstmt.setString(1, producto.getNombre());
        pstmt.setString(2, producto.getCodigo());
        pstmt.setString(3, producto.getProveedor());
        pstmt.setDouble(4, producto.getPrecioCompra());
        pstmt.setDouble(5, producto.getPrecioVenta());
        pstmt.setString(6, producto.getCategoria());
         pstmt.setInt(7, producto.getCantidad());
    }
    
    private static Producto mapearResultSetAProducto(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getString("nombre"),
            rs.getString("codigo"),
            rs.getString("proveedor"),
            rs.getDouble("precio_compra"),
            rs.getDouble("precio_venta"),
            rs.getString("categoria"),
            rs.getInt("Cantidad")
        );
    }
    
    private static void manejarExcepcionSQL(SQLException e) {
        System.err.println("Error de base de datos:");
        System.err.println("Mensaje: " + e.getMessage());
        System.err.println("Código error: " + e.getErrorCode());
        System.err.println("Estado SQL: " + e.getSQLState());
    }
    
   
    public static void migrarDatosDesdeJSON() {
        List<Producto> productos = cargarProductosDesdeJSON();
        
        if (productos.isEmpty()) return;
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_PRODUCTO)) {
            
            conn.setAutoCommit(false);
            
            for (Producto producto : productos) {
                establecerParametrosProducto(pstmt, producto);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            conn.commit();
            
            System.out.println("Migración completada: " + productos.size() + " productos migrados");
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }
    
    private static List<Producto> cargarProductosDesdeJSON() {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File("DB/productos.json");
        
        if (!archivo.exists()) return productos;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            JSONParser parser = new JSONParser();
            JSONArray arrayProductos = (JSONArray) parser.parse(reader);
            
            for (Object obj : arrayProductos) {
                JSONObject jsonProducto = (JSONObject) obj;
                Producto producto = new Producto(
                    (String) jsonProducto.get("nombre"),
                    (String) jsonProducto.get("codigo"),
                    (String) jsonProducto.get("proveedor"),
                    ((Number) jsonProducto.get("precioCompra")).doubleValue(),
                    ((Number) jsonProducto.get("precioVenta")).doubleValue(),
                    (String) jsonProducto.get("categoria"),
                    (int)    jsonProducto.get("Cantidad")
                );
                productos.add(producto);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error cargando datos desde JSON:");
            e.printStackTrace();
        }
        
        return productos;
    }
    public static Producto obtenerPorCodigo(String codigo) {
    String sql = "SELECT * FROM productos WHERE codigo = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new Producto(
                rs.getString("nombre"),
                rs.getString("codigo"),
                rs.getString("proveedor"),
                rs.getDouble("precio_compra"),
                rs.getDouble("precio_venta"),
                rs.getString("categoria"),
                rs.getInt("Cantidad")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public static List<Producto> obtenerPorProveedor(String nombreProveedor) {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM productos WHERE proveedor = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, nombreProveedor);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            productos.add(mapearResultSetAProducto(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}
}