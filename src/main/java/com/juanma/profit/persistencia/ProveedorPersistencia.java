package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Proveedor;
import com.juanma.profit.entidad.Producto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar la persistencia de los PROVEEDORES en un archivo JSON.
 * Proporciona métodos para guardar, cargar, agregar, eliminar y obtener todos los PROVEEDORES.
 * Los proveedores se almacenan en un archivo JSON ubicado en la ruta especificada por ARCHIVO_PROVEEDORES.
 */
public class ProveedorPersistencia {

    private static final String ARCHIVO_PROVEEDORES = "DB/proveedores.json";

    /**
     * Guarda la lista de proveedores en un archivo JSON.
     * Si el directorio no existe, lo crea antes de guardar los datos.
     *
     * @param proveedores Lista de proveedores a guardar.
     */
    private static void guardarProveedores(List<Proveedor> proveedores) {
        File archivo = new File(ARCHIVO_PROVEEDORES);
        File directorio = archivo.getParentFile();

        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        JSONArray arrayProveedores = new JSONArray();
        for (Proveedor p : proveedores) {
            JSONObject obj = new JSONObject();
            obj.put("id", p.getId());
            obj.put("nombre", p.getNombre());
            obj.put("apellido", p.getApellido());
            obj.put("email", p.getEmail());

            // Guardar la lista de productos del proveedor
            JSONArray arrayProductos = new JSONArray();
            for (Producto producto : p.getProductos()) {
                JSONObject productoObj = new JSONObject();
                productoObj.put("nombre", producto.getNombre());
                productoObj.put("codigo", producto.getCodigo());
                productoObj.put("precioCompra", producto.getPrecioCompra());
                productoObj.put("precioVenta", producto.getPrecioVenta());
                productoObj.put("categoria", producto.getCategoria());
                arrayProductos.add(productoObj);
            }
            obj.put("productos", arrayProductos);

            arrayProveedores.add(obj);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(arrayProveedores.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de proveedores desde un archivo JSON.
     * Si el archivo no existe, retorna una lista vacía.
     *
     * @return Lista de proveedores cargados desde el archivo JSON.
     */
    private static List<Proveedor> cargarProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        File archivo = new File(ARCHIVO_PROVEEDORES);

        if (!archivo.exists()) {
            return proveedores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            JSONParser parser = new JSONParser();
            JSONArray arrayProveedores = (JSONArray) parser.parse(reader);

            for (Object o : arrayProveedores) {
                JSONObject obj = (JSONObject) o;
                int id = ((Long) obj.get("id")).intValue();
                String nombre = (String) obj.get("nombre");
                String apellido = (String) obj.get("apellido");
                String email = (String) obj.get("email");

                // Cargar la lista de productos del proveedor
                JSONArray arrayProductos = (JSONArray) obj.get("productos");
                List<Producto> productos = new ArrayList<>();
                for (Object productoObj : arrayProductos) {
                    JSONObject productoJson = (JSONObject) productoObj;
                    String nombreProducto = (String) productoJson.get("nombre");
                    String codigo = (String) productoJson.get("codigo");
                    double precioCompra = ((Number) productoJson.get("precioCompra")).doubleValue();
                    double precioVenta = ((Number) productoJson.get("precioVenta")).doubleValue();
                    String categoria = (String) productoJson.get("categoria");

                    productos.add(new Producto(nombreProducto, codigo, nombre, precioCompra, precioVenta, categoria));
                }

                proveedores.add(new Proveedor(apellido, email, nombre, productos, id));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    /**
     * Agrega un nuevo proveedor a la lista y guarda los cambios en el archivo JSON.
     *
     * @param proveedor Proveedor a agregar.
     */
    public static void agregarProveedor(Proveedor proveedor) {
        List<Proveedor> proveedores = cargarProveedores();
        proveedores.add(proveedor);
        guardarProveedores(proveedores);
    }

    /**
     * Obtiene todos los proveedores almacenados en el archivo JSON.
     *
     * @return Lista de todos los proveedores.
     */
    public static List<Proveedor> obtenerTodos() {
        return cargarProveedores();
    }

    /**
     * Elimina un proveedor de la lista basado en su ID y guarda los cambios en el archivo JSON.
     *
     * @param id ID del proveedor a eliminar.
     */
    public static void eliminarProveedor(int id) {
        List<Proveedor> proveedores = cargarProveedores();
        proveedores.removeIf(p -> p.getId() == id);
        guardarProveedores(proveedores);
    }
}