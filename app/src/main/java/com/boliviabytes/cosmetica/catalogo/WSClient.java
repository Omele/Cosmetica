package com.boliviabytes.cosmetica.catalogo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.boliviabytes.cosmetica.model.Categoria;
import com.boliviabytes.cosmetica.model.Pedido;
import com.boliviabytes.cosmetica.model.Producto;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;
/**
 * Created by HPIPP on 11/23/2015.
 */
public class WSClient {
    private static WSClient ourInstance = new WSClient();
	public final static String BASE_URL = "http://192.168.1.101:8080";
	public static WSClient getInstance() {
		return ourInstance;
	}
	private Retrofit retrofit;
    private WSClient() {
		retrofit= new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
    }
    public TaskRunner Hello(Handler handler){
		APICosmetica stackOverflowAPI = retrofit.create(APICosmetica.class);
		Call<List<Categoria>> call = stackOverflowAPI.getCategoria();
		TaskRunner  taskRunner=new TaskRunner(handler,call);
		System.out.println(taskRunner.execute());
		return taskRunner;
	}

	public TaskRunner wsObtenerCategorias(Handler handler){
		APICosmetica stackOverflowAPI = retrofit.create(APICosmetica.class);
		Call<List<Categoria>> call = stackOverflowAPI.getCategoria();
		TaskRunner  taskRunner=new TaskRunner(handler,call);
		System.out.println(taskRunner.execute());
		return taskRunner;
	}



	/**
	 * 
	 * @param login
	 * @param pass
	 */
	public TaskRunner wsLogin(Handler  handler, String login, String pass){
		APICosmetica stackOverflowAPI = retrofit.create(APICosmetica.class);
		Call<Integer> call = stackOverflowAPI.getValidarUsuario(login, pass);
		TaskRunner  taskRunner=new TaskRunner(handler,call);
		System.out.println(taskRunner.execute());
		return taskRunner;
	}
	/**
	 * 
	 * @param categoria
	 */
	public TaskRunner wsConsultarProductos( WSHandler handler,int categoria){
		APICosmetica stackOverflowAPI = retrofit.create(APICosmetica.class);
		Call<List<Producto>> call = stackOverflowAPI.obtenerProductosPorpkCategoriasID(categoria);
		TaskRunner  taskRunner=new TaskRunner(handler,call);
		taskRunner.execute();
		return taskRunner;
	}


	/**
	 * 
	 * @param producto
	 */
	public void wsEnviarPedido(ProductoPedido producto){

	}

	/**
	 * 
	 * @param nombre
	 */
	public void wsBuscarPromotor(String nombre){

	}

	/**
	 * 
	 * @param promotor
	 */
	public void wsConsultarPedido(String promotor){

	}

	/**
	 * 
	 * @param id
	 */
	public void wsEditarPedido(int id){

	}

	/**
	 * 
	 * @param id
	 */
	public void wsEliminarPedido(int id){

	}

	/**
	 * 
	 * @param promotor
	 */
	public TaskRunner wsConsultarPedidos(WSHandler handler,int promotorId){
		APICosmetica stackOverflowAPI = retrofit.create(APICosmetica.class);
		Call<List<Pedido>> call = stackOverflowAPI.obtenerPedidos(promotorId);
		TaskRunner  taskRunner=new TaskRunner(handler,call);
		taskRunner.execute();
		return taskRunner;
	}


}
