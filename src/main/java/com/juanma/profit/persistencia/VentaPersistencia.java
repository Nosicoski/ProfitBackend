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
 * Clase encargada de gestionar la persistencia de las ventas. Las ventas se
 * almacenan en un archivo JSON con un formato que incluye la fecha de la venta.
 */
public class VentaPersistencia {

    private static final String ARCHIVO_VENTAS = "DB/ventas.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Guarda una lista de ventas en el archivo JSON. Si el archivo no existe,
     * lo crea.
     *
     * @param ventas Lista de ventas a guardar.
     */
    private static void guardarVentas(List<Venta> ventas) {
        JSONArray arrayVentas = new JSONArray();
        for (Venta venta : ventas) {
            JSONObject obj = new JSONObject();
            obj.put("id", venta.getId());
            obj.put("importe", venta.getImporte());

            // Guardar productos
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
            obj.put("productos", productosArray);

            arrayVentas.add(obj);
        }

        // Guardar en el archivo JSON
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS))) {
            writer.write(arrayVentas.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga las ventas desde el archivo JSON. Si el archivo no existe, retorna
     * una lista vacía.
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
            double codigoProducto = obj.get("codigoProducto") != null
                    ? ((Number) obj.get("codigoProducto")).doubleValue() : 0.0;

           
            Date fecha = null;
            if (obj.get("fecha") != null) {
                try {
                    fecha = DATE_FORMAT.parse((String) obj.get("fecha"));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                    
                    fecha = new Date(); 
                }
            } else {
                fecha = new Date(); 
            }

            // Cargar la lista de productos
            List<Producto> productosVenta = new ArrayList<>();
            JSONArray productosArray = (JSONArray) obj.get("productos");
            if (productosArray != null) {
                for (Object prodObj : productosArray) {
                    JSONObject prodJson = (JSONObject) prodObj;
                    String nombre = (String) prodJson.get("nombre");
                    String codigo = (String) prodJson.get("codigo");
                    String proveedor = (String) prodJson.get("proveedor");
                    double precioCompra = prodJson.get("precioCompra") != null
                            ? ((Number) prodJson.get("precioCompra")).doubleValue() : 0.0;
                    double precioVenta = prodJson.get("precioVenta") != null
                            ? ((Number) prodJson.get("precioVenta")).doubleValue() : 0.0;
                    String categoria = (String) prodJson.get("categoria");

                    productosVenta.add(new Producto(nombre, codigo, proveedor, precioCompra, precioVenta, categoria));
                }
            }

            // Crear la venta con los datos cargados
            ventas.add(new Venta(id, productosVenta, importe, codigoProducto, fecha));
        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }

    return ventas;
}

    public static void agregarVenta(Venta venta) {
        {

            List<Venta> ventas = cargarVentas();

            int nuevoId = 1;
            if (!ventas.isEmpty()) {

                int ultimoId = ventas.stream()
                        .mapToInt(Venta::getId)
                        .max()
                        .orElse(0);
                nuevoId = ultimoId + 1;
            }

            venta.setId(nuevoId);

            ventas.add(venta);

            guardarVentas(ventas);
        }
    }

    public static List<Venta> obtenerTodas() {
        return cargarVentas();
    }

    public static void eliminarVenta(int id) {
        List<Venta> ventas = cargarVentas();
        boolean ventaEliminada = ventas.removeIf(venta -> venta.getId() == id);
        guardarVentas(ventas);

        if (ventaEliminada) {
            System.out.println("Venta con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna venta con el ID: " + id);
        }
    }
}
