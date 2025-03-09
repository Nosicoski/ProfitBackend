/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Producto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.juanma.profit.entidad.Producto;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author juanm
 */
public class ProductoPersistencia {
    private static final String ARCHIVO_PRODUCTOS = "DB/productos.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    
    private static void guardarProductos(List<Producto> productos) {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        File directorio = archivo.getParentFile();

        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        JSONArray arrayProductos = new JSONArray();
        for (Producto p : productos) {
            JSONObject obj = new JSONObject();
            obj.put("nombre", p.getNombre());
            obj.put("codigo", p.getCodigo());
            obj.put("proveedor", p.getProveedor());
            obj.put("fechaAgregado", DATE_FORMAT.format(p.getFechaAgregado()));
            obj.put("fechaCaducidad", DATE_FORMAT.format(p.getFechaCaducidad()));
            arrayProductos.add(obj);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(arrayProductos.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(ARCHIVO_PRODUCTOS);

        if (!archivo.exists()) {
            return productos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            JSONParser parser = new JSONParser();
            JSONArray arrayProductos = (JSONArray) parser.parse(reader);

            for (Object o : arrayProductos) {
                JSONObject obj = (JSONObject) o;
                String nombre = (String) obj.get("nombre");
                String codigo = (String) obj.get("codigo");
                String proveedor = (String) obj.get("proveedor");
                Date fechaAgregado = DATE_FORMAT.parse((String) obj.get("fechaAgregado"));
                Date fechaCaducidad = DATE_FORMAT.parse((String) obj.get("fechaCaducidad"));

                productos.add(new Producto(nombre, codigo, proveedor, fechaAgregado, fechaCaducidad));
            }
        } catch (IOException | ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return productos;
    }


    public static void agregarProducto(Producto producto) {
        List<Producto> productos = cargarProductos();
        productos.add(producto);
        guardarProductos(productos);
    }

 
    public static List<Producto> obtenerTodos() {
        return cargarProductos();
    }

    public static void eliminarProducto(String codigo) {
        List<Producto> productos = cargarProductos();
        productos.removeIf(p -> p.getCodigo().equals(codigo));
        guardarProductos(productos);
    }
}
