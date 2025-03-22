/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.entidad;

import java.util.Date;
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
    private Date fecha;

    public Venta(int id, List<Producto> productos, String importe, double codigoProducto, Date fecha) {
        this.id = id;
        this.productos = productos;
        this.importe = importe;
        this.codigoProducto = codigoProducto;
        this.fecha = fecha;
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

    
 public void setCodigoProducto(Double codigoProducto) { // Acepta un String
        this.codigoProducto = codigoProducto;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

