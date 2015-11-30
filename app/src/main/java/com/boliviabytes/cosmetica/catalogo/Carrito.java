package com.boliviabytes.cosmetica.catalogo;

import com.boliviabytes.cosmetica.model.DetallePedido;
import com.boliviabytes.cosmetica.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HPIPP
 * @version 1.0
 * @created 28-Nov-2015 8:53:25 AM
 */
public class Carrito{
	private static Carrito ourInstance = new Carrito();
	private List<DetallePedido> detallePedidos;
	public static Carrito getInstance() {
		return ourInstance;
	}
	public Carrito(){
		detallePedidos=new ArrayList<>();
	}

	public List<DetallePedido> getDetallePedidos() {
		return detallePedidos;
	}

	public void setDetallePedidos(List<DetallePedido> detallePedidos) {
		this.detallePedidos = detallePedidos;
	}

	public void finalize() throws Throwable {

	}

	public void anhadirProducto(Producto producto,int cantidad){
		int indice=detallePedidos.indexOf(producto);
		if (indice!=-1){
			DetallePedido detallePedido=new DetallePedido();
			detallePedido.setCantidad(cantidad);
			detallePedido.setPrecio(cantidad * producto.getPrecio());
			detallePedido.setFkProductosID(producto);
			detallePedidos.add(detallePedido);
		}else{
			DetallePedido detallePedido=detallePedidos.get(indice);
			detallePedido.setCantidad(detallePedido.getCantidad()+cantidad);
			detallePedido.setPrecio((cantidad * producto.getPrecio())+detallePedido.getPrecio());
		}

	}
	public void limpiarCarrito(){
		detallePedidos.clear();
	}
	public void quitarProducto(){

	}

}