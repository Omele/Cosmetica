package com.boliviabytes.cosmetica.catalogo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Base64;
import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Producto;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.List;

/**
 * Created by HPIPP on 11/29/2015.
 */
public class AdapterCatalogo extends BaseAdapter {
    List<Producto>  productos;
    private static LayoutInflater inflater=null;
    public AdapterCatalogo(Context context,List<Producto> productos) {
        this.productos=productos;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        productos.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        Producto producto=productos.get(position);
        rowView = inflater.inflate(R.layout.lv_item, null);
        holder.lvNombre=(TextView) rowView.findViewById(R.id.tvNombrePro);
        holder.ivFoto=(ImageView) rowView.findViewById(R.id.ivFoto);
        holder.lvDescripcion= (TextView) rowView.findViewById(R.id.tvDescripcionPro);
        holder.lvPrecio= (TextView) rowView.findViewById(R.id.tvPrecio);
        holder.lvNombre.setText(producto.getNombre());
        holder.lvDescripcion.setText(producto.getDescripcion());
        holder.lvPrecio.setText("Precio "+producto.getPrecio());
            try {
                String imageDataBytes = producto.getFoto().substring(producto.getFoto().indexOf(",") + 1);
                byte[] image = Base64.decode(imageDataBytes , Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                holder.ivFoto.setImageBitmap(bitmap);

            }catch (Exception e){

            };

        return rowView;
    }
    public class Holder
    {
        TextView lvNombre;
        TextView lvDescripcion;
        ImageView ivFoto;
        TextView lvPrecio;
    }
}
