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
/**
 * Clase encargada de gestionar la persistencia de los USUARIOS en un archivo JSON.
 * Proporciona métodos para guardar, cargar, agregar, eliminar y obtener todos los USUARIOS.
 * Los productos se almacenan en un archivo JSON ubicado en la ruta especificada por ARCHIVO_USUARIOS.
 */
public class UsuarioPersistencia {
    private static final String ARCHIVO_USUARIOS = "DB/usuarios.json";

  
    private static void guardarUsuarios(List<Usuario> usuarios) {
        File archivo = new File(ARCHIVO_USUARIOS);
        File directorio = archivo.getParentFile(); 

       
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        
        JSONArray arrayUsuarios = new JSONArray();
        for (Usuario u : usuarios) {
            JSONObject obj = new JSONObject();
            obj.put("usuario", u.getUsuario());
            obj.put("contraseña", u.getContraseña());
            arrayUsuarios.add(obj);
        }

       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(arrayUsuarios.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    private static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO_USUARIOS);

        
        if (!archivo.exists()) {
            return usuarios;
        }

       
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

   
    public static boolean registrarUsuario(String usuario, String contraseña) {
        List<Usuario> usuarios = cargarUsuarios();

        
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return false; 
            }
        }

      
        usuarios.add(new Usuario(usuario, contraseña));
        guardarUsuarios(usuarios);
        return true;
    }

    
    public static boolean validarUsuario(String usuario, String contraseña) {
        List<Usuario> usuarios = cargarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                return true; 
            }
        }
        return false; 
    }
}