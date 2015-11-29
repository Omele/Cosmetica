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
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pkCategoriasID;
    private String nombre;
    private List<Producto> productoList;

    public Categoria() {
    }

    public Categoria(Integer pkCategoriasID) {
        this.pkCategoriasID = pkCategoriasID;
    }

    public Categoria(Integer pkCategoriasID, String nombre) {
        this.pkCategoriasID = pkCategoriasID;
        this.nombre = nombre;
    }

    public Integer getPkCategoriasID() {
        return pkCategoriasID;
    }

    public void setPkCategoriasID(Integer pkCategoriasID) {
        this.pkCategoriasID = pkCategoriasID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCategoriasID != null ? pkCategoriasID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.pkCategoriasID == null && other.pkCategoriasID != null) || (this.pkCategoriasID != null && !this.pkCategoriasID.equals(other.pkCategoriasID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
