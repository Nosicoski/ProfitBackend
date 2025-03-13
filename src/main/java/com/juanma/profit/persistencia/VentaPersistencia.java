package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Producto;
import com.juanma.profit.entidad.Venta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de gestionar la persistencia de las ventas.
 * Las ventas se almacenan en un archivo JSON con un formato que incluye la fecha de la venta.
 */
public class VentaPersistencia {
    private static final String ARCHIVO_VENTAS = "DB/ventas.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Guarda una lista de ventas en el archivo JSON.
     * Si el archivo no existe, lo crea.
     *
     * @param ventas Lista de ventas a guardar.
     */
    private static void guardarVentas(List<Venta> ventas) {
    File archivo = new File(ARCHIVO_VENTAS);
    File directorio = archivo.getParentFile();

    if (directorio != null && !directorio.exists()) {
        directorio.mkdirs();
    }

    JSONArray arrayVentas = new JSONArray();
    for (Venta venta : ventas) {
        JSONObject obj = new JSONObject();
        obj.put("id", venta.getId());
        obj.put("importe", venta.getImporte());
        obj.put("codigoProducto", venta.getCodigoProducto());
        obj.put("categoria", venta.getCategoria());
        obj.put("fecha", DATE_FORMAT.format(new Date())); // Guarda la fecha actual

        // 🔹 Guardar la lista de productos dentro de la venta
        JSONArray productosArray = new JSONArray();
        for (Producto p : venta.getProductos()) {
            JSONObject prodObj = new JSONObject();
            prodObj.put("nombre", p.getNombre());
            prodObj.put("codigo", p.getCodigo());
            prodObj.put("proveedor", p.getProveedor());
            prodObj.put("precioCompra", p.getPrecioCompra());
            prodObj.put("precioVenta", p.getPrecioVenta());
            prodObj.put("categoria", p.getCategoria());
            productosArray.add(prodObj);
        }
        obj.put("productos", productosArray); // Agregar productos al JSON

        arrayVentas.add(obj);
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
        writer.write(arrayVentas.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /**
     * Carga las ventas desde el archivo JSON.
     * Si el archivo no existe, retorna una lista vacía.
     *
     * @return Lista de ventas cargadas desde el archivo JSON.
     */
   private static List<Venta> cargarVentas() {
    List<Venta> ventas = new ArrayList<>();
    File archivo = new File(ARCHIVO_VENTAS);

    if (!archivo.exists()) {
        return ventas;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        JSONParser parser = new JSONParser();
        JSONArray arrayVentas = (JSONArray) parser.parse(reader);

        for (Object o : arrayVentas) {
            JSONObject obj = (JSONObject) o;
            int id = ((Long) obj.get("id")).intValue();
            String importe = (String) obj.get("importe");
            double codigoProducto = (double) obj.get("codigoProducto");
            String categoria = (String) obj.get("categoria");

            //  Cargar productos de la venta
            List<Producto> productosVenta = new ArrayList<>();
            JSONArray productosArray = (JSONArray) obj.get("productos");
            if (productosArray != null) { // Evitar NullPointerException
                for (Object prodObj : productosArray) {
                    JSONObject prodJson = (JSONObject) prodObj;
                    String nombre = (String) prodJson.get("nombre");
                    String codigo = (String) prodJson.get("codigo");
                    String proveedor = (String) prodJson.get("proveedor");
                    double precioCompra = ((Number) prodJson.get("precioCompra")).doubleValue();
                    double precioVenta = ((Number) prodJson.get("precioVenta")).doubleValue();
                    String categoriaProd = (String) prodJson.get("categoria");

                    productosVenta.add(new Producto(nombre, codigo, proveedor, precioCompra, precioVenta, categoriaProd));
                }
            }

            // 🔹 Ahora creamos la venta con los productos cargados
            ventas.add(new Venta(id, productosVenta, importe, codigoProducto, categoria));
        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }

    return ventas;
}

    /**
     * Agrega una nueva venta a la lista y guarda los cambios en el archivo JSON.
     *
     * @param venta Venta a agregar.
     */
    public static void agregarVenta(Venta venta) {
        List<Venta> ventas = cargarVentas();
        ventas.add(venta);
        guardarVentas(ventas);
    }

    /**
     * Obtiene todas las ventas almacenadas en el archivo JSON.
     *
     * @return Lista de todas las ventas.
     */
    public static List<Venta> obtenerTodas() {
        return cargarVentas();
    }
}