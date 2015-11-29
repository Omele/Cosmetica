package com.boliviabytes.cosmetica.catalogo;

import com.boliviabytes.cosmetica.model.Categoria;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by HPIPP on 11/28/2015.
 */
public interface APICosmetica {
    @GET("/CosmeticaServicio/servicio/categoria/0/2")
    Call<List<Categoria>> getCategoria();

    @GET("/CosmeticaServicio/servicio/promotor/validarLogin/{login}/{password}")
    Call<Integer> getValidarUsuario(@Path("login") String login, @Path("password") String password);
}
