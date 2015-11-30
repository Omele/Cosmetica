package com.boliviabytes.cosmetica.promotor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.DetallePedido;

import java.util.ArrayList;


public class VistaPedido extends AppCompatActivity {
    public  ArrayList<DetallePedido> lDetallePedido=new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvDetallePedido= (ListView) findViewById(R.id.lvPedidosCliente);
            if(lDetallePedido!=null){
                ArrayAdapter<DetallePedido> arPedidos=new ArrayAdapter<DetallePedido>(getBaseContext(),android.R.layout.simple_list_item_1,lDetallePedido);
                lvDetallePedido.setAdapter(arPedidos);
            }else{
                ArrayAdapter<DetallePedido> arPedidos=new ArrayAdapter<DetallePedido>(getBaseContext(),android.R.layout.simple_list_item_1,new ArrayList<DetallePedido>());
                lvDetallePedido.setAdapter(arPedidos);
            }
    }

}
