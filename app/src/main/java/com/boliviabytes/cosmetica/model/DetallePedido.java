/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boliviabytes.cosmetica.model;

import java.io.Serializable;

/**
 *
 * @author edwin molina cardena
 */
public class DetallePedido implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pkDetallePedidosID;
    private int cantidad;
    private double precio;
    private Producto fkProductosID;
    private Promotor fkPedidosID;

    public DetallePedido() {
    }

    public DetallePedido(Integer pkDetallePedidosID) {
        this.pkDetallePedidosID = pkDetallePedidosID;
    }

    public DetallePedido(Integer pkDetallePedidosID, int cantidad, double precio) {
        this.pkDetallePedidosID = pkDetallePedidosID;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getPkDetallePedidosID() {
        return pkDetallePedidosID;
    }

    public void setPkDetallePedidosID(Integer pkDetallePedidosID) {
        this.pkDetallePedidosID = pkDetallePedidosID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getFkProductosID() {
        return fkProductosID;
    }

    public void setFkProductosID(Producto fkProductosID) {
        this.fkProductosID = fkProductosID;
    }

    public Promotor getFkPedidosID() {
        return fkPedidosID;
    }

    public void setFkPedidosID(Promotor fkPedidosID) {
        this.fkPedidosID = fkPedidosID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDetallePedidosID != null ? pkDetallePedidosID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetallePedido)) {
            return false;
        }
        DetallePedido other = (DetallePedido) object;
        if ((this.pkDetallePedidosID == null && other.pkDetallePedidosID != null) || (this.pkDetallePedidosID != null && !this.pkDetallePedidosID.equals(other.pkDetallePedidosID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fkProductosID.getNombre();
    }

}
