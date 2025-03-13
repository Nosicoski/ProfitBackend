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
public class Venta {
    private int id;
    List<Producto> productos;
    private String importe;
    private double codigoProducto;
    private String categoria;

    public Venta(int id, List<Producto> productos, String importe, String categoria) {
        this.id = id;
        this.productos = productos;
        this.importe = importe;
        this.codigoProducto = codigoProducto;
        this.categoria = categoria;
    }

    public Venta() {
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public double getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(double codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

