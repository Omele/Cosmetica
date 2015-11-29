/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boliviabytes.cosmetica.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author edwin molina cardena
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pkProductosID;
    private String nombre;
    private String descripcion;
    private double costo;
    private double precio;
    private String foto;
    private Categoria fkCategoriasID;
    private List<DetallePedido> detallePedidoList;

    public Producto() {
    }

    public Producto(Integer pkProductosID) {
        this.pkProductosID = pkProductosID;
    }

    public Producto(Integer pkProductosID, String nombre, String descripcion, double costo, double precio, String foto) {
        this.pkProductosID = pkProductosID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precio = precio;
        this.foto = foto;
    }

    public Integer getPkProductosID() {
        return pkProductosID;
    }

    public void setPkProductosID(Integer pkProductosID) {
        this.pkProductosID = pkProductosID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Categoria getFkCategoriasID() {
        return fkCategoriasID;
    }

    public void setFkCategoriasID(Categoria fkCategoriasID) {
        this.fkCategoriasID = fkCategoriasID;
    }

    public List<DetallePedido> getDetallePedidoList() {
        return detallePedidoList;
    }

    public void setDetallePedidoList(List<DetallePedido> detallePedidoList) {
        this.detallePedidoList = detallePedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProductosID != null ? pkProductosID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.pkProductosID == null && other.pkProductosID != null) || (this.pkProductosID != null && !this.pkProductosID.equals(other.pkProductosID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
