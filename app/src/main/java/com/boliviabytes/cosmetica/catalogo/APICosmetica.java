package com.boliviabytes.cosmetica.catalogo;

import com.boliviabytes.cosmetica.model.Categoria;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by HPIPP on 11/28/2015.
 */
public interface APICosmetica {
    @GET("/CosmeticaServicio/servicio/categoria/")
    Call<List<Categoria>> getCategoria();
}
