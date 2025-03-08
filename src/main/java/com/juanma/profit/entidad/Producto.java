/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.entidad;

import java.util.Date;

/**
 *
 * @author juanm
 */
public class Producto {
    
    private int id;
    private String nombre;
    private String codigo;
    private String proveedor;
    private Date fechaAgregado;
    private Date fechaCaducidad;

    public Producto(String nombre, String codigo, String proveedor, Date fechaAgregado, Date fechaCaducidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.proveedor = proveedor;
        this.fechaAgregado = fechaAgregado;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    
    
}
