package com.juanma.profit.persistencia;

import com.juanma.profit.entidad.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
// Librerías de JSON Simple
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author juanm
 */
public class UsuarioPersistencia {
    private static final String ARCHIVO_USUARIOS = "DB/usuarios.json";

    // Guardar lista de usuarios en formato JSON
    private static void guardarUsuarios(List<Usuario> usuarios) {
        File archivo = new File(ARCHIVO_USUARIOS);
        File directorio = archivo.getParentFile(); // Obtiene la carpeta "DB"

        // Si la carpeta "DB" no existe, créala
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        // Convertir la lista de usuarios en un JSONArray
        JSONArray arrayUsuarios = new JSONArray();
        for (Usuario u : usuarios) {
            JSONObject obj = new JSONObject();
            obj.put("usuario", u.getUsuario());
            obj.put("contraseña", u.getContraseña());
            arrayUsuarios.add(obj);
        }

        // Escribir el JSONArray en el archivo .json
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(arrayUsuarios.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar lista de usuarios desde el archivo JSON
    private static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO_USUARIOS);

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            return usuarios;
        }

        // Leer el contenido JSON y convertirlo en objetos Usuario
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            JSONParser parser = new JSONParser();
            JSONArray arrayUsuarios = (JSONArray) parser.parse(reader);

            for (Object o : arrayUsuarios) {
                JSONObject obj = (JSONObject) o;
                String user = (String) obj.get("usuario");
                String pass = (String) obj.get("contraseña");

                usuarios.add(new Usuario(user, pass));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para registrar un nuevo usuario
    public static boolean registrarUsuario(String usuario, String contraseña) {
        List<Usuario> usuarios = cargarUsuarios();

        // Verificar si el usuario ya existe
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return false; // Usuario ya existe
            }
        }

        // Agregar nuevo usuario y guardar en el archivo JSON
        usuarios.add(new Usuario(usuario, contraseña));
        guardarUsuarios(usuarios);
        return true;
    }

    // Método para validar usuario en el login
    public static boolean validarUsuario(String usuario, String contraseña) {
        List<Usuario> usuarios = cargarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                return true; // Usuario encontrado y contraseña correcta
            }
        }
        return false; // No se encontró el usuario o contraseña incorrecta
    }
}