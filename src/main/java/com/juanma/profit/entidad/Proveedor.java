/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.entidad;

import java.util.List;

/**
 *
 * @author juanm
 */
public class Proveedor {
    private String apellido;
    private String email;
    private String nombre;
    private List <Producto> productos ;
    
    
    private int id;

    public Proveedor() {
    }

    public Proveedor(String apellido, String email, String nombre, List<Producto> productos, int id) {
        this.apellido = apellido;
        this.email = email;
        this.nombre = nombre;
        this.productos = productos;
        
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

  

   

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
