package com.boliviabytes.cosmetica.catalogo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link VistaCatalogoProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaCatalogoProducto extends Fragment {

    public final static String TIPO="TIPO";
    private List<Producto> lProductos;
    private int tipo;
    public final static Integer CABELLO=0,ROSTRO=1,CUERPO=2;
    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tipo Parameter 1.
     * @return A new instance of fragment VistaCatalogo.
     */
    // TODO: Rename and change types and number of parameters
    public static VistaCatalogoProducto newInstance(int tipo) {
        VistaCatalogoProducto fragment = new VistaCatalogoProducto();
        Bundle args = new Bundle();
        args.putInt(TIPO, tipo);
        fragment.setArguments(args);
        return fragment;
    }

    public VistaCatalogoProducto() {
        // Required empty public constructor
    }
    public void addOnFragmentInteractionListener(OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          tipo= getArguments().getInt(TIPO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (tipo==CABELLO){
            lProductos=VistaCatalogoPrincipal.getlProductosCabello();
        }else if(tipo==ROSTRO){
            lProductos=VistaCatalogoPrincipal.getlProductosRostro();
        }else if(tipo==CUERPO){
            lProductos=VistaCatalogoPrincipal.getlProductosCuerpo();
        }
        View view=inflater.inflate(R.layout.fragment_vista_catalogo_filtro, container, false);
        if(lProductos!=null){
            final ListView lvProductos= (ListView) view.findViewById(R.id.lv_productos);
            lvProductos.setAdapter(new AdapterCatalogo(getContext(), lProductos));
            lvProductos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) {
                    mostrarDialogoSeleccion(index);
                    return true;


                }

            });

        }
        return view;
    }

    private void mostrarDialogoSeleccion(final int indice){
        final String[] items = {"Ver Producto",  "Añadir Pedido"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if(items[item].toString().equals("Añadir Pedido"))
                    mostrarDialogoConfirmacion(indice);
            }
        });


        builder.show();
    }
    private void mostrarDialogoConfirmacion(final int indice)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final EditText textoBusqueda = new EditText(getActivity());
        builder.setTitle("Añadir cantidad");   // Título
        builder.setView(textoBusqueda);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(textoBusqueda.getText().toString().matches("[0-9]*")){
                    int cantidad=Integer.parseInt(textoBusqueda.getText().toString());
                        Carrito.getInstance().anhadirProducto(lProductos.get(indice),cantidad);
                        Toast.makeText(getContext(),"Agregado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Esto no es un valor numerico", Toast.LENGTH_LONG).show();
                }


            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               // Log.i("Dialogos", "Confirmacion Cancelada.");
            }
        });
        builder.show();


    }
    // TODO: Rename method, update argument anlvd hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void setLayoutCatalogo(){


    }
    public void setLayoutCatalogoFiltro(){


    }



    /**
	 * 
	 * @param categoria
	 */
	public void consultarProductos(int categoria){

	}

	/**
	 * 
	 * @param id
	 */
	public void visualizarProducto(int id){

	}

}
