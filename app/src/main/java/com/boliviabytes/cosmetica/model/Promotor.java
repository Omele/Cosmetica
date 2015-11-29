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
public class Promotor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pkPromotoresID;
    private String nombre;
    private String login;
    private String password;
    private List<Pedido> pedidoList;
    private List<DetallePedido> detallePedidoList;

    public Promotor() {
    }

    public Promotor(Integer pkPromotoresID) {
        this.pkPromotoresID = pkPromotoresID;
    }

    public Promotor(Integer pkPromotoresID, String nombre, String login, String password) {
        this.pkPromotoresID = pkPromotoresID;
        this.nombre = nombre;
        this.login = login;
        this.password = password;
    }

    public Integer getPkPromotoresID() {
        return pkPromotoresID;
    }

    public void setPkPromotoresID(Integer pkPromotoresID) {
        this.pkPromotoresID = pkPromotoresID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
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
        hash += (pkPromotoresID != null ? pkPromotoresID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Promotor)) {
            return false;
        }
        Promotor other = (Promotor) object;
        if ((this.pkPromotoresID == null && other.pkPromotoresID != null) || (this.pkPromotoresID != null && !this.pkPromotoresID.equals(other.pkPromotoresID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
