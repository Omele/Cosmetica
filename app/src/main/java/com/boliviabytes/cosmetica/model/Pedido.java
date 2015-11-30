/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boliviabytes.cosmetica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edwin molina cardena
 */
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pkPedidosID;
    private String cliente;
    private String telefono;
    private double total;
    private Date fecha;
    private Promotor fkPromotoresID;
    private List<DetallePedido> detallePedidoList;

    public Pedido() {
    }

    public Pedido(Integer pkPedidosID) {
        this.pkPedidosID = pkPedidosID;
    }

    public Pedido(Integer pkPedidosID, String cliente, String telefono, double total, Date fecha) {
        this.pkPedidosID = pkPedidosID;
        this.cliente = cliente;
        this.telefono = telefono;
        this.total = total;
        this.fecha = fecha;
    }

    public Integer getPkPedidosID() {
        return pkPedidosID;
    }

    public void setPkPedidosID(Integer pkPedidosID) {
        this.pkPedidosID = pkPedidosID;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Promotor getFkPromotoresID() {
        return fkPromotoresID;
    }

    public void setFkPromotoresID(Promotor fkPromotoresID) {
        this.fkPromotoresID = fkPromotoresID;
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
        hash += (pkPedidosID != null ? pkPedidosID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.pkPedidosID == null && other.pkPedidosID != null) || (this.pkPedidosID != null && !this.pkPedidosID.equals(other.pkPedidosID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cliente;
    }

}
